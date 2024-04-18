package com.example.jrproject.web.controller;

import com.example.jrproject.domain.Continent;
import com.example.jrproject.domain.Country;
import com.example.jrproject.service.CityService;
import com.example.jrproject.service.CountryService;
import com.example.jrproject.web.dto.CountryDto;
import com.example.jrproject.web.mappers.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;
    private final CityService cityService;
    private final CountryMapper countryMapper;

    @GetMapping("/{countryId}")
    public CountryDto getCountryById(@PathVariable Long countryId){
        Country country = countryService.getById(countryId);
        return countryMapper.toDto(country);
    }

    @GetMapping("/byName/{countryName}")
    public CountryDto getCountryByName(@PathVariable String countryName){
        Country country =  countryService.getByName(countryName);
        return countryMapper.toDto(country);
    }

    @GetMapping("/all")
    public List<CountryDto>getAllCountries(){
        List<Country> countries = countryService.getAll();
       return countryMapper.toDto(countries);
    }

    @PostMapping("/create")
    public CountryDto createCountry(CountryDto countryDto){
        Country country = countryMapper.toEntity(countryDto,cityService);
        countryService.create(country);
        return countryMapper.toDto(country);
    }
    @PutMapping("/update")
    public CountryDto updateCountry(CountryDto countryDto){
        Country country = countryMapper.toEntity(countryDto);
        countryService.update(country);
        return countryMapper.toDto(country);
    }
    @DeleteMapping("/{countryId}")
    public ResponseEntity<?> deleteCountryById(@PathVariable Long countryId){
        countryService.deleteById(countryId);
        return ResponseEntity.ok().body("Country successfully deleted.");
    }

//    public CountryDto toDto(Country country){
//        return CountryDto.builder()
//                .id(country.getId())
//                .code(country.getCode())
//                .code2(country.getCode2())
//                .name(country.getName())
//                .continent(country.getContinent().name())
//                .region(country.getRegion())
//                .surfaceArea(country.getSurfaceArea())
//                .indepYear(country.getIndepYear())
//                .population(country.getPopulation())
//                .lifeExpectancy(country.getLifeExpectancy())
//                .gnp(country.getGnp())
//                .gnpoId(country.getGnpoId())
//                .localName(country.getLocalName())
//                .governmentForm(country.getGovernmentForm())
//                .headOfState(country.getHeadOfState())
//                .capital(country.getCity().getName())
//                .build();
//    }
//    public Country toEntity(CountryDto countryDto){
//        return Country.builder()
//                .id(countryDto.getId())
//                .code(countryDto.getCode())
//                .code2(countryDto.getCode2())
//                .name(countryDto.getName())
//                .continent(Continent.valueOf(countryDto.getContinent().toUpperCase()))
//                .region(countryDto.getRegion())
//                .surfaceArea(countryDto.getSurfaceArea())
//                .indepYear(countryDto.getIndepYear())
//                .population(countryDto.getPopulation())
//                .lifeExpectancy(countryDto.getLifeExpectancy())
//                .gnp(countryDto.getGnp())
//                .gnpoId(countryDto.getGnpoId())
//                .localName(countryDto.getLocalName())
//                .governmentForm(countryDto.getGovernmentForm())
//                .headOfState(countryDto.getHeadOfState())
//                .city(cityService.getByName(countryDto.getCapital()))
//                .build();
//    }


}
