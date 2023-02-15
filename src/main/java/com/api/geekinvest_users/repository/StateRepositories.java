package com.api.geekinvest_users.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.geekinvest_users.model.State;

public interface StateRepositories extends JpaRepository<State, UUID> {
	
}
