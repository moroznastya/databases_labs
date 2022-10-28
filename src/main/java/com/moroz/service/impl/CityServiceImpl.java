package com.moroz.service.impl;

import com.moroz.dao.CityDao;
import com.moroz.domain.City;
import com.moroz.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> findAll() {
        return cityDao.findAll();
    }

    @Override
    public Optional<City> findById(String cityName) {
        return cityDao.findById(cityName);
    }

    @Override
    public int create(City city) {
        return cityDao.create(city);
    }

    @Override
    public int update(String cityName, City city) {
        return cityDao.update(cityName, city);
    }

    @Override
    public int delete(String cityName) {
        return cityDao.delete(cityName);
    }
}
