package com.moroz.service.impl;

import com.moroz.dao.DoctorDao;
import com.moroz.domain.City;
import com.moroz.domain.Doctor;
import com.moroz.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorDao doctorDao;

    @Override
    public List<Doctor> findAll() {
        return doctorDao.findAll();
    }

    @Override
    public Optional<Doctor> findById(Integer doctorId) {
        return doctorDao.findById(doctorId);
    }

    @Override
    public int create(Doctor doctor) {
        return doctorDao.create(doctor);
    }

    @Override
    public int update(Integer doctorId, Doctor doctor) {
        return doctorDao.update(doctorId, doctor);
    }

    @Override
    public int delete(Integer doctorId) {
        return doctorDao.delete(doctorId);
    }
}
