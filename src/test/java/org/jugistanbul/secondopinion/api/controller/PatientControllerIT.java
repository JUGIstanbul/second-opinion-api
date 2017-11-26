package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.dto.PatientRequest;
import org.jugistanbul.secondopinion.api.dto.PatientResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class PatientControllerIT extends BaseIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void should_create_patient() throws Exception {
        //Given
        PatientRequest request = new PatientRequest();
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
}
