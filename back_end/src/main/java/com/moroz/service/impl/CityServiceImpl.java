package com.moroz.service.impl;

import com.moroz.domain.City;
import com.moroz.exception.CityNotFoundException;
import com.moroz.exception.ClinicsExistForCityException;
import com.moroz.repository.CityRepository;
import com.moroz.repository.ClinicRepository;
import com.moroz.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    ClinicRepository clinicRepository;

    public City findById(String name) {
        return cityRepository.findById(name)
                .orElseThrow(() -> new CityNotFoundException(name));
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Transactional
    public City create(City city) {
        cityRepository.save(city);
        return city;
    }

    @Transactional
    public void update(String name, City uCity) {
        City city = cityRepository.findById(name)
                .orElseThrow(() -> new CityNotFoundException(name));
        //update
        city.setCity(uCity.getName(), uCity.getClinics());
        cityRepository.save(city);
    }

    @Transactional
    public void delete(String name) {
        City city = cityRepository.findById(name)
                .orElseThrow(() -> new CityNotFoundException(name));
        if (!city.getClinics().isEmpty()) throw new ClinicsExistForCityException(name);
        cityRepository.delete(city);
    }
}
