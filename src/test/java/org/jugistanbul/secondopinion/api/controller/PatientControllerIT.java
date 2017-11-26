package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.dto.PatientInformation;
import org.jugistanbul.secondopinion.api.dto.PatientResponse;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.jugistanbul.secondopinion.api.repository.PatientRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import static org.assertj.core.api.Assertions.assertThat;

public class PatientControllerIT extends BaseIT {

  @Autowired
  TestRestTemplate testRestTemplate;

  @Autowired
  PatientRepository patientRepository;

  @Test
  public void should_create_patient() throws Exception {
    //Given
    PatientInformation request = new PatientInformation();
    request.setUsername("eilhan");
    request.setPassword("test123");
    request.setEmail("eilhan@gmail.com");
    request.setPhone("05309547629");

    //When
    ResponseEntity<PatientResponse> responseEntity = testRestTemplate
        .withBasicAuth("1", "1").postForEntity("/v1/patients", request, PatientResponse.class);

    //Then
    assertThat(responseEntity).isNotNull();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    PatientResponse patientResponse = responseEntity.getBody();
    assertThat(patientResponse).isNotNull();
    assertThat(patientResponse.getPatientId()).isNotNull();
  }

  @Test
  public void should_get_patient_account_info() throws Exception {
    //Given - generate and retrieve user id first


    Patient patient = new Patient();
    patient.setPhone("5554443322");
    patient.setEmail("adere@deloitte.com");
    patient.setUsername("adere");
    patient.setPassword("Aa123456");

    Patient patientSaved = patientRepository.save(patient);

    Long userId = patientSaved.getId();

    //When
    ResponseEntity<PatientInformation> responseEntity = testRestTemplate
        .withBasicAuth("1", "1")
        .getForEntity("/v1/patients/{userId}", PatientInformation.class, userId);

    //Then

    assertThat(responseEntity).isNotNull();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

  }
}
