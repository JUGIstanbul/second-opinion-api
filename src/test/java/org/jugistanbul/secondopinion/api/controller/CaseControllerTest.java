package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Case;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class CaseControllerTest extends BaseIT {

    @Autowired
    TestRestTemplate testRestTemplate;


    @Test
    public void should_save_case() throws Exception {
        //given
        Case caseEntity = new Case();
        caseEntity.setNote("Lorem ipsum dolor sit amet.");


        //when
        ResponseEntity entity = testRestTemplate.withBasicAuth("1","1").postForEntity("/v1/cases", caseEntity, ResponseEntity.class);

        //then
        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
