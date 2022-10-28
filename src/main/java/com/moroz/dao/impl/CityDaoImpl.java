package com.moroz.dao.impl;

import com.moroz.dao.CityDao;
import com.moroz.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@SuppressWarnings("SqlResolve")
@Service
public class CityDaoImpl implements CityDao {
    private static final String FIND_ALL = "SELECT * FROM city";
    private static final String CREATE = "INSERT city(`name`, region) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE city SET `name`=?, region = ? WHERE `name`=?";
    private static final String DELETE = "DELETE FROM city WHERE `name`=?";
    private static final String FIND_BY_ID = "SELECT * FROM city WHERE `name`=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<City> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(City.class));
    }

    @Override
    public Optional<City> findById(String cityName) {
        Optional<City> city;
        try {
            city = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(City.class), cityName));
        } catch (EmptyResultDataAccessException e) {
            city = Optional.empty();
        }
        return city;
    }

    @Override
    public int create(City city) {
        return jdbcTemplate.update(CREATE, city.getCity(), city.getRegion());
    }

    @Override
    public int update(String cityName, City city) {
        return jdbcTemplate.update(UPDATE, city.getCity(), city.getRegion(), cityName);
    }

    @Override
    public int delete(String cityName) {
        return jdbcTemplate.update(DELETE, cityName);
    }
}
