package com.api.geekinvest_users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.geekinvest_users.model.CityModel;
import com.api.geekinvest_users.repository.CityRepository;

@Service
public class CityService {
    
    @Autowired
    private CityRepository cityRepository;
    
    public CityModel saveCity(CityModel city){
        return cityRepository.save(city);
    }
    
}
