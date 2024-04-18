package com.example.jrproject.service.impl;

import com.example.jrproject.domain.City;
import com.example.jrproject.repository.CityRepository;
import com.example.jrproject.service.CityService;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;


    @Override
    @Cacheable(value="CityService::getById",key = "#id")
    @Transactional(readOnly = true)
    public City getById(Long id) {
        return cityRepository.findById(id).orElseThrow(
                ()->new RuntimeException("city with id: "+id+" not found")
        );
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "CityService::getByName",key = "#name")
    public City getByName(String name) {
        return cityRepository.findByName(name).orElseThrow(
                ()->new RuntimeException("City with name "+name+" not found.")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    @Transactional
    @Caching(cacheable = {
            @Cacheable(value = "CityService::getById",key = "#city.id"),
            @Cacheable(value = "CityService::getByName",key = "#city.name")
    })
    public City create(City city) {
        if(cityRepository.findByName(city.getName()).isPresent()){
            throw new RuntimeException("city already exists.");
        }
        cityRepository.save(city);
        return city;
    }

    @Override
    @Transactional
    @Caching(put = {
            @CachePut(value = "CityService::getById",key="#city.id"),
            @CachePut(value = "CityService::getByName",key = "#city.name")
    })
    public City update(City city) {
        return cityRepository.save(city);

    }

    @Override
    @Transactional
    @CacheEvict(value = "CityService::getById",key = "#id")
    public void deleteById(Long id) {
       cityRepository.deleteById(id);
    }


}
