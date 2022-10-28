package com.moroz.controller.impl;

import com.moroz.controller.TreatmentController;
import com.moroz.domain.City;
import com.moroz.domain.Treatment;
import com.moroz.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentControllerImpl implements TreatmentController {
    @Autowired
    TreatmentService treatmentService;

    @Override
    public List<Treatment> findAll() {
        return treatmentService.findAll();
    }

    @Override
    public Optional<Treatment> findById(Integer treatmentId) {
        return treatmentService.findById(treatmentId);
    }

    @Override
    public int create(Treatment treatment) {
        return treatmentService.create(treatment);
    }

    @Override
    public int update(Integer treatmentId, Treatment treatment) {
        return treatmentService.update(treatmentId, treatment);
    }

    @Override
    public int delete(Integer treatmentId) {
        return treatmentService.delete(treatmentId);
    }
}
