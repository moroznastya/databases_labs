package com.moroz.controller;

import com.moroz.domain.Patient;
import com.moroz.dto.PatientDto;
import com.moroz.dto.assembler.PatientDtoAssembler;
import com.moroz.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientDtoAssembler patientDtoAssembler;

    @GetMapping(value = "/{patientId}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable Integer patientId) {
        Patient patient = patientService.findById(patientId);
        PatientDto patientDto = patientDtoAssembler.toModel(patient);
        return new ResponseEntity<>(patientDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<PatientDto>> getAllPatients() {
        List<Patient> patients = patientService.findAll();
        CollectionModel<PatientDto> patientDtos = patientDtoAssembler.toCollectionModel(patients);
        return new ResponseEntity<>(patientDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<PatientDto> addPatient(@RequestBody Patient patient) {
        Patient newPatient = patientService.create(patient);
        PatientDto patientDto = patientDtoAssembler.toModel(newPatient);
        return new ResponseEntity<>(patientDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{patientId}")
    public ResponseEntity<?> updatePatient(@RequestBody Patient uPatient, @PathVariable Integer patientId) {
        patientService.update(patientId, uPatient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable Integer patientId) {
        patientService.delete(patientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
