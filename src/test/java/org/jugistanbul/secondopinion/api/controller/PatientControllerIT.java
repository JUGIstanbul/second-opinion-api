package org.jugistanbul.secondopinion.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.dto.PatientInformation;
import org.jugistanbul.secondopinion.api.dto.PatientResponse;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.jugistanbul.secondopinion.api.repository.PatientRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PatientControllerIT extends BaseIT {

  @Autowired
  TestRestTemplate testRestTemplate;

  @Autowired
  PatientRepository patientRepository;

  @Test
  public void should_create_patient() throws Exception {
    //Given
    PatientInformation request = this.createSamplePatientInformation();

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
	  
	  //Given
	  PatientInformation request = this.createSamplePatientInformation();
	  
	  //When
	  ResponseEntity<PatientResponse> responseEntity = testRestTemplate
	       .withBasicAuth("1", "1").postForEntity("/v1/patients", request, PatientResponse.class);
	  
	  PatientResponse patientResponse = responseEntity.getBody();
	  Long patiendId = patientResponse.getPatientId();
	  
	  ResponseEntity<PatientInformation> patientInformationEntity =  testRestTemplate.
			  	withBasicAuth("1", "1").getForEntity("/v1/patients/"+patiendId, PatientInformation.class);
	  
	  PatientInformation patientInformation = patientInformationEntity.getBody();
	

	  //Then
	  assertThat(patientInformationEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	  assertThat(patientInformation).isNotNull();	  
	  assertEquals("user@gmail.com",(patientInformation.getEmail()));

  }

  @Test
  public void should_update_patient_account_info() {
    Patient patient = new Patient();
    patient.setPhone("5554443322");
    patient.setEmail("adere@yahoo.com");
    patient.setUsername("adere");
    patient.setPassword("Aa123456");

    Patient patientSaved = patientRepository.save(patient);

    Long userId = patientSaved.getId();

    PatientInformation request = new PatientInformation();
    request.setPhone("05309541111");
    request.setGender("Bay");
    request.setAddress("Istanbul");
    request.setJob("Mühendis");
    request.setAddictiveDrugProfile("drug");
    request.setAlcoholConsumptionProfile("Haftada bir");
    request.setSmokerProfile("Günde yarım paket");


    HttpEntity<PatientInformation> requestEntity = new HttpEntity<PatientInformation>(request);

    ResponseEntity<PatientInformation> response = testRestTemplate
        .withBasicAuth("1", "1")
        .exchange("/v1/patients/" + userId, HttpMethod.PUT, requestEntity, PatientInformation.class,
            new HashMap<String, String>());

    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    //When
    ResponseEntity<PatientInformation> responseEntity = testRestTemplate
        .withBasicAuth("1", "1")
        .getForEntity("/v1/patients/{userId}", PatientInformation.class, userId);

    assertThat(responseEntity).isNotNull();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody().getGender().equals("Bay"));
    assertThat(responseEntity.getBody().getAddress().equals("Istanbul"));
    assertThat(responseEntity.getBody().getPhone().equals("05309541111"));
    assertThat(responseEntity.getBody().getJob().equals("Mühendis"));
    assertThat(responseEntity.getBody().getAddictiveDrugProfile().equals("drug"));
    assertThat(responseEntity.getBody().getAlcoholConsumptionProfile().equals("Haftada bir"));
    assertThat(responseEntity.getBody().getSmokerProfile().equals("Günde yarım paket"));

  }
  
  private PatientInformation createSamplePatientInformation() {
	    PatientInformation request = new PatientInformation();
	    request.setUsername("user");
	    request.setPassword("test123");
	    request.setEmail("user@gmail.com");
	    request.setPhone("05309547630");
	    request.setAddress("demo");
	    request.setBirthday("01/01/1982");
	    request.setJob("demo");
	    request.setGender("male");
	    request.setSmokerProfile("demoSmokerProfile");
	    request.setAddictiveDrugProfile("demoDrugProfile");
	    request.setAlcoholConsumptionProfile("demoConsumptionProfile");
	    
	    return request;
  }
}
