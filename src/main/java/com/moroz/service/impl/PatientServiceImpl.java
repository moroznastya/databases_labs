package com.moroz.service.impl;

import com.moroz.dao.PatientDao;
import com.moroz.domain.City;
import com.moroz.domain.Patient;
import com.moroz.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientDao patientDao;

    @Override
    public List<Patient> findAll() {
        return patientDao.findAll();
    }

    @Override
    public Optional<Patient> findById(Integer patientId) {
        return patientDao.findById(patientId);
    }

    @Override
    public int create(Patient patient) {
        return patientDao.create(patient);
    }

    @Override
    public int update(Integer patientId, Patient patient) {
        return patientDao.update(patientId, patient);
    }

    @Override
    public int delete(Integer patientId) {
        return patientDao.delete(patientId);
    }
}
