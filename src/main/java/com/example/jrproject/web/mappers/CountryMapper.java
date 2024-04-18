package com.example.jrproject.web.mappers;

import com.example.jrproject.domain.Continent;
import com.example.jrproject.domain.Country;
import com.example.jrproject.service.CityService;
import com.example.jrproject.web.dto.CountryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper extends Mappable<Country, CountryDto> {
    @Override
    default CountryDto toDto(Country country){
        if(country==null){
            return null;
        }
        return CountryDto.builder()
                .id(country.getId())
                .code(country.getCode())
                .code2(country.getCode2())
                .name(country.getName())
                .continent(country.getContinent().name())
                .region(country.getRegion())
                .surfaceArea(country.getSurfaceArea())
                .indepYear(country.getIndepYear())
                .population(country.getPopulation())
                .lifeExpectancy(country.getLifeExpectancy())
                .gnp(country.getGnp())
                .gnpoId(country.getGnpoId())
                .localName(country.getLocalName())
                .governmentForm(country.getGovernmentForm())
                .headOfState(country.getHeadOfState())
                .capital(country.getCity().getName())
                .build();
    }

    @Override
    default List<CountryDto> toDto(List<Country> countries){
        List<CountryDto> countryDtos = new ArrayList<>();
        for(Country c:countries){
            countryDtos.add(toDto(c));
        }
        return countryDtos;
    }

    default Country toEntity(CountryDto countryDto, CityService cityService){
        return Country.builder()
                .id(countryDto.getId())
                .code(countryDto.getCode())
                .code2(countryDto.getCode2())
                .name(countryDto.getName())
                .continent(Continent.valueOf(countryDto.getContinent().toUpperCase()))
                .region(countryDto.getRegion())
                .surfaceArea(countryDto.getSurfaceArea())
                .indepYear(countryDto.getIndepYear())
                .population(countryDto.getPopulation())
                .lifeExpectancy(countryDto.getLifeExpectancy())
                .gnp(countryDto.getGnp())
                .gnpoId(countryDto.getGnpoId())
                .localName(countryDto.getLocalName())
                .governmentForm(countryDto.getGovernmentForm())
                .headOfState(countryDto.getHeadOfState())
                .city(cityService.getByName(countryDto.getCapital()))
                .build();
    }

}
