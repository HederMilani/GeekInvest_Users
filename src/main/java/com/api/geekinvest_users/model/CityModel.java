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
@Table(name = "TB_CITY")
public class CityModel implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false)
	private String cityName;

	@Column(nullable = false)
	private State state;

    @Column(nullable = false)
    private boolean cityEnabled;

    @Column(nullable = false)
    private boolean cityDeleted;

    public CityModel() {
    }

    public CityModel(UUID id, String cityName, State state, boolean cityEnabled, boolean cityDeleted) {
        this.id = id;
        this.cityName = cityName;
        this.state = state;
        this.cityEnabled = cityEnabled;
        this.cityDeleted = cityDeleted;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    public boolean isCityEnabled() {
        return cityEnabled;
    }
    public void setCityEnabled(boolean cityEnabled) {
        this.cityEnabled = cityEnabled;
    }

    public boolean isCityDeleted() {
        return cityDeleted;
    }
    public void setCityDeleted(boolean cityDeleted) {
        this.cityDeleted = cityDeleted;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + (cityEnabled ? 1231 : 1237);
        result = prime * result + (cityDeleted ? 1231 : 1237);
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
        CityModel other = (CityModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (cityName == null) {
            if (other.cityName != null)
                return false;
        } else if (!cityName.equals(other.cityName))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (cityEnabled != other.cityEnabled)
            return false;
        if (cityDeleted != other.cityDeleted)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CityModel [id=" + id + ", cityName=" + cityName + ", state=" + state + ", cityEnabled=" + cityEnabled
                + ", cityDeleted=" + cityDeleted + "]";
    }
}
