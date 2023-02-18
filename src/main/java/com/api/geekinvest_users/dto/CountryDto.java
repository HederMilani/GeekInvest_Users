package com.api.geekinvest_users.dto;

public class CountryDto {
	
	private String countryName;

	private String sigla;

	public CountryDto() {
	}

	public CountryDto(String countryName, String sigla) {
		this.countryName = countryName;
		this.sigla = sigla;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	
}
