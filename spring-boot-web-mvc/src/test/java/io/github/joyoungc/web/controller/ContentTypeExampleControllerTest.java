package io.github.joyoungc.web.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import io.github.joyoungc.web.model.ContentTypeExample;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ContentTypeExampleControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    private ContentTypeExample content;

    @Before
    public void setUp() {
        content = new ContentTypeExample();
        content.setId(1L);
        content.setTitle("제목");
        content.setContent("내용");
    }

    @Test
    public void testPostFormModel() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", String.valueOf(content.getId()));
        map.add("title", content.getTitle());
        map.add("content", content.getContent());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);
        ResponseEntity<ContentTypeExample> response = restTemplate.exchange("/content/form/model", HttpMethod.POST,
                requestEntity, ContentTypeExample.class);

        assertThat(response).isNotNull();
        assertThat(response.getBody()).hasFieldOrPropertyWithValue("title", content.getTitle());

    }


    @Test
    public void testPostFormMap() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", String.valueOf(content.getId()));
        map.add("title", content.getTitle());
        map.add("content", content.getContent());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);

        ParameterizedTypeReference<Map<String, Object>> responseType =
                new ParameterizedTypeReference<Map<String, Object>>() {
                };

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange("/content/form/map?test=00",
                HttpMethod.POST,
                requestEntity, responseType);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("title")).isNotNull().isEqualTo(content.getTitle());
        assertThat(response.getBody().get("test")).isNotNull().isEqualTo("00");

    }

    @Test
    public void testPostJsonModel() {

        HttpEntity<ContentTypeExample> requestEntity = new HttpEntity<>(content);
        ResponseEntity<ContentTypeExample> response = restTemplate.exchange("/content/json/model", HttpMethod.POST,
                requestEntity, ContentTypeExample.class);

        assertThat(response).isNotNull();
        assertThat(response.getBody()).hasFieldOrPropertyWithValue("title", content.getTitle());

    }


    @Test
    public void testPostJsonMap() {

        HttpEntity<ContentTypeExample> requestEntity = new HttpEntity<>(content);

        ParameterizedTypeReference<Map<String, Object>> responseType =
                new ParameterizedTypeReference<Map<String, Object>>() {
                };

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange("/content/json/map", HttpMethod.POST,
                requestEntity, responseType);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("title")).isNotNull().isEqualTo(content.getTitle());

    }


}

