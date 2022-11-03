package com.moroz.controller;

import com.moroz.domain.Doctor;
import com.moroz.dto.DoctorDto;
import com.moroz.dto.assembler.DoctorDtoAssembler;
import com.moroz.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorDtoAssembler doctorDtoAssembler;

    @GetMapping(value = "/{doctorId}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable Integer doctorId) {
        Doctor doctor = doctorService.findById(doctorId);
        DoctorDto doctorDto = doctorDtoAssembler.toModel(doctor);
        return new ResponseEntity<>(doctorDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<DoctorDto>> getAllDoctors() {
        List<Doctor> doctors = doctorService.findAll();
        CollectionModel<DoctorDto> doctorDtos = doctorDtoAssembler.toCollectionModel(doctors);
        return new ResponseEntity<>(doctorDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<DoctorDto> addDoctor(@RequestBody Doctor doctor) {
        Doctor newDoctor = doctorService.create(doctor);
        DoctorDto doctorDto = doctorDtoAssembler.toModel(newDoctor);
        return new ResponseEntity<>(doctorDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{doctorId}")
    public ResponseEntity<?> updateDoctor(@RequestBody Doctor uDoctor, @PathVariable Integer doctorId) {
        doctorService.update(doctorId, uDoctor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{doctorId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Integer doctorId) {
        doctorService.delete(doctorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
