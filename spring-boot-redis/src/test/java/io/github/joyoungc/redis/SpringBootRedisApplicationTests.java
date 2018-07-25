package io.github.joyoungc.redis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootRedisApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		
		ResponseEntity<String> response = this.restTemplate.exchange("/session", HttpMethod.GET, null, String.class);
		
		log.info("## first response : {}", response.getBody());
		
		assertThat(response.getBody()).isNotBlank();
		
		HttpHeaders responseHeaders = response.getHeaders();
		List<String> cookies = responseHeaders.get(HttpHeaders.SET_COOKIE);
		
		log.info("## cookies : {}", cookies);
		
		if (!CollectionUtils.isEmpty(cookies)) {
			
			String sessionStr = Stream
					.of(cookies.stream().filter(x -> x.contains("SESSION=")).findFirst().map(s -> s.split(";")).get())
					.filter(x -> x.contains("SESSION=")).findFirst().orElse("");
			
			log.info("## {}", sessionStr);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.COOKIE, sessionStr);
			
			HttpEntity<?> requestEntity2 = new HttpEntity<>(headers);
			ResponseEntity<String> response2 = this.restTemplate.exchange("/session", HttpMethod.GET, requestEntity2, String.class);
			
			log.info("## second response : {}", response2.getBody());
			
			assertThat(response2.getBody()).isNotBlank();
		}
		
		
	}
	
	public void testSubString() {
		String t = "SESSION=NWZhMDE4YzctYzdlOS00N2I0LTkwY2ItNzA4OWIxNDU0MTE3; Path=/; HttpOnly";
	}

}
