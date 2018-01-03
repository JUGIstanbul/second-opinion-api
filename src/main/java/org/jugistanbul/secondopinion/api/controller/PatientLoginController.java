package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.dto.PatientLoginInformation;
import org.jugistanbul.secondopinion.api.service.PatientLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/login")
public class PatientLoginController {

	@Autowired
	private PatientLoginService patientLoginService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody PatientLoginInformation patientLoginInformation) {
		patientLoginService.logInPatient(patientLoginInformation);
	}

}
