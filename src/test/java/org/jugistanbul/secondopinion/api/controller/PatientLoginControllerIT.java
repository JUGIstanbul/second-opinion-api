package org.jugistanbul.secondopinion.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.dto.PatientLoginInformation;
import org.jugistanbul.secondopinion.api.dto.Response;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.jugistanbul.secondopinion.api.repository.PatientRepository;
import org.jugistanbul.secondopinion.api.types.RequestStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PatientLoginControllerIT extends BaseIT {
	@Autowired
	TestRestTemplate testRestTemplate;

	@Autowired
	PatientRepository patientRepository;

	@Test
	public void should_return_login_successful() {

		// Given
		PatientLoginInformation request = this.createSamplePatientInLoginformation();
		patientRepository.save(createSamplePatientEntity());

		// When
		ResponseEntity<Response> patientLoginResponse = testRestTemplate
				.withBasicAuth("1", "1")
				.postForEntity("/v1/login", request, Response.class);

		// Then
		assertThat(patientLoginResponse).isNotNull();
		assertThat(patientLoginResponse.getBody()).isNull();
		assertThat(patientLoginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	public void should_return_login_forbidden() {
		// Given
		PatientLoginInformation request = this.createSamplePatientInLoginformation();
		patientRepository.save(new Patient());

		// When
		ResponseEntity<Response> patientLoginResponse = testRestTemplate.withBasicAuth("1", "1")
				.postForEntity("/v1/login", request, Response.class);

		// Then
		Response response = patientLoginResponse.getBody();
		assertThat(patientLoginResponse).isNotNull();
		assertThat(patientLoginResponse.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
		assertThat(response.getStatus()).isEqualTo(RequestStatus.FAILURE);
		assertThat(response.getErrorCode()).isEqualTo("61");
		assertThat(response.getErrorMessage()).isEqualTo("Belirtilen giris kimlik bilgileri sistemde bulunamadi");

	}

	private final PatientLoginInformation createSamplePatientInLoginformation() {

		PatientLoginInformation request = new PatientLoginInformation();
		request.setUsername("enes");
		request.setPassword("Ts6161");

		return request;
	}

	private final Patient createSamplePatientEntity() {

		Patient entity = new Patient();
		entity.setPhone("5554443322");
		entity.setEmail("enes@yahoo.com");
		entity.setUsername("enes");
		entity.setPassword("Ts6161");

		return entity;
	}
}
