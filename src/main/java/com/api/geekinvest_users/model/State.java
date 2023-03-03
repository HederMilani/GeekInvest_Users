package com.api.geekinvest_users.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_STATE")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false)
	private String stateName;

	@Column(nullable = false)
	private String stateUf;

	@JoinColumn
	@ManyToOne
	private Country country;

	public State() {
	}

	public State(UUID id, String stateName, String stateUf, Country country) {
		this.id = id;
		this.stateName = stateName;
		this.stateUf = stateUf;
		this.country = country;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateUf() {
		return stateUf;
	}

	public void setStateUf(String stateUf) {
		this.stateUf = stateUf;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((stateName == null) ? 0 : stateName.hashCode());
		result = prime * result + ((stateUf == null) ? 0 : stateUf.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
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
		State other = (State) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (stateName == null) {
			if (other.stateName != null)
				return false;
		} else if (!stateName.equals(other.stateName))
			return false;
		if (stateUf == null) {
			if (other.stateUf != null)
				return false;
		} else if (!stateUf.equals(other.stateUf))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", stateName=" + stateName + ", stateUf=" + stateUf + ", country=" + country + "]";
	}

}
