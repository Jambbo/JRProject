package com.example.jrproject.repository;

import com.example.jrproject.domain.CountryLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryLanguageRepository extends JpaRepository<CountryLanguage,Long> {
}
