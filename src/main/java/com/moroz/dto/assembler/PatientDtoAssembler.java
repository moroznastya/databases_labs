package com.moroz.dto.assembler;

import com.moroz.controller.ClinicController;
import com.moroz.controller.DoctorController;
import com.moroz.controller.PatientController;
import com.moroz.domain.Doctor;
import com.moroz.domain.Patient;
import com.moroz.dto.DoctorDto;
import com.moroz.dto.PatientDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PatientDtoAssembler implements RepresentationModelAssembler<Patient, PatientDto> {
    @Override
    public PatientDto toModel(Patient entity) {
        PatientDto patientDto = PatientDto.builder()
                .id(entity.getId())
                .breed(entity.getBreed())
                .healthComplains(entity.getHealthComplains())
                .build();
        Link selfLink = linkTo(methodOn(PatientController.class).getPatient(patientDto.getId())).withSelfRel();
        patientDto.add(selfLink);
        return patientDto;
    }

    @Override
    public CollectionModel<PatientDto> toCollectionModel(Iterable<? extends Patient> entities) {
        CollectionModel<PatientDto> patientDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(PatientController.class).getAllPatients()).withSelfRel();
        patientDtos.add(selfLink);
        return patientDtos;
    }
}
