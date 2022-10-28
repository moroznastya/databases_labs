package com.moroz.controller.impl;

import com.moroz.controller.DiagnosisController;
import com.moroz.domain.City;
import com.moroz.domain.Diagnosis;
import com.moroz.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisControllerImpl implements DiagnosisController {
    @Autowired
    DiagnosisService diagnosisService;

    @Override
    public List<Diagnosis> findAll() {
        return diagnosisService.findAll();
    }

    @Override
    public Optional<Diagnosis> findById(String diagnosisName) {
        return diagnosisService.findById(diagnosisName);
    }

    @Override
    public int create(Diagnosis diagnosis) {
        return diagnosisService.create(diagnosis);
    }

    @Override
    public int update(String diagnosisName, Diagnosis diagnosis) {
        return diagnosisService.update(diagnosisName, diagnosis);
    }

    @Override
    public int delete(String diagnosisName) {
        return diagnosisService.delete(diagnosisName);
    }
}
