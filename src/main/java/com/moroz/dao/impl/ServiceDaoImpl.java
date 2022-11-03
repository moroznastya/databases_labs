package com.moroz.dao.impl;

import com.moroz.dao.ServiceDao;
import com.moroz.domain.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceDaoImpl implements ServiceDao {
    private static final String FIND_ALL = "SELECT * FROM service";
    private static final String CREATE = "INSERT service(id, `name`, duration, is_available, doctor_id)" +
            " VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE service" +
            " SET `name`=? duration=?, is_available=?, doctor_id =? WHERE id=?";
    private static final String DELETE = "DELETE FROM service WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM service WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Service> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Service.class));
    }

    @Override
    public Optional<Service> findById(Integer serviceId) {
        Optional<Service> service;
        try {
            service = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Service.class), serviceId));
        } catch (EmptyResultDataAccessException e) {
            service = Optional.empty();
        }
        return service;
    }

    @Override
    public int create(Service service) {
        return jdbcTemplate.update(CREATE,
                service.getId(), service.getDoctorId(), service.getName(), service.getDuration(),
                service.getIsAvailable());
    }

    @Override
    public int update(Integer serviceId, Service service) {
        return jdbcTemplate.update(UPDATE, service.getId(), service.getDoctorId(), service.getName(),
                service.getDuration(), service.getIsAvailable(), serviceId);
    }

    @Override
    public int delete(Integer serviceId) {
        return jdbcTemplate.update(DELETE, serviceId);
    }
}
