package com.api.geekinvest_users.dto;

import java.io.Serializable;
import java.util.UUID;

import com.api.geekinvest_users.model.State;

public class CityDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
	private String cityName;
	private State state;
    private boolean cityEnabled;

    
    
}
