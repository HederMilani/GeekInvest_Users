package com.api.geekinvest_users.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_COUNTRY")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false)
	private String countryName;

	@Column(nullable = false)
	private String sigla;

	@Column(nullable = false)
	private boolean countryEnabled;

	public Country() {
		super();
	}

	public Country(UUID id, String countryName, String sigla, boolean countryEnabled) {
		super();

		this.id = id;
		this.countryName = countryName;
		this.sigla = sigla;
		this.countryEnabled = countryEnabled;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		result = prime * result + (countryEnabled ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		if (countryEnabled != other.countryEnabled)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", countryName=" + countryName + ", sigla=" + sigla + ", countryEnabled="
				+ countryEnabled + "]";
	}

}
