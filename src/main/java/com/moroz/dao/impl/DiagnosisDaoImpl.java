package com.moroz.dao.impl;

import com.moroz.dao.DiagnosisDao;
import com.moroz.domain.City;
import com.moroz.domain.Diagnosis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisDaoImpl implements DiagnosisDao {
    private static final String FIND_ALL = "SELECT * FROM diagnosis";
    private static final String CREATE = "INSERT diagnosis(`name`, `description`) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE diagnosis SET `name`=?, `description`=? WHERE `name`=?";
    private static final String DELETE = "DELETE FROM diagnosis WHERE `name`=?";
    private static final String FIND_BY_ID = "SELECT * FROM diagnosis WHERE `name`=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Diagnosis> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Diagnosis.class));
    }

    @Override
    public Optional<Diagnosis> findById(String diagnosisName) {
        Optional<Diagnosis> diagnosis;
        try {
            diagnosis = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Diagnosis.class), diagnosisName));
        } catch (EmptyResultDataAccessException e) {
            diagnosis = Optional.empty();
        }
        return diagnosis;
    }

    @Override
    public int create(Diagnosis diagnosis) {
        return jdbcTemplate.update(CREATE,
                diagnosis.getName(), diagnosis.getDescription());
    }

    @Override
    public int update(String diagnosisName, Diagnosis diagnosis) {
        return jdbcTemplate.update(UPDATE, diagnosis.getName(), diagnosis.getDescription(), diagnosisName);
    }

    @Override
    public int delete(String diagnosisName) {
        return jdbcTemplate.update(DELETE, diagnosisName);
    }
}
