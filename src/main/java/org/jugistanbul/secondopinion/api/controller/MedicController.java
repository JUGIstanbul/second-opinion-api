package org.jugistanbul.secondopinion.api.controller;

import java.net.URI;

import org.jugistanbul.secondopinion.api.entity.Medic;
import org.jugistanbul.secondopinion.api.service.MedicService;
import org.jugistanbul.secondopinion.api.validator.MedicValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v1/medics")
public class MedicController {

	@Autowired
	private MedicService medicService;
	
	@Autowired
	private MedicValidator medicValidator;

	@GetMapping
	public ResponseEntity<?> getAllMedics() {
		return new ResponseEntity<>(medicService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getMedicById(@PathVariable Long id) {
		Medic medic = medicService.findOne(id);

		if (medic == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(medic, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> saveMedic(@RequestBody Medic medic, BindingResult result) {
		medicValidator.validate(medic, result);

		if (result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
		}
		
		Medic newMedic = medicService.save(medic);

		if (newMedic != null) {
			final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/v1/medics/{id}").build()
					.expand(newMedic.getId()).toUri();

			final HttpHeaders headers = new HttpHeaders();
			headers.setLocation(location);

			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

		return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMedic(@PathVariable Long id, @RequestBody Medic medic, BindingResult result){
		Medic existingMedic = medicService.findOne(id);
		
		if(existingMedic == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		medic.setId(id);
		
		Medic newMedic = medicService.save(medic);
		
		return new ResponseEntity<>(newMedic, HttpStatus.OK);
	}

}
