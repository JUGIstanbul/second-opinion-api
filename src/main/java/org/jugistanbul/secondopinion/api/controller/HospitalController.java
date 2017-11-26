package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/v1/hospitals")
public class HospitalController {

	@Autowired
	public HospitalRepository hospitalRepository;
	
	@GetMapping
	public ResponseEntity<?> getHospitals(){
		return new ResponseEntity<>(hospitalRepository.findAll(), HttpStatus.OK);
	}
}
