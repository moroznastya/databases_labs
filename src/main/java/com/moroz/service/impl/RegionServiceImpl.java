package com.moroz.service.impl;

import com.moroz.dao.RegionDao;
import com.moroz.domain.City;
import com.moroz.domain.Region;
import com.moroz.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionDao regionDao;

    @Override
    public List<Region> findAll() {
        return regionDao.findAll();
    }

    @Override
    public Optional<Region> findById(String regionName) {
        return regionDao.findById(regionName);
    }

    @Override
    public int create(Region region) {
        return regionDao.create(region);
    }

    @Override
    public int update(String regionName, Region region) {
        return regionDao.update(regionName, region);
    }

    @Override
    public int delete(String regionName) {
        return regionDao.delete(regionName);
    }
}
