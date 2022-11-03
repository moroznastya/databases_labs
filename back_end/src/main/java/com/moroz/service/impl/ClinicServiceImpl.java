package com.moroz.service.impl;

import com.moroz.domain.Client;
import com.moroz.domain.Clinic;
import com.moroz.exception.*;
import com.moroz.repository.ClinicRepository;
import com.moroz.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClinicServiceImpl implements ClinicService {
    @Autowired
    ClinicRepository clinicRepository;


    public Clinic findById(Integer id) {
        return clinicRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }


    @Transactional
    public Clinic create(Clinic clinic) {
        clinicRepository.save(clinic);
        return clinic;
    }

    @Transactional
    public void update(Integer id, Clinic uClinic) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ClinicNotFoundException(id));
        //update
        clinic.setName(uClinic.getName());
        clinic.setClinicPhone(uClinic.getClinicPhone());
        clinic.setStreetAdress(uClinic.getStreetAdress());
        clinic.setCity(uClinic.getCity());
        clinic.setDoctors(uClinic.getDoctors());
        clinic.setClients(uClinic.getClients());
        clinicRepository.save(clinic);
    }

    @Transactional
    public void delete(Integer id) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        if (!clinic.getClients().isEmpty()) throw new ClientsExistForClinicException(id);
        if (!clinic.getDoctors().isEmpty()) throw new DoctorsExistForClinicException(id);
        clinicRepository.delete(clinic);
    }
}
