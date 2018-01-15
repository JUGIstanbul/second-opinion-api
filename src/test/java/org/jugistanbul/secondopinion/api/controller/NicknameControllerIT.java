package org.jugistanbul.secondopinion.api.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Nickname;
import org.jugistanbul.secondopinion.api.entity.NicknameCache;
import org.jugistanbul.secondopinion.api.repository.NicknameRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NicknameControllerIT extends BaseIT {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    NicknameRepository nicknameRepository;

    private List<Nickname> nicknames;

    @Before
    public void fillNicknames() {
        nicknames = new ArrayList<>();
        nicknames.add(new Nickname("taylan"));
        nicknames.add(new Nickname("michael"));
        nicknames.add(new Nickname("jetty"));
        nicknames.add(new Nickname("gokalp"));
        nicknames.add(new Nickname("hudson"));
    }

    @After
    public void emptyNicknames() {
        nicknameRepository.deleteAll();
        NicknameCache.INSTANCE.clear();
    }

    @Test
    public void should_suggest_a_nickname_from_predefined_list() {
        // Given
        nicknameRepository.save(nicknames);

        // When
        ResponseEntity<Nickname> responseEntity = restTemplate
                .withBasicAuth("1","1")
                .getForEntity("/v1/nicknames",
                        Nickname.class);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isIn(nicknames);
    }

    @Test
    public void should_return_no_content() {
        // Given

        // When
        ResponseEntity<Nickname> responseEntity = restTemplate
                .withBasicAuth("1","1")
                .getForEntity("/v1/nicknames",
                        Nickname.class);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(responseEntity.getBody()).isNull();
    }
}
