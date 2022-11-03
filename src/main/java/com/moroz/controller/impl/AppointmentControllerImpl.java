package com.moroz.controller.impl;

import com.moroz.controller.AppointmentController;
import com.moroz.domain.Appointment;
import com.moroz.domain.City;
import com.moroz.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AppointmentControllerImpl implements AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @Override
    public List<Appointment> findAll() {
        return appointmentService.findAll();
    }

    @Override
    public Optional<Appointment> findById(Integer appointmentId) {
        return appointmentService.findById(appointmentId);
    }

    @Override
    public int create(Appointment appointment) {
        return appointmentService.create(appointment);
    }

    @Override
    public int update(Integer appointmentId, Appointment appointment) {
        return appointmentService.update(appointmentId, appointment);
    }

    @Override
    public int delete(Integer appointmentId) {
        return appointmentService.delete(appointmentId);
    }

}
