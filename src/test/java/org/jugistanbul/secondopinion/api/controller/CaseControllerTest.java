package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CaseControllerTest extends BaseIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    CaseRepository caseRepository;

    @Test
    public void should_save_case() throws Exception {
        //given
        Case caseEntity = new Case();

        //when
        ResponseEntity entity = testRestTemplate.withBasicAuth("1", "1").postForEntity("/v1/cases", caseEntity, ResponseEntity.class);

        List<Case> caseList = caseRepository.findAll();

        //then
        assertThat(caseList.size()).isEqualTo(1);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(entity.getHeaders().get(HttpHeaders.LOCATION).get(0)).isEqualTo("/api/v1/cases/1");
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
    public void should_update_whole_case() throws Exception {
        //given
        Case caseEntity = new Case();
        caseEntity.setNickname("michael");
        caseEntity.setNote("superiz");
        caseEntity = caseRepository.save(caseEntity);
        //when
        caseEntity.setNote("hello world");
        testRestTemplate.withBasicAuth("1", "1").put("/v1/cases/" + caseEntity.getId(), caseEntity);

        //then
        caseEntity = caseRepository.findOne(caseEntity.getId());
        assertThat(caseEntity.getNote()).isEqualTo("hello world");
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

        List<Case> caseList = caseRepository.findAll();

        assertThat(caseList.size()).isEqualTo(1);
        assertThat(caseList.get(0).getModelStatus()).isEqualTo(ModelStatus.DELETED);
    }
}
