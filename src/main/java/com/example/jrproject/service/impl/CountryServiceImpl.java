package com.example.jrproject.service.impl;

import com.example.jrproject.domain.Country;
import com.example.jrproject.repository.CountryRepository;
import com.example.jrproject.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "CountryService::getById",key = "#id")
    public Country getById(Long id) {
        return countryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Country with id: "+id+" not found.")
        );
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "CountryService::getByName",key = "#name")
    public Country getByName(String name) {
        Optional<Country> country = countryRepository.findByName(name);
        if(country.isEmpty()){
            throw new RuntimeException("country with name: "+name+" not found.");
        }
        return country.get();
    }



    @Override
    @Transactional(readOnly = true)
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @Override
    @Transactional
    @Caching(put = {
            @CachePut(value = "CountryService::getById",key = "#country.id"),
            @CachePut(value = "CountryService::getByName",key = "#country.name")
    })
    public Country update(Country country) {
        return countryRepository.save(country);
    }
    @Override
    @Transactional
    @Caching(cacheable = {
            @Cacheable(value = "CountryService::getById",key = "#country.id"),
            @Cacheable(value = "CountryService::getByName",key = "#country.name")
    })
    public Country create(Country country) {
        if(countryRepository.findByName(country.getName()).isPresent()){
            throw new RuntimeException("country already exists.");
        }
        return countryRepository.save(country);
    }



    @Override
    @Transactional
    @CacheEvict(value = "CountryService::getById",key = "#id")
    public void deleteById(Long id) {
        Country country = getById(id);
        countryRepository.delete(country);
    }
}
