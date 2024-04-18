package com.example.jrproject.service;

import com.example.jrproject.domain.Country;

import java.util.List;

public interface CountryService {
    Country getById(Long id);
    Country getByName(String name);
    List<Country> getAll();
    Country create(Country country);
    Country update(Country country);
    void deleteById(Long id);

}
