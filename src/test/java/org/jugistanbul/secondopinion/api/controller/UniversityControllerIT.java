package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.University;
import org.jugistanbul.secondopinion.api.repository.UniversityRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class UniversityControllerIT extends BaseIT {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UniversityRepository universityRepository;

    @Test
    public void should_list_all_hospitals() {
        //given
        University university = new University();
        university.setName("ITU");
        universityRepository.save(university);

        //when
        ResponseEntity<List> entity = testRestTemplate.withBasicAuth("1", "1").getForEntity("/v1/universities", List.class);

        //then
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().size()).isEqualTo(1);
    }
}
