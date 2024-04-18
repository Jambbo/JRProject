package com.example.jrproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "city", schema = "world")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @JsonIgnoreProperties("cities")
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "country_id")
    private Country country;
    private String district;
    private Integer population;
}
