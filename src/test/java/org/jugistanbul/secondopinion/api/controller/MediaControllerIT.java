package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.RestHelper;
import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Media;
import org.jugistanbul.secondopinion.api.repository.MediaRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

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
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        ResponseEntity<Media> result = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/media", HttpMethod.POST, requestEntity, Media.class);
        // then

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(RestHelper.extractIdFromURI(result.getHeaders().getLocation()), notNullValue());
        assertThat(result.getBody(), notNullValue());

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
        ResponseEntity<Media> responseEntity = testRestTemplate.withBasicAuth("1", "1")
                .getForEntity("/v1/media/" + entity.getId(), Media.class);

        // then
        assertThat(responseEntity.getBody(), notNullValue());
        assertThat(responseEntity.getBody().getFileName(), equalTo("/tmp/x"));
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
        ResponseEntity<Void> responseEntity = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/media/" + entity.getId(), HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);

        // then
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
    }
}
