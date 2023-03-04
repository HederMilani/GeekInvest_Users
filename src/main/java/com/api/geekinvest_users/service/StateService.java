package com.api.geekinvest_users.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.geekinvest_users.model.State;
import com.api.geekinvest_users.repository.StateRepository;

@Service
public class StateService {
    
    @Autowired
    private StateRepository stateRepository;

    public State save(State state) {
        return stateRepository.save(state);
    }

    public List<State> findAllState() {
        return stateRepository.findAll();
    }

    public List<State> findAllStateEnabled(){
        return stateRepository.findAllByStateEnabledTrue();
    }

    public Optional<State> findById(UUID id){
        return stateRepository.findById(id);
    }
    
}
