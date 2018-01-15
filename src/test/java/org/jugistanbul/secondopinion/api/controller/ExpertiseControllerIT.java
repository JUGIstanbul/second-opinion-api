package org.jugistanbul.secondopinion.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Expertise;
import org.jugistanbul.secondopinion.api.repository.ExpertiseRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ExpertiseControllerIT extends BaseIT {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    ExpertiseRepository expertiseRepository;

    @Test
    public void should_list_all_expertise() {
        //given
        Expertise expertise = new Expertise();
        expertise.setName("Oncology");
        expertiseRepository.save(expertise);

        //when
        ResponseEntity<List> entity = testRestTemplate
                .withBasicAuth("1", "1")
                .getForEntity("/v1/expertise",
                        List.class);

        //then
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().size()).isEqualTo(1);
    }
}
