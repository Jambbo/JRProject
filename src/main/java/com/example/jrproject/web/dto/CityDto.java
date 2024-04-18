package com.example.jrproject.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDto {

    private Integer id;
    private String name;
    private String countryName;
    private String district;
    private Integer population;

}
