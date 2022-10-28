package com.moroz.service.impl;

import com.moroz.dao.ServiceDao;
import com.moroz.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceDao serviceDao;

    @Override
    public List<com.moroz.domain.Service> findAll() {
        return serviceDao.findAll();
    }

    @Override
    public Optional<com.moroz.domain.Service> findById(Integer serviceId) {
        return serviceDao.findById(serviceId);
    }

    @Override
    public int create(com.moroz.domain.Service service) {
        return serviceDao.create(service);
    }

    @Override
    public int update(Integer serviceId, com.moroz.domain.Service service) {
        return serviceDao.update(serviceId, service);
    }

    @Override
    public int delete(Integer serviceId) {
        return serviceDao.delete(serviceId);
    }
}
