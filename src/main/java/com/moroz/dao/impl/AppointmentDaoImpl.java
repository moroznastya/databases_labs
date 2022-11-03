package com.moroz.dao.impl;

import com.moroz.dao.AppointmentDao;
import com.moroz.domain.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentDaoImpl implements AppointmentDao {
    private static final String FIND_ALL = "SELECT * FROM appointment";
    private static final String CREATE = "INSERT appointment" +
            "(id, date_time, service_id, doctor_id, patient_id, patient_client_id, clinic_id)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE appointment SET id = ?, date_time = ?, service_id = ?," +
            " doctor_id = ?, patient_id = ?, patient_client_id = ?, clinic_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM appointment WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM appointment WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Appointment> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Appointment.class));
    }

    @Override
    public Optional<Appointment> findById(Integer appointmentId) {
        Optional<Appointment> appointment;
        try {
            appointment = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Appointment.class), appointmentId));
        } catch (EmptyResultDataAccessException e) {
            appointment = Optional.empty();
        }
        return appointment;
    }

    @Override
    public int create(Appointment appointment) {
        return jdbcTemplate.update(CREATE, appointment.getId(), appointment.getClinicId(), appointment.getDateTime(),
                appointment.getDoctorId(), appointment.getPatientId(), appointment.getServiceId(),
                appointment.getPatientClientId());
    }

    @Override
    public int update(Integer appointmentId, Appointment appointment) {
        return jdbcTemplate.update(UPDATE, appointment.getId(), appointment.getClinicId(), appointment.getDateTime(),
                appointment.getDoctorId(), appointment.getPatientId(), appointment.getServiceId(),
                appointment.getPatientClientId(), appointmentId);
    }

    @Override
    public int delete(Integer appointmentId) {
        return jdbcTemplate.update(DELETE, appointmentId);
    }
}
