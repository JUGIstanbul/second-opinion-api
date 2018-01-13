package org.jugistanbul.secondopinion.api.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Media;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class MediaDownloadControllerIT extends BaseIT {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void should_return_duke() throws Exception {
        // given
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        ClassPathResource dukeResource = new ClassPathResource("Duke-Istanbul.png");
        map.add("file", dukeResource);
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
        RestTemplate restTemplate = getRawRestTemplate();

        ResponseEntity<byte[]> responseEntity = restTemplate
                .exchange(result.getBody().getUrl(),
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        byte[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(readAllBytes(get(dukeResource.getURI())));

    }

    private RestTemplate getRawRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(
                new ByteArrayHttpMessageConverter());

        restTemplate.setRequestFactory(
                new InterceptingClientHttpRequestFactory(
                        restTemplate.getRequestFactory(),
                        Collections.singletonList(
                                new BasicAuthorizationInterceptor("1", "1"))));
        return restTemplate;
    }
}
