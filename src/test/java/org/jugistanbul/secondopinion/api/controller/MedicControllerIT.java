package org.jugistanbul.secondopinion.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Medic;
import org.jugistanbul.secondopinion.api.repository.MedicRepository;
import org.jugistanbul.secondopinion.api.types.Gender;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
		medic.setGender(Gender.MALE);
		medic.setLicenseNumber("licenseNumber");
		medic.setResumeLink("resumeLink");
	}

	@Test
	public void should_return_all_medics() {
		//Given
		medicRepository.save(new Medic());
		medicRepository.save(new Medic());
		medicRepository.save(new Medic());

		//When
		ResponseEntity<List> entityList = testRestTemplate
				.withBasicAuth("1", "1")
				.getForEntity("/v1/medics", List.class);

		//Then
		assertThat(entityList.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entityList.getBody().size()).isGreaterThan(2);
	}

	@Test
	public void should_return_one_medic() {
		//Given
		Medic savedMedic = medicRepository.save(new Medic());

		//When
		ResponseEntity<Medic> entityList = testRestTemplate
				.withBasicAuth("1", "1")
				.getForEntity("/v1/medics/" + savedMedic.getId(),
						Medic.class);

		//Then
		assertThat(entityList.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entityList.getBody().getId()).isEqualTo(savedMedic.getId());
	}
	
	@Test
	public void should_save_medic(){
		//Given

		//When
        ResponseEntity<Void> entity = testRestTemplate
				.withBasicAuth("1", "1")
				.postForEntity("/v1/medics",
						medic,
						Void.class);

        //Then
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(entity.getHeaders().getLocation()).hasPath("/api/v1/medics/1");
	}
}
