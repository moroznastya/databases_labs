package com.moroz.dao.impl;

import com.moroz.dao.ClinicDao;
import com.moroz.domain.Client;
import com.moroz.domain.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicDaoImpl implements ClinicDao {
    private static final String FIND_ALL = "SELECT * FROM clinic";
    private static final String CREATE = "INSERT clinic(id, `name`, clinic_phone, street_adress, city_name)" +
            " VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE clinic SET `name`=?, clinic_phone=?, street_adress=?, city_name=?" +
            "WHERE id=?";
    private static final String DELETE = "DELETE FROM clinic WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM clinic WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Clinic> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Clinic.class));
    }

    @Override
    public Optional<Clinic> findById(Integer clinictId) {
        Optional<Clinic> clinic;
        try {
            clinic = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Clinic.class), clinictId));
        } catch (EmptyResultDataAccessException e) {
            clinic = Optional.empty();
        }
        return clinic;
    }

    @Override
    public int create(Clinic clinic) {
        return jdbcTemplate.update(CREATE,
                clinic.getId(), clinic.getName(), clinic.getCity(), clinic.getStreetAddress(), clinic.getClinicPhone());
    }

    @Override
    public int update(Integer clinicId, Clinic clinic) {
        return jdbcTemplate.update(UPDATE, clinic.getId(), clinic.getName(),
                clinic.getCity(), clinic.getStreetAddress(), clinic.getClinicPhone(), clinicId);
    }

    @Override
    public int delete(Integer clinicId) {
        return jdbcTemplate.update(DELETE, clinicId);
    }
}
