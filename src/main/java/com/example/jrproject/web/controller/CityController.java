package com.example.jrproject.web.controller;

import com.example.jrproject.domain.City;
import com.example.jrproject.service.CityService;
import com.example.jrproject.service.CountryService;
import com.example.jrproject.web.dto.CityDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;
    private final CountryService countryService;

    @GetMapping("/{id}")
    public CityDto getCityById(@PathVariable Long id){
        City city = cityService.getById(id);
        return toDto(city);
    }

    @GetMapping("/byName/{cityName}")
    public CityDto getCityByName(@PathVariable String cityName){
        City city = cityService.getByName(cityName);
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .countryName(city.getCountry().getName())
                .district(city.getDistrict())
                .population(city.getPopulation())
                .build();
    }
    @GetMapping("/all")
    public List<CityDto> getAllCities(){
        List<City> cities = cityService.getAll();
        List<CityDto> cityDtoList = new ArrayList<>();
        for(City city:cities){
            cityDtoList.add(
                    toDto(city)
            );
        }
        return cityDtoList;
    }

    @PostMapping("/create")
    public CityDto createCity(@RequestBody CityDto cityDto){
        City city = toEntity(cityDto);
        cityService.create(city);
        return toDto(city);
    }

    @PostMapping("/update")
    public CityDto updateCity(@RequestBody CityDto cityDto){
        City city = toEntity(cityDto);
        cityService.update(city);
        return toDto(city);
    }

    @DeleteMapping("/delete/{cityId}")
    public ResponseEntity<?> deleteCityById(@PathVariable Long cityId){
        cityService.deleteById(cityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public CityDto toDto(City city){
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .countryName(city.getCountry().getName())
                .district(city.getDistrict())
                .population(city.getPopulation())
                .build();
    }

    public City toEntity(CityDto cityDto){
        return City.builder()
                .id(cityDto.getId())
                .name(cityDto.getName())
                .country(countryService.getByName(cityDto.getCountryName()))
                .district(cityDto.getDistrict())
                .population(cityDto.getPopulation())
                .build();
    }
}
