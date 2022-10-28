package com.moroz.service.impl;

import com.moroz.dao.ClinicDao;
import com.moroz.domain.Clinic;
import com.moroz.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicServiceImpl implements ClinicService {
    @Autowired
    private ClinicDao clinicDao;

    @Override
    public List<Clinic> findAll() {
        return clinicDao.findAll();
    }

    @Override
    public Optional<Clinic> findById(Integer clinicId) {
        return clinicDao.findById(clinicId);
    }

    @Override
    public int create(Clinic clinic) {
        return clinicDao.create(clinic);
    }

    @Override
    public int update(Integer clinicId, Clinic clinic) {
        return clinicDao.update(clinicId, clinic);
    }

    @Override
    public int delete(Integer clinicId) {
        return clinicDao.delete(clinicId);
    }
}
