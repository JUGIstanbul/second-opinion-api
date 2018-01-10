package org.jugistanbul.secondopinion.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.jugistanbul.secondopinion.api.RestHelper;
import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.entity.Treatment;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
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
        ResponseEntity<Case> entity = testRestTemplate.withBasicAuth("1", "1").postForEntity("/v1/cases", caseEntity, Case.class);
        Long id = RestHelper.extractIdFromURI(entity.getHeaders().getLocation());

        Case theCase = caseRepository.findOne(id);

        //then
        assertThat(theCase).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(entity.getHeaders().getLocation().toString()).isEqualTo("/api/v1/cases/" + id);
        assertThat(theCase.getBodyPartsAffected().equals("Yanlarım"));
        assertThat(theCase.getPrimaryComplaint().equals("Yanlarım agrıyor"));
        assertThat(theCase.getSymptoms().equals("Bulantı"));
        assertThat(theCase.getTreatments().equals(pastTreatmentList));

    }

    @Test
    public void should_post_return_406_for_invalid_case() throws Exception{

        // given
        Case caseEntity= new Case();

        // when
        ResponseEntity<Void> responseEntity = testRestTemplate.withBasicAuth("1", "1").postForEntity("/v1/cases", caseEntity, Void.class);

        // then
        MatcherAssert.assertThat(responseEntity.getStatusCode(), Matchers.equalTo(HttpStatus.NOT_ACCEPTABLE));


    }



    @Test
    public void should_get_case() throws Exception {
        //given
        Case caseEntity = new Case();
        caseEntity = caseRepository.save(caseEntity);

        //when
        ResponseEntity<Case> acase = testRestTemplate.withBasicAuth("1", "1").getForEntity("/v1/cases/" + caseEntity.getId(), Case.class);

        //then
        assertThat(acase.getBody()).isNotNull();
    }

    @Test
    public void should_get_case_return_404() throws Exception {
        //given


        //when
        ResponseEntity<Void> responseEntity = testRestTemplate.withBasicAuth("1", "1").getForEntity("/v1/cases/1234567", Void.class);

        //then
        MatcherAssert.assertThat(responseEntity.getStatusCode(), Matchers.equalTo(HttpStatus.NOT_FOUND));
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
        ResponseEntity<Void> putResponse = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/cases/" + caseEntity.getId(), HttpMethod.PUT, new HttpEntity<>(caseEntity), Void.class);

        MatcherAssert.assertThat(putResponse.getStatusCode(), Matchers.equalTo(HttpStatus.OK));
        //then
        caseEntity = caseRepository.findOne(caseEntity.getId());
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
        caseEntity=caseRepository.save(caseEntity);

        Case savedCase = caseRepository.findOne(caseEntity.getId());

        // when
        ResponseEntity<Void> putResponse = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/cases/12345678", HttpMethod.PUT, new HttpEntity<>(savedCase), Void.class);

        // then
        MatcherAssert.assertThat(putResponse.getStatusCode(), Matchers.equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    public void should_put_return_406_for_Id() throws Exception {
        // given
        Case caseEntity = new Case();
        caseEntity.setIllnessStartDate(LocalDate.MAX);
        caseEntity.setNickname("R2-D2");
        caseEntity.setSymptoms("Lorem ipsum dolor sit amet");
        caseEntity.setNote("superiz");
        caseEntity=caseRepository.save(caseEntity);

        Case savedCase = caseRepository.findOne(caseEntity.getId());
        savedCase.setId(null);

        // when
        ResponseEntity<Void> putResponse = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/cases/" + caseEntity.getId(), HttpMethod.PUT, new HttpEntity<>(savedCase), Void.class);

        // then
        MatcherAssert.assertThat(putResponse.getStatusCode(), Matchers.equalTo(HttpStatus.NOT_ACCEPTABLE));

    }

    @Test
    public void should_put_return_406_for_symptoms() throws Exception {
        // given

        Case caseEntity = new Case();
        caseEntity.setIllnessStartDate(LocalDate.MAX);
        caseEntity.setNickname("R2-D2");
        caseEntity.setSymptoms("Lorem ipsum dolor sit amet");
        caseEntity.setNote("superiz");
        caseEntity=caseRepository.save(caseEntity);

        Case savedCase = caseRepository.findOne(caseEntity.getId());
        savedCase.setSymptoms(null);

        // when
        ResponseEntity<Void> putResponse = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/cases/" + caseEntity.getId(), HttpMethod.PUT, new HttpEntity<>(savedCase), Void.class);

        // then
        MatcherAssert.assertThat(putResponse.getStatusCode(), Matchers.equalTo(HttpStatus.NOT_ACCEPTABLE));
    }
    @Test
    public void should_put_return_406_for_illnessStartDate() throws Exception {

        // given
        Case caseEntity = new Case();
        caseEntity.setIllnessStartDate(LocalDate.MAX);
        caseEntity.setNickname("R2-D2");
        caseEntity.setSymptoms("Lorem ipsum dolor sit amet");
        caseEntity.setNote("superiz");
        caseEntity=caseRepository.save(caseEntity);

        Case savedCase = caseRepository.findOne(caseEntity.getId());

        // given
        savedCase.setIllnessStartDate(null);

        // when
        ResponseEntity<Void> putResponse = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/cases/" + caseEntity.getId(), HttpMethod.PUT, new HttpEntity<>(savedCase), Void.class);

        // then
        MatcherAssert.assertThat(putResponse.getStatusCode(), Matchers.equalTo(HttpStatus.NOT_ACCEPTABLE));





    }

    @Test
    public void should_put_return_406_for_invalid_nickname() throws Exception {
        // given
        Case caseEntity = new Case();
        caseEntity.setIllnessStartDate(LocalDate.MAX);
        caseEntity.setNickname("R2-D2");
        caseEntity.setSymptoms("Lorem ipsum dolor sit amet");
        caseEntity.setNote("superiz");
        caseEntity=caseRepository.save(caseEntity);

        Case savedCase = caseRepository.findOne(caseEntity.getId());
        savedCase.setNickname(null);

        // when
        ResponseEntity<Void> putResponse = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/cases/" + caseEntity.getId(), HttpMethod.PUT, new HttpEntity<>(savedCase), Void.class);

        // then
        MatcherAssert.assertThat(putResponse.getStatusCode(), Matchers.equalTo(HttpStatus.NOT_ACCEPTABLE));
    }


    @Test
    public void should_delete_case() {

        Case caseEntity = new Case();

        caseEntity = caseRepository.save(caseEntity);

        //when

        ResponseEntity entity = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/cases/" + caseEntity.getId(), HttpMethod.DELETE, null, ResponseEntity.class);

        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Case theCase = caseRepository.findOne(caseEntity.getId());

        assertThat(theCase).isNotNull();
        assertThat(theCase.getModelStatus()).isEqualTo(ModelStatus.DELETED);
    }

    @Test
    public void should_delete_return_404_for_non_existing_case() throws Exception {

        //given

        // when
        ResponseEntity<Void> deleteCase = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/cases/12345678", HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);

        // then
        MatcherAssert.assertThat(deleteCase.getStatusCode(), Matchers.equalTo(HttpStatus.NOT_FOUND));

    }
}
