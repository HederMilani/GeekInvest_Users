package com.api.geekinvest_users.dto;

import java.io.Serializable;
import java.util.UUID;

public class StateDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String stateName;
    private String stateUf;
    private boolean stateEnabled;
    private String countryName;

    public StateDto() {
    }

    public StateDto(UUID id, String stateName, String stateUf, String countryName, boolean stateEnabled) {
        this.id = id;
        this.stateName = stateName;
        this.stateUf = stateUf;
        this.countryName = countryName;
        this.stateEnabled = stateEnabled;
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public boolean isStateEnabled() {
        return stateEnabled;
    }

    public void setStateEnabled(boolean stateEnabled) {
        this.stateEnabled = stateEnabled;
    }

    @Override
    public String toString() {
        return "StateDto [id=" + id + ", stateName=" + stateName + ", stateUf=" + stateUf + ", countryName="
                + countryName + "]";
    }
}
