package com.moroz.controller;

import com.moroz.domain.Clinic;
import com.moroz.dto.ClinicDto;
import com.moroz.dto.assembler.ClinicDtoAssembler;
import com.moroz.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clinics")
public class ClinicController {
    @Autowired
    private ClinicService clinicService;
    @Autowired
    private ClinicDtoAssembler clinicDtoAssembler;

    @GetMapping(value = "/{clinicId}")
    public ResponseEntity<ClinicDto> getClinic(@PathVariable Integer clinicId) {
        Clinic clinic = clinicService.findById(clinicId);
        ClinicDto clinicDto = clinicDtoAssembler.toModel(clinic);
        return new ResponseEntity<>(clinicDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ClinicDto>> getAllClinics() {
        List<Clinic> clinics = clinicService.findAll();
        CollectionModel<ClinicDto> clinicDtos = clinicDtoAssembler.toCollectionModel(clinics);
        return new ResponseEntity<>(clinicDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ClinicDto> addClinic(@RequestBody Clinic clinic) {
        Clinic newClinic = clinicService.create(clinic);
        ClinicDto clinicDto = clinicDtoAssembler.toModel(newClinic);
        return new ResponseEntity<>(clinicDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{clinicId}")
    public ResponseEntity<?> updateClinic(@RequestBody Clinic uClinic, @PathVariable Integer clinicId) {
        clinicService.update(clinicId, uClinic);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{clinicId}")
    public ResponseEntity<?> deleteClinic(@PathVariable Integer clinicId) {
        clinicService.delete(clinicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
