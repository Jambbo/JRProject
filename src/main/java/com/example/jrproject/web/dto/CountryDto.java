package com.example.jrproject.web.dto;

import com.example.jrproject.domain.Continent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryDto {

    private Integer id;
    private String code;
    private String code2;
    private String name;
    private String continent;
    private String region;
    private BigDecimal surfaceArea;
    private Short indepYear;
    private Integer population;
    private BigDecimal lifeExpectancy;
    private BigDecimal gnp;
    private BigDecimal gnpoId;
    private String localName;
    private String governmentForm;
    private String headOfState;
    private String capital;


}
