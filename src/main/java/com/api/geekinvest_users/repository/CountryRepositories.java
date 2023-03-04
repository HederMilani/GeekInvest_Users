package com.api.geekinvest_users.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.geekinvest_users.model.Country;

public interface CountryRepositories extends JpaRepository<Country, UUID> {
	
	boolean existsByCountryName(String nameCountry);
	boolean existsBySigla(String sigla);
	Optional<Country> findByCountryNameLikeIgnoreCase(String countryName);
	List<Country> findAllByCountryEnableTrue();

}
