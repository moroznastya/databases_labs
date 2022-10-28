package com.moroz.dao.impl;

import com.moroz.dao.TreatmentDao;
import com.moroz.domain.Treatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentDaoImpl implements TreatmentDao {
    private static final String FIND_ALL = "SELECT * FROM treatment";
    private static final String CREATE = "INSERT treatment(id, `description`, recomenration, diagnosis_name, patient_id)" +
            " VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE treatment SET `description`=?, recomenration=?, diagnosis_name=?, patient_id=?" +
            "WHERE id=?";
    private static final String DELETE = "DELETE FROM treatment WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM treatment WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Treatment> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Treatment.class));
    }

    @Override
    public Optional<Treatment> findById(Integer treatmentId) {
        Optional<Treatment> treatment;
        try {
            treatment = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Treatment.class), treatmentId));
        } catch (EmptyResultDataAccessException e) {
            treatment = Optional.empty();
        }
        return treatment;
    }

    @Override
    public int create(Treatment treatment) {
        return jdbcTemplate.update(CREATE,
                treatment.getId(), treatment.getPatientId(), treatment.getDiagnosisName(),
                treatment.getDescription(), treatment.getRecommendation());
    }

    @Override
    public int update(Integer treatmentId, Treatment treatment) {
        return jdbcTemplate.update(UPDATE, treatment.getId(),  treatment.getPatientId(), treatment.getDiagnosisName(),
                treatment.getDescription(), treatment.getRecommendation(), treatmentId);
    }

    @Override
    public int delete(Integer treatmentId) {
        return jdbcTemplate.update(DELETE, treatmentId);
    }
}
