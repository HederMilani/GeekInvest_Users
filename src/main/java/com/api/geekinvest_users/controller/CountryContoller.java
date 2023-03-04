package com.api.geekinvest_users.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.geekinvest_users.constant.RabbitMQConstant;
import com.api.geekinvest_users.dto.CountryDto;
import com.api.geekinvest_users.model.Country;
import com.api.geekinvest_users.service.CountryService;
import com.api.geekinvest_users.service.RabbitMQService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("geekuser/contry")
public class CountryContoller {
	private static final Logger LOG = LogManager.getLogger(CountryContoller.class);

	@Autowired
	private CountryService service;

	@Autowired
	private RabbitMQService rabbitMQService;

	@PostMapping
	public ResponseEntity<Object> createCountry(
			@Valid @RequestBody Country objCountry) {

		objCountry.setId(null);

		LOG.info("Create Country: \n" + objCountry.toString());

		if (objCountry.getCountryName() == null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body("Null: Country is required!");
		}

		if (service.existsByCountryName(objCountry.getCountryName())) {
			Optional<Country> countryOptional = service.findByCountryName(objCountry.getCountryName());
			if (!countryOptional.isPresent() || !countryOptional.get().isCountryEnabled()) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
						.body("Country Error! Contact the Administrator!");
			}

			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Conflict: Country " + objCountry.getCountryName() + " already exists!");
		}

		if (objCountry.getSigla() == null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body("Null: Sigla is required");
		}
		if (objCountry.getSigla().length() > 3) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body("Not Validated: Acronym field must contain up to 3 characters!");
		}
		if (service.existsBySigla(objCountry.getSigla())) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Conflict: Sigla " + objCountry.getSigla() + " already exists!");
		}

		objCountry.setCountryEnabled(true);

		objCountry = service.save(objCountry);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(objCountry.getId())
				.toUri();

		rabbitMQService.sendCountry(RabbitMQConstant.QUEUE_COUNTRY_SAVE, objCountry);

		return ResponseEntity.created(uri).body(objCountry);
	}

	@GetMapping
	public ResponseEntity<Page<Country>> findAll(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
	}

	@GetMapping(value = "/enabled")
    public ResponseEntity<List<Country>> findAllByCountryEnable() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllByCountryEnable());
    }

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
		Optional<Country> countryOptional = service.findById(id);

		if (!countryOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country Not Found!");
		}

		return ResponseEntity.status(HttpStatus.OK).body(countryOptional.get());
	}

	@GetMapping(value = "/name/{countryName}")
	public ResponseEntity<Object> findByCountryName(@PathVariable(value = "countryName") String countryName) {
		Optional<Country> countryOptional = service.findByCountryName(countryName);

		if (!countryOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country Not Found!");
		}

		rabbitMQService.sendCountry(RabbitMQConstant.QUEUE_COUNTRY_SAVE, countryOptional);

		return ResponseEntity.status(HttpStatus.OK).body(countryOptional.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCountry(@PathVariable(value = "id") UUID id) {
		Optional<Country> countryOptional = service.findById(id);

		if (!countryOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country Not Found!");
		}

		countryOptional.get().setCountryEnabled(false);
		service.save(countryOptional.get());

		//TODO: Corrigir as filas do rabbitMQ service
		rabbitMQService.sendCountry(RabbitMQConstant.QUEUE_COUNTRY_DELETE, countryOptional);

		return ResponseEntity.status(HttpStatus.OK).body("Country deleted Successfully!");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCountry(
			@PathVariable(value = "id") UUID id,
			@RequestBody @Valid CountryDto countryDto) {

		Optional<Country> optionalCountry = service.findById(id);

		if (!optionalCountry.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Country Not Found");
		}

		var objCountry = new Country();

		BeanUtils.copyProperties(countryDto, objCountry);
		objCountry.setId(id);

		LOG.info("Country Atualizado: " + objCountry.toString());

		return ResponseEntity.status(HttpStatus.OK)
				.body(service.save(objCountry));
	}

}
