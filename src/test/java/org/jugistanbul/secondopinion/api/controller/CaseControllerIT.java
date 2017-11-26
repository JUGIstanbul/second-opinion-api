package org.jugistanbul.secondopinion.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.jugistanbul.secondopinion.api.RestHelper;
import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.entity.CaseStatus;
import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CaseControllerIT extends BaseIT {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Autowired
	CaseRepository caseRepository;

	@Test
	public void should_save_case() throws Exception {
		// given
		Case caseEntity = new Case();

		// when
		ResponseEntity entity = testRestTemplate.withBasicAuth("1", "1").postForEntity("/v1/cases", caseEntity,
				ResponseEntity.class);
		Long id = RestHelper.extractIdFromURI(entity.getHeaders().getLocation());

		Case theCase = caseRepository.findOne(id);

		// then
		assertThat(theCase).isNotNull();
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(entity.getHeaders().getLocation().toString()).isEqualTo("/api/v1/cases/" + id);
	}

	@Test
	public void should_get_case() throws Exception {
		// given
		Case caseEntity = new Case();
		caseEntity = caseRepository.save(caseEntity);

		// when
		ResponseEntity<Case> acase = testRestTemplate.withBasicAuth("1", "1")
				.getForEntity("/v1/cases/" + caseEntity.getId(), Case.class);

		// then
		assertThat(acase.getBody()).isNotNull();
	}

	@Test
	public void should_update_whole_case() throws Exception {
		// given
		Case caseEntity = new Case();
		caseEntity.setNickname("michael");
		caseEntity.setNote("superiz");
		caseEntity = caseRepository.save(caseEntity);
		// when
		caseEntity.setNote("hello world");
		testRestTemplate.withBasicAuth("1", "1").put("/v1/cases/" + caseEntity.getId(), caseEntity);

		// then
		caseEntity = caseRepository.findOne(caseEntity.getId());
		assertThat(caseEntity.getNote()).isEqualTo("hello world");
	}

	@Test
	public void should_delete_case() {

		Case caseEntity = new Case();

		caseEntity = caseRepository.save(caseEntity);

		// when

		ResponseEntity entity = testRestTemplate.withBasicAuth("1", "1").exchange("/v1/cases/" + caseEntity.getId(),
				HttpMethod.DELETE, null, ResponseEntity.class);

		assertThat(entity).isNotNull();
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

		Case theCase = caseRepository.findOne(caseEntity.getId());

		assertThat(theCase).isNotNull();
		assertThat(theCase.getModelStatus()).isEqualTo(ModelStatus.DELETED);
	}

	@Test
	public void ShouldGetUnclosedCases() {
		Case caseEntity = new Case();
		caseEntity.setStatus(CaseStatus.NEW);
		caseRepository.save(caseEntity);
		Case caseEntity2 = new Case();
		caseEntity2.setStatus(CaseStatus.OPEN);
		caseRepository.save(caseEntity2);
		Case caseEntity3 = new Case();
		caseEntity3.setStatus(CaseStatus.CLOSED);
		caseRepository.save(caseEntity3);

		Case[] unclosedCases = testRestTemplate.withBasicAuth("1", "1").getForObject("/v1/cases/unclosed",
				Case[].class);

		assertThat(unclosedCases).isNotNull();
		
		List<Case> unclosedCasesList = Arrays.asList(unclosedCases);
		for (Case case1 : unclosedCasesList) {
			assertThat(case1.getStatus()).isNotEqualTo(CaseStatus.CLOSED);
		}
	}
}
