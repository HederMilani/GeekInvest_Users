package com.api.geekinvest_users.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.geekinvest_users.model.Country;
import com.api.geekinvest_users.repository.CountryRepositories;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepositories repository;

	public Country save( Country country) {
		return repository.save(country);
	}	

	public boolean existsByCountryName(String nameCountry) {
		return repository.existsByCountryNameIgnoreCase(nameCountry);
	}

	public boolean existsBySigla(String sigla) {
		return repository.existsBySiglaIgnoreCase(sigla);
	}

	public Page<Country> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Optional<Country> findById(UUID id) {
		return repository.findById(id);
	}

	public void delete(Country country) {
		repository.delete(country);
	}

	public Optional<Country> findByCountryName(String countrName) {
		return repository.findByCountryNameLikeIgnoreCase(countrName);
	}

}
