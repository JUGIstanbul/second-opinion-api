package org.jugistanbul.secondopinion.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.City;
import org.jugistanbul.secondopinion.api.repository.CityRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CityControllerIT extends BaseIT{
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    CityRepository cityRepository;

    @Test
    public void should_list_all_cities() {
        //given
        City city = new City();
        city.setName("Amsterdam");
        cityRepository.save(city);

        //when
        ResponseEntity<List> entity = testRestTemplate
                .withBasicAuth("1", "1")
                .getForEntity("/v1/cities",
                        List.class);

        //then
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().size()).isEqualTo(1);
    }
}
