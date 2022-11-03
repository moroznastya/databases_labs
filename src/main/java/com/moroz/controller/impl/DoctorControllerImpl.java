package com.moroz.controller.impl;

import com.moroz.controller.DoctorController;
import com.moroz.domain.City;
import com.moroz.domain.Doctor;
import com.moroz.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorControllerImpl implements DoctorController {
    @Autowired
    DoctorService doctorService;

    @Override
    public List<Doctor> findAll() {
        return doctorService.findAll();
    }

    @Override
    public Optional<Doctor> findById(Integer doctorId) {
        return doctorService.findById(doctorId);
    }

    @Override
    public int create(Doctor doctor) {
        return doctorService.create(doctor);
    }

    @Override
    public int update(Integer doctorId, Doctor doctor) {
        return doctorService.update(doctorId, doctor);
    }

    @Override
    public int delete(Integer doctorId) {
        return doctorService.delete(doctorId);
    }
}
