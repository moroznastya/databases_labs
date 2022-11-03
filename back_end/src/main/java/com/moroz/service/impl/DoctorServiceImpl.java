package com.moroz.service.impl;


import com.moroz.domain.Doctor;
import com.moroz.exception.*;
import com.moroz.repository.DoctorRepository;
import com.moroz.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;


    public Doctor findById(Integer id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id));
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }


    @Transactional
    public Doctor create(Doctor doctor) {
        doctorRepository.save(doctor);
        return doctor;
    }

    @Transactional
    public void update(Integer id, Doctor uDoctor) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id));
        //update
        doctor.setName(uDoctor.getName());
        doctor.setSurname(uDoctor.getSurname());
        doctor.setWorkSchedule(uDoctor.getWorkSchedule());
        doctor.setClinics(uDoctor.getClinics());
        doctorRepository.save(doctor);
    }

    @Transactional
    public void delete(Integer id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id));
        if (!doctor.getClinics().isEmpty()) throw new ClinicsExistForDoctorException(id);
        doctorRepository.delete(doctor);
    }

}
