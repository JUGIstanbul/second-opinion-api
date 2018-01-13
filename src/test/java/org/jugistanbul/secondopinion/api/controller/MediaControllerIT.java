package org.jugistanbul.secondopinion.api.controller;

import org.assertj.core.api.Assertions;
import org.jugistanbul.secondopinion.api.RestHelper;
import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Media;
import org.jugistanbul.secondopinion.api.repository.MediaRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jugistanbul.secondopinion.api.RestHelper.extractIdFromURI;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class MediaControllerIT extends BaseIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MediaRepository mediaRepository;

    @Test
    public void should_post_media() throws Exception {
        // given
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", new ClassPathResource("Duke-Istanbul.png"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // when
        ResponseEntity<Media> result = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange("/v1/media",
                        HttpMethod.POST,
                        new HttpEntity<>(map, headers),
                        Media.class);
        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(extractIdFromURI(result.getHeaders().getLocation())).isNotNull();
        assertThat(result.getBody()).isNotNull();
    }

    @Test
    public void should_get_media() throws Exception {
        // given
        Media entity = new Media();
        entity.setFileName("/tmp/x");
        entity.setType("txt/plain");
        entity.setUrl("about:blank");
        entity = mediaRepository.save(entity);

        // when
        ResponseEntity<Media> responseEntity = testRestTemplate
                .withBasicAuth("1", "1")
                .getForEntity("/v1/media/" + entity.getId(),
                        Media.class);

        // then
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getFileName()).isEqualTo("/tmp/x");
    }


    @Test
    public void should_delete_media() throws Exception {
        // given
        Media entity = new Media();
        entity.setFileName("/tmp/x");
        entity.setType("txt/plain");
        entity.setUrl("about:blank");

        entity = mediaRepository.save(entity);

        // when
        ResponseEntity<Void> responseEntity = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange("/v1/media/" + entity.getId(),
                        HttpMethod.DELETE,
                        HttpEntity.EMPTY,
                        Void.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
