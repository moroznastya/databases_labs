package com.moroz.dao.impl;

import com.moroz.dao.DoctorDao;
import com.moroz.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorDaoImpl implements DoctorDao {
    private static final String FIND_ALL = "SELECT * FROM doctor";
    private static final String CREATE = "INSERT doctor(id, `name`, surname, work_schedule) " +
            "VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE doctor SET `name`=? surname=?, work_schedule=?";
    private static final String DELETE = "DELETE FROM doctor WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM doctor WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Doctor> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Doctor.class));
    }

    @Override
    public Optional<Doctor> findById(Integer doctorId) {
        Optional<Doctor> doctor;
        try {
            doctor = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Doctor.class), doctorId));
        } catch (EmptyResultDataAccessException e) {
            doctor = Optional.empty();
        }
        return doctor;
    }

    @Override
    public int create(Doctor doctor) {
        return jdbcTemplate.update(CREATE,
                doctor.getId(), doctor.getName(), doctor.getSurname(), doctor.getWorkSchedule());
    }

    @Override
    public int update(Integer doctorId, Doctor doctor) {
        return jdbcTemplate.update(UPDATE, doctor.getId(), doctor.getName(), doctor.getSurname(),
                doctor.getWorkSchedule(), doctorId);
    }

    @Override
    public int delete(Integer doctorId) {
        return jdbcTemplate.update(DELETE, doctorId);
    }
}
