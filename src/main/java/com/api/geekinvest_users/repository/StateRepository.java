package com.api.geekinvest_users.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.geekinvest_users.model.State;

public interface StateRepository extends JpaRepository<State, UUID> {
    
    boolean existsByStateNameIgnoreCase(String stateName);
    boolean existsByStateUfIgnoreCase(String stateUf);
    Optional<State> findByStateNameContainsIgnoreCase(String stateName);
    List<State> findAllByStateEnabledTrue();
}
