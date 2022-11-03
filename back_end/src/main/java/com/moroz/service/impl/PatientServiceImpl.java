package com.moroz.service.impl;

import com.moroz.domain.Patient;
import com.moroz.exception.PatientNotFoundException;
import com.moroz.repository.PatientRepository;
import com.moroz.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;


    public Patient findById(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }


    @Transactional
    public Patient create(Patient patient) {
        patientRepository.save(patient);
        return patient;
    }

    @Transactional
    public void update(Integer id, Patient uPatient) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
        //update
        patient.setBreed(uPatient.getBreed());
        patient.setHealthComplains(uPatient.getHealthComplains());
        patient.setClient(uPatient.getClient());
    }

    @Transactional
    public void delete(Integer id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
        patientRepository.delete(patient);
    }
}
