package org.jugistanbul.secondopinion.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Medic;
import org.jugistanbul.secondopinion.api.repository.MedicRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MedicControllerIT extends BaseIT {
	@Autowired
	TestRestTemplate testRestTemplate;

	@Autowired
	public MedicRepository medicRepository;

	public Medic medic;

	@Before
	public void before() {
		medic = new Medic();
		medic.setUsername("username");
		medic.setEmail("email");		
		medic.setPassword("password");		
		medic.setAddress("address");
		medic.setBirthDate(new Date());
		medic.setCurrentTitle("title");
		medic.setEmail("email");
		medic.setFullname("fullname");
		medic.setGender("Male");
		medic.setLicenseNumber("licenseNumber");
		medic.setResumeLink("resumeLink");
	}

	@Test
	public void ShouldReturnAllMedics() {
		medicRepository.save(new Medic());
		medicRepository.save(new Medic());
		medicRepository.save(new Medic());

		ResponseEntity<List> entityList = testRestTemplate.withBasicAuth("1", "1").getForEntity("/v1/medics",
				List.class);

		assertThat(entityList.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entityList.getBody().size()).isGreaterThan(2);
	}

	@Test
	public void ShouldReturnOneMedic() {
		Medic savedMedic = medicRepository.save(new Medic());
		ResponseEntity<Medic> entityList = testRestTemplate.withBasicAuth("1", "1")
				.getForEntity("/v1/medics/" + savedMedic.getId(), Medic.class);

		assertThat(entityList.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entityList.getBody().getId()).isEqualTo(savedMedic.getId());
	}
	
	@Test
	public void ShouldCreateMedic(){
        ResponseEntity<Void> entity = testRestTemplate.withBasicAuth("1", "1").postForEntity("/v1/medics", medic, Void.class);
        
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(entity.getHeaders().getLocation()).hasPath("/api/v1/medics/1");
	}
	
	@Test
	public void ShouldUpdateMedic(){
		final HttpEntity<Medic> httpEntity = new HttpEntity<>(medic);

		final ResponseEntity<Medic> entity = this.testRestTemplate.withBasicAuth("1", "1").exchange("/v1/medics/1", HttpMethod.PUT, httpEntity,
				Medic.class);
		
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
