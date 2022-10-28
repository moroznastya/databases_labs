package com.moroz.controller.impl;

import com.moroz.controller.PatientController;
import com.moroz.domain.Patient;
import com.moroz.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientControllerImpl implements PatientController {
    @Autowired
    PatientService patientService;

    @Override
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    @Override
    public Optional<Patient> findById(Integer patientId) {
        return patientService.findById(patientId);
    }

    @Override
    public int create(Patient patient) {
        return patientService.create(patient);
    }

    @Override
    public int update(Integer patientId, Patient patient) {
        return patientService.update(patientId, patient);
    }

    @Override
    public int delete(Integer patientId) {
        return patientService.delete(patientId);
    }
}
