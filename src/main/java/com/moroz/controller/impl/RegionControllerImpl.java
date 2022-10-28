package com.moroz.controller.impl;

import com.moroz.controller.RegionController;
import com.moroz.domain.City;
import com.moroz.domain.Region;
import com.moroz.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionControllerImpl implements RegionController {
    @Autowired
    RegionService regionService;

    @Override
    public List<Region> findAll() {
        return regionService.findAll();
    }

    @Override
    public Optional<Region> findById(String regionName) {
        return regionService.findById(regionName);
    }

    @Override
    public int create(Region region) {
        return regionService.create(region);
    }

    @Override
    public int update(String regionName, Region region) {
        return regionService.update(regionName, region);
    }

    @Override
    public int delete(String regionName) {
        return regionService.delete(regionName);
    }
}
