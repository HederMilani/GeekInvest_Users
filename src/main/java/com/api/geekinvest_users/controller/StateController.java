package com.api.geekinvest_users.controller;

import java.net.URI;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.geekinvest_users.dto.StateDto;
import com.api.geekinvest_users.model.Country;
import com.api.geekinvest_users.model.State;
import com.api.geekinvest_users.service.CountryService;
import com.api.geekinvest_users.service.StateService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@Controller
@RequestMapping("geekuser/state")
public class StateController {
    private static Logger LOG = LogManager.getLogger(StateController.class);

    @Autowired
    private StateService stateService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private State stateObj;

    @PostMapping
    public ResponseEntity<Object> createState(@Valid @RequestBody StateDto stateDto) {
        LOG.info("Create state: " + stateDto.toString());

        stateObj.setId(null);

        if (stateDto.getStateName() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Null! Name State is required");
        }
        stateObj.setStateName(stateDto.getStateName().toUpperCase());

        if (stateDto.getStateUf() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Null! UF State is required");
        }
        stateObj.setStateUf(stateDto.getStateUf().toUpperCase());

        if (stateDto.getCountryName() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Null! Name Country is required");
        }
        Optional<Country> countryObj = countryService.findByCountryName(stateDto.getCountryName());

        if (!countryObj.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Country not found");
        }

        stateObj.setCountry(countryObj.get());

        State state = stateService.save(stateObj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(state.getId())
                .toUri();

        return ResponseEntity.created(uri).body(state);
    }
}
