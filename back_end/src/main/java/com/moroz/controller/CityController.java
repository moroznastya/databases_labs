package com.moroz.controller;

import com.moroz.domain.City;
import com.moroz.dto.CityDto;
import com.moroz.dto.assembler.CityDtoAssembler;
import com.moroz.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cities")
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CityDtoAssembler cityDtoAssembler;

    @GetMapping(value = "/{cityId}")
    public ResponseEntity<CityDto> getCity(@PathVariable String cityId) {
        City city = cityService.findById(cityId);
        CityDto cityDto = cityDtoAssembler.toModel(city);
        return new ResponseEntity<>(cityDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CityDto>> getAllCities() {
        List<City> cities = cityService.findAll();
        CollectionModel<CityDto> cityDtos = cityDtoAssembler.toCollectionModel(cities);
        return new ResponseEntity<>(cityDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CityDto> addCity(@RequestBody City city) {
        City newCity = cityService.create(city);
        CityDto cityDto = cityDtoAssembler.toModel(newCity);
        return new ResponseEntity<>(cityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{cityId}")
    public ResponseEntity<?> updateCity(@RequestBody City uCity, @PathVariable String cityId) {
        cityService.update(cityId, uCity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{cityId}")
    public ResponseEntity<?> deleteCity(@PathVariable String cityId) {
        cityService.delete(cityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
