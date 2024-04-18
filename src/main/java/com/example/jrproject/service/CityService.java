package com.example.jrproject.service;

import com.example.jrproject.domain.City;

import java.util.List;

public interface CityService {

    City getById(Long id);
    City getByName(String name);
    List<City> getAll();
    City create(City city);
    City update(City city);
    void deleteById(Long id);

}
