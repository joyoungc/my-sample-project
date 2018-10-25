package io.github.joyoungc.web.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.joyoungc.web.model.ContentTypeExample;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExceptionSampleControllerTest {
	
	@Autowired
	TestRestTemplate restTemplate;
	
	private static ContentTypeExample content;
	
	@BeforeClass
	public static void setUp() {
		content = new ContentTypeExample();
		//content.setId(1L);
		//content.setTitle("제목");
		content.setContent("내용");
	}
	
	@Test
	public void testPostJsonException() {
		
		HttpEntity<ContentTypeExample> requestEntity = new HttpEntity<>(content);
		ResponseEntity<Object> response = restTemplate.exchange("/exception/post", HttpMethod.POST,
				requestEntity, Object.class);
		
		log.error("## response : {}", response.getBody());
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	// @Test
	public void tesGetParamsException() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("x-Trace-Id", "#@%DFASDFSAF");
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Object> response = restTemplate.exchange("/exception/get?key=키&value=값", HttpMethod.GET,
				requestEntity, Object.class);
		
		log.error("## response : {}", response.getBody());
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	// @Test
	public void tesGetNoParamsException() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("x-Trace-Id", "#@%DFASDFSAF");
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Object> response = restTemplate.exchange("/exception/params?key=키&value=값", HttpMethod.GET,
				requestEntity, Object.class);

		log.error("## response : {}", response.getBody());
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

	}

}

