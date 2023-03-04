package com.api.geekinvest_users.dto;

public class CountryDto {
	
	private String countryName;
	private String sigla;
	private boolean countryEnabled;

	public CountryDto() {
	}

	public CountryDto(String countryName, String sigla, boolean countryEnabled) {
		this.countryName = countryName;
		this.sigla = sigla;
		this.countryEnabled = countryEnabled;
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

	public boolean isCountryEnabled() {
        return countryEnabled;
    }

	public void setCountryEnabled(boolean countryEnabled) {
        this.countryEnabled = countryEnabled;
    }
}
