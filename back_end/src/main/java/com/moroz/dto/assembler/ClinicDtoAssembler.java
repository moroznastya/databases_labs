package com.moroz.dto.assembler;


import com.moroz.controller.ClinicController;
import com.moroz.domain.Clinic;
import com.moroz.dto.ClinicDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClinicDtoAssembler implements RepresentationModelAssembler<Clinic, ClinicDto>{
    @Override
    public ClinicDto toModel(Clinic entity) {
        ClinicDto clinicDto = ClinicDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .clinicPhone(entity.getClinicPhone())
                .streetAddress(entity.getStreetAdress())
                .build();
        Link selfLink = linkTo(methodOn(ClinicController.class).getClinic(clinicDto.getId())).withSelfRel();
        clinicDto.add(selfLink);
        return clinicDto;
    }

    @Override
    public CollectionModel<ClinicDto> toCollectionModel(Iterable<? extends Clinic> entities) {
        CollectionModel<ClinicDto> clinicDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ClinicController.class).getAllClinics()).withSelfRel();
        clinicDtos.add(selfLink);
        return clinicDtos;
    }
}
