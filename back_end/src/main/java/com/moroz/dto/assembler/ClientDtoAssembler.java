package com.moroz.dto.assembler;

import com.moroz.controller.ClientContoller;
import com.moroz.domain.Client;
import com.moroz.dto.ClientDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClientDtoAssembler implements RepresentationModelAssembler<Client, ClientDto> {
    @Override
    public ClientDto toModel(Client entity) {
        ClientDto clientDto = ClientDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .contactNumber(entity.getContactNumber())
                .build();
        Link selfLink = linkTo(methodOn(ClientContoller.class).getClient(clientDto.getId())).withSelfRel();
        clientDto.add(selfLink);
        return clientDto;
    }

    @Override
    public CollectionModel<ClientDto> toCollectionModel(Iterable<? extends Client> entities) {
        CollectionModel<ClientDto> clientDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ClientContoller.class).getAllClients()).withSelfRel();
        clientDtos.add(selfLink);
        return clientDtos;
    }
}
