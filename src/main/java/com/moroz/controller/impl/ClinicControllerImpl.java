package com.moroz.controller.impl;

import com.moroz.controller.ClinicController;
import com.moroz.domain.City;
import com.moroz.domain.Clinic;
import com.moroz.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicControllerImpl implements ClinicController {
    @Autowired
    ClinicService clinicService;

    @Override
    public List<Clinic> findAll() {
        return clinicService.findAll();
    }

    @Override
    public Optional<Clinic> findById(Integer clinicId) {
        return clinicService.findById(clinicId);
    }

    @Override
    public int create(Clinic clinic) {
        return clinicService.create(clinic);
    }

    @Override
    public int update(Integer clinicId, Clinic clinic) {
        return clinicService.update(clinicId, clinic);
    }

    @Override
    public int delete(Integer clinicId) {
        return clinicService.delete(clinicId);
    }
}
