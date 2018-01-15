package org.jugistanbul.secondopinion.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Hospital;
import org.jugistanbul.secondopinion.api.repository.HospitalRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HospitalControllerIT extends BaseIT {
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Autowired
	public HospitalRepository hospitalRepository;
	
	@Test
	public void ShouldReturnAllHospitals() {
		//Given
		hospitalRepository.save(new Hospital());
		hospitalRepository.save(new Hospital());
		hospitalRepository.save(new Hospital());

		//When
		ResponseEntity<List> entityList = testRestTemplate
				.withBasicAuth("1", "1")
				.getForEntity("/v1/hospitals",
						List.class);

        //Then
        assertThat(entityList.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entityList.getBody().size()).isEqualTo(3);        
	}
}
