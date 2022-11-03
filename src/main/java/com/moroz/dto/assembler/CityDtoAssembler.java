package com.moroz.dto.assembler;

import com.moroz.dto.CityDto;
import com.moroz.controller.CityController;
import com.moroz.domain.City;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CityDtoAssembler implements RepresentationModelAssembler<City, CityDto> {
    @Override
    public CityDto toModel(City entity) {
        CityDto cityDto = CityDto.builder()
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(CityController.class).getCity(cityDto.getName())).withSelfRel();
        cityDto.add(selfLink);
        return cityDto;
    }

    @Override
    public CollectionModel<CityDto> toCollectionModel(Iterable<? extends City> entities) {
        CollectionModel<CityDto> cityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CityController.class).getAllCities()).withSelfRel();
        cityDtos.add(selfLink);
        return cityDtos;
    }
}
