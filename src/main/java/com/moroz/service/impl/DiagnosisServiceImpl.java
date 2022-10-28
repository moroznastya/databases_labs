package com.moroz.service.impl;

import com.moroz.dao.DiagnosisDao;
import com.moroz.domain.Diagnosis;
import com.moroz.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {
    @Autowired
    private DiagnosisDao diagnosisDao;

    @Override
    public List<Diagnosis> findAll() {
        return diagnosisDao.findAll();
    }

    @Override
    public Optional<Diagnosis> findById(String diagnosisName) {
        return diagnosisDao.findById(diagnosisName);
    }

    @Override
    public int create(Diagnosis diagnosis) {
        return diagnosisDao.create(diagnosis);
    }

    @Override
    public int update(String diagnosisName, Diagnosis diagnosis) {
        return diagnosisDao.update(diagnosisName, diagnosis);
    }

    @Override
    public int delete(String diagnosisName) {
        return diagnosisDao.delete(diagnosisName);
    }
}
