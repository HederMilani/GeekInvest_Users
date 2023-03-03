package com.api.geekinvest_users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.geekinvest_users.model.State;
import com.api.geekinvest_users.repository.StateRepository;

@Service
public class StateService {
    
    @Autowired
    private StateRepository stateRepository;

    public void save(State state) {
        stateRepository.save(state);
    }
}
