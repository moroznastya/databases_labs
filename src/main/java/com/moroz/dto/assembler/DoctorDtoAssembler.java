package com.moroz.dto.assembler;

import com.moroz.controller.ClinicController;
import com.moroz.controller.DoctorController;
import com.moroz.domain.Doctor;
import com.moroz.dto.DoctorDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DoctorDtoAssembler implements RepresentationModelAssembler <Doctor, DoctorDto> {
    @Override
    public DoctorDto toModel(Doctor entity) {
        DoctorDto doctorDto = DoctorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .workSchedule(entity.getWorkSchedule())
                .build();
        Link selfLink = linkTo(methodOn(DoctorController.class).getDoctor(doctorDto.getId())).withSelfRel();
        doctorDto.add(selfLink);
        return doctorDto;
    }

    @Override
    public CollectionModel<DoctorDto> toCollectionModel(Iterable<? extends Doctor> entities) {
        CollectionModel<DoctorDto> doctorDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DoctorController.class).getAllDoctors()).withSelfRel();
        doctorDtos.add(selfLink);
        return doctorDtos;
    }
}
