package com.example.jrproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "country_language",schema = "world")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CountryLanguage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    private String language;
    @Column(name = "is_official",columnDefinition = "BIT")
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private Boolean isOfficial;

    private BigDecimal percentage;
}
