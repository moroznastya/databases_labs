package com.moroz.dao.impl;

import com.moroz.dao.PatientDao;
import com.moroz.domain.Doctor;
import com.moroz.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientDaoImpl implements PatientDao {
    private static final String FIND_ALL = "SELECT * FROM patient";
    private static final String CREATE = "INSERT patient(id, breed, health_complains, client_id) VALUES (?)";
    private static final String UPDATE = "UPDATE patient SET breed=?, health_complains=?, client_id=?";
    private static final String DELETE = "DELETE FROM patient WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM patient WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Patient> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Patient.class));
    }

    @Override
    public Optional<Patient> findById(Integer patientId) {
        Optional<Patient> patient;
        try {
            patient = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Patient.class), patientId));
        } catch (EmptyResultDataAccessException e) {
            patient = Optional.empty();
        }
        return patient;
    }

    @Override
    public int create(Patient patient) {
        return jdbcTemplate.update(CREATE,
                patient.getId(), patient.getClientId(), patient.getBreed(), patient.getHealthComplains());
    }

    @Override
    public int update(Integer patientId, Patient patient) {
        return jdbcTemplate.update(UPDATE, patient.getId(), patient.getClientId(), patient.getBreed(),
                patient.getHealthComplains(), patientId);
    }

    @Override
    public int delete(Integer patientId) {
        return jdbcTemplate.update(DELETE, patientId);
    }

}
