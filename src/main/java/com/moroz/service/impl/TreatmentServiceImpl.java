package com.moroz.service.impl;

import com.moroz.dao.TreatmentDao;
import com.moroz.domain.Treatment;
import com.moroz.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentServiceImpl implements TreatmentService {
    @Autowired
    private TreatmentDao treatmentDao;

    @Override
    public List<Treatment> findAll() {
        return treatmentDao.findAll();
    }

    @Override
    public Optional<Treatment> findById(Integer treatmentId) {
        return treatmentDao.findById(treatmentId);
    }

    @Override
    public int create(Treatment treatment) {
        return treatmentDao.create(treatment);
    }

    @Override
    public int update(Integer treatmentId, Treatment treatment) {
        return treatmentDao.update(treatmentId, treatment);
    }

    @Override
    public int delete(Integer treatmentId) {
        return treatmentDao.delete(treatmentId);
    }
}
