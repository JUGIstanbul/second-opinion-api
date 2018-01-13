package org.jugistanbul.secondopinion.api.controller;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.jugistanbul.secondopinion.api.RestHelper;
import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.jugistanbul.secondopinion.api.entity.Treatment;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.jugistanbul.secondopinion.api.repository.PatientRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CaseControllerIT extends BaseIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    PatientRepository patientRepository;

    public Case createTestCase(Patient patient)
    {
        Case caseEntity = new Case();
        caseEntity.setIllnessStartDate(LocalDate.MAX);
        caseEntity.setNickname("R2-D2");
        caseEntity.setSymptoms("Lorem ipsum dolor sit amet");
        caseEntity.setPatient(patient);
        return caseEntity;
    }

    @Test
    public void should_save_case() throws Exception {
        //given
        Case caseEntity = new Case();
        caseEntity.setIllnessStartDate(LocalDate.MAX);
        caseEntity.setNickname("R2-D2");
        caseEntity.setSymptoms("Lorem ipsum dolor sit amet");
        caseEntity.setBodyPartsAffected("Yanlarım");
        caseEntity.setPrimaryComplaint("Yanlarım agrıyor");
        caseEntity.setSymptoms("Bulantı");
        Treatment treatment = new Treatment();
        treatment.setDescription("Fizik tedavi");
        Set<Treatment> pastTreatmentList = new HashSet<>(Arrays.asList(treatment));
        caseEntity.setTreatments(pastTreatmentList);
        caseEntity = caseRepository.save(caseEntity);

        //when
        ResponseEntity<Case> entity = testRestTemplate
                .withBasicAuth("1", "1")
                .postForEntity("/v1/cases",
                        caseEntity,
                        Case.class);

        Long id = RestHelper.extractIdFromURI(entity.getHeaders().getLocation());
        Case theCase = caseRepository.findOne(id);

        //then
        assertThat(theCase).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(entity.getHeaders().getLocation().toString()).isEqualTo("/api/v1/cases/" + id);
        assertThat(theCase.getBodyPartsAffected()).isEqualTo("Yanlarım");
        assertThat(theCase.getPrimaryComplaint()).isEqualTo("Yanlarım agrıyor");
        assertThat(theCase.getSymptoms()).isEqualTo("Bulantı");
        assertThat(theCase.getTreatments()).isEqualTo(pastTreatmentList);
    }

    @Test
    public void should_return_cases_as_empty_list_if_patient_not_have() {
        //Given
        Patient patient = new Patient();
        patient = patientRepository.save(patient);

        //When
        ResponseEntity<List<Case>> response = testRestTemplate
                .withBasicAuth("1","1").
                exchange("/v1/cases?patientId="+patient.getId(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Case>>() {
                        });
        List<Case> cases = response.getBody();

        //Then
        assertThat(cases).isNotNull();
        assertThat(cases).isEmpty();
    }

    @Test
    public void should_return_cases_as_empty_list_if_patient_not_exist() {
        //Given
        long patientId = 123123;

        //When
        ResponseEntity<List<Case>> response = testRestTemplate.
                withBasicAuth("1","1").
                exchange("/v1/cases?patientId="+patientId,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Case>>() {
                        });
        List<Case> cases = response.getBody();

        //Then
        assertThat(cases).isNotNull();
        assertThat(cases).isEmpty();
    }

    @Test
    public void should_return_cases_of_patient() {
        //Given
        Patient patient = new Patient();
        patient = patientRepository.save(patient);

        Case caseEntityı = caseRepository.save(createTestCase(patient));
        Case caseEntity2 = caseRepository.save(createTestCase(patient));
        List<Case> caseEntityList=new ArrayList<>();
        caseEntityList.add(caseEntityı);
        caseEntityList.add(caseEntity2);
        caseEntityList=caseRepository.save(caseEntityList);

        //When
        ResponseEntity<List<Case>> response = testRestTemplate
                .withBasicAuth("1","1").
                exchange("/v1/cases?patientId="+patient.getId(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Case>>() {
                        });
        List<Case> cases = response.getBody();

        //Then
        assertThat(cases).isNotNull();
        assertThat(cases).isNotEmpty();
        assertThat(cases.size()).isEqualTo(2);
        assertThat(cases.get(0).getId()).isEqualTo(caseEntityList.get(0).getId());
        assertThat(cases.get(1).getId()).isEqualTo(caseEntityList.get(1).getId());
    }

    @Test
    public void should_post_return_406_for_invalid_case() throws Exception{
        // given
        Case caseEntity= new Case();

        // when
        ResponseEntity<Void> responseEntity = testRestTemplate
                .withBasicAuth("1", "1")
                .postForEntity("/v1/cases",
                        caseEntity,
                        Void.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void should_get_case() throws Exception {
        //given
        Case caseEntity = new Case();
        caseEntity = caseRepository.save(caseEntity);

        //when
        ResponseEntity<Case> acase = testRestTemplate
                .withBasicAuth("1", "1")
                .getForEntity("/v1/cases/" + caseEntity.getId(),
                        Case.class);

        //then
        assertThat(acase.getBody()).isNotNull();
    }

    @Test
    public void should_get_case_return_404() throws Exception {
        //Given

        //When
        ResponseEntity<Void> responseEntity = testRestTemplate
                .withBasicAuth("1", "1")
                .getForEntity("/v1/cases/1234567", Void.class);

        //Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void should_update_whole_case() throws Exception {
        //given
        Case caseEntity = new Case();
        caseEntity.setIllnessStartDate(LocalDate.MAX);
        caseEntity.setNickname("R2-D2");
        caseEntity.setSymptoms("Lorem ipsum dolor sit amet");
        caseEntity.setNote("superiz");
        caseEntity=caseRepository.save(caseEntity);

        //when
        caseEntity.setNote("hello world");
        ResponseEntity<Void> putResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange("/v1/cases/" + caseEntity.getId(),
                        HttpMethod.PUT,
                        new HttpEntity<>(caseEntity),
                        Void.class);

        caseEntity = caseRepository.findOne(caseEntity.getId());

        //then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(caseEntity.getNote()).isEqualTo("hello world");
    }
    @Test
    public void should_put_return_404_for_nonexisting_case() throws Exception {

        // given
        Case caseEntity = new Case();
        caseEntity.setIllnessStartDate(LocalDate.MAX);
        caseEntity.setNickname("R2-D2");
        caseEntity.setSymptoms("Lorem ipsum dolor sit amet");
        caseEntity.setNote("superiz");
        Case persistedCase = caseRepository.save(caseEntity);

        // when
        ResponseEntity<Void> putResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange("/v1/cases/12345678",
                        HttpMethod.PUT,
                        new HttpEntity<>(persistedCase),
                        Void.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void should_put_return_406_when_case_not_have_an_id() throws Exception {
        // given
        Case caseEntity = new Case();
        caseEntity.setIllnessStartDate(LocalDate.MAX);
        caseEntity.setNickname("R2-D2");
        caseEntity.setSymptoms("Lorem ipsum dolor sit amet");
        caseEntity.setNote("superiz");
        Case persistedCase = caseRepository.save(caseEntity);
        Long caseId = persistedCase.getId();

        Case updatedCaseOnClientSide = persistedCase;
        updatedCaseOnClientSide.setId(null);

        // when
        ResponseEntity<Void> putResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange("/v1/cases/" + caseId,
                        HttpMethod.PUT,
                        new HttpEntity<>(updatedCaseOnClientSide),
                        Void.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void should_put_return_406_when_symptoms_no_set() throws Exception {
        // given
        Case caseEntity = new Case();
        caseEntity.setIllnessStartDate(LocalDate.MAX);
        caseEntity.setNickname("R2-D2");
        caseEntity.setSymptoms("Lorem ipsum dolor sit amet");
        caseEntity.setNote("superiz");
        Case persistedCase = caseRepository.save(caseEntity);
        Long caseId = persistedCase.getId();

        Case updatedCaseOnClientSide = persistedCase;
        updatedCaseOnClientSide.setSymptoms(null);

        // when
        ResponseEntity<Void> putResponse = testRestTemplate.
                withBasicAuth("1", "1")
                .exchange("/v1/cases/" + caseId,
                        HttpMethod.PUT,
                        new HttpEntity<>(updatedCaseOnClientSide),
                        Void.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void should_put_return_406_when_illnessStartDate_not_set() throws Exception {
        // given
        Case caseEntity = new Case();
        caseEntity.setIllnessStartDate(LocalDate.MAX);
        caseEntity.setNickname("R2-D2");
        caseEntity.setSymptoms("Lorem ipsum dolor sit amet");
        caseEntity.setNote("superiz");
        Case persistedCase = caseRepository.save(caseEntity);
        Long caseId = persistedCase.getId();

        Case updatedCaseOnClientSide = persistedCase;
        updatedCaseOnClientSide.setIllnessStartDate(null);

        // when
        ResponseEntity<Void> putResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange("/v1/cases/" + caseId,
                        HttpMethod.PUT,
                        new HttpEntity<>(updatedCaseOnClientSide),
                        Void.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void should_put_return_406_when_nickname_not_set() throws Exception {
        // given
        Case caseEntity = new Case();
        caseEntity.setIllnessStartDate(LocalDate.MAX);
        caseEntity.setNickname("R2-D2");
        caseEntity.setSymptoms("Lorem ipsum dolor sit amet");
        caseEntity.setNote("superiz");
        Case persistedCase = caseRepository.save(caseEntity);
        Long caseId = persistedCase.getId();

        Case updatedCaseOnClientSide = persistedCase;
        updatedCaseOnClientSide.setNickname(null);

        // when
        ResponseEntity<Void> putResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange("/v1/cases/" + caseEntity.getId(),
                        HttpMethod.PUT,
                        new HttpEntity<>(updatedCaseOnClientSide),
                        Void.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void should_delete_case() {
        // given
        Case caseEntity = new Case();
        caseEntity = caseRepository.save(caseEntity);

        //when
        ResponseEntity entity = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange("/v1/cases/" + caseEntity.getId(),
                        HttpMethod.DELETE,
                        null,
                        ResponseEntity.class);

        //then
        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Case theCase = caseRepository.findOne(caseEntity.getId());
        assertThat(theCase).isNotNull();
        assertThat(theCase.getModelStatus()).isEqualTo(ModelStatus.DELETED);
    }

    @Test
    public void should_delete_return_404_for_non_existing_case() throws Exception {
        //given

        //when
        ResponseEntity<Void> deleteCase = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange("/v1/cases/12345678",
                        HttpMethod.DELETE,
                        HttpEntity.EMPTY,
                        Void.class);

        //then
        assertThat(deleteCase.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
