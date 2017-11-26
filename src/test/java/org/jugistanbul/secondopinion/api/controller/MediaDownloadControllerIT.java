package org.jugistanbul.secondopinion.api.controller;

import org.hamcrest.CoreMatchers;
import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Media;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

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
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        ResponseEntity<Media> result = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/media", HttpMethod.POST, requestEntity, Media.class);
        // then

        RestTemplate restTemplate = getRawRestTemplate();

        ResponseEntity<byte[]> responseEntity = restTemplate
                .exchange(result.getBody().getUrl(), HttpMethod.GET, HttpEntity.EMPTY, byte[].class);

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertTrue(Arrays.equals(responseEntity.getBody(), Files.readAllBytes(Paths.get(dukeResource.getURI()))));

    }

    private RestTemplate getRawRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(
                new ByteArrayHttpMessageConverter());
        restTemplate.setRequestFactory(new InterceptingClientHttpRequestFactory(restTemplate.getRequestFactory(), Collections.singletonList(new BasicAuthorizationInterceptor("1", "1"))));
        return restTemplate;
    }
}
