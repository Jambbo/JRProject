package com.example.jrproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country",schema = "world")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Country implements Serializable {

    @Id
    private Integer id;

    private String code;
    @Column(name = "code_2")
    private String code2;
    private String name;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "continent",columnDefinition = "int")
    private Continent continent;
    private String region;
    @Column(name = "surface_area")
    private BigDecimal surfaceArea;
    @Column(name = "indep_year")
    private Short indepYear;
    private Integer population;
    @Column(name = "life_expectancy")
    private BigDecimal lifeExpectancy;
    private BigDecimal gnp;
    @Column(name = "gnpo_id")
    private BigDecimal gnpoId;
    @Column(name = "local_name")
    private String localName;
    @Column(name = "government_form")
    private String governmentForm;
    @Column(name = "head_of_state")
    private String headOfState;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "capital")
    private City city;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "country")
    private Set<CountryLanguage> countryLanguages = new HashSet<>();
}
