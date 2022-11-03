package com.moroz.controller.impl;

import com.moroz.controller.ServiceController;
import com.moroz.domain.City;
import com.moroz.domain.Service;
import com.moroz.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceControllerImpl implements ServiceController {
    @Autowired
    ServiceService serviceService;

    @Override
    public List<Service> findAll() {
        return serviceService.findAll();
    }

    @Override
    public Optional<Service> findById(Integer serviceId) {
        return serviceService.findById(serviceId);
    }

    @Override
    public int create(Service service) {
        return serviceService.create(service);
    }

    @Override
    public int update(Integer serviceId, Service service) {
        return serviceService.update(serviceId, service);
    }

    @Override
    public int delete(Integer serviceId) {
        return serviceService.delete(serviceId);
    }
}
