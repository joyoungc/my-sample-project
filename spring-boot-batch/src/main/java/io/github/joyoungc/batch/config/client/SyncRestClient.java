package io.github.joyoungc.batch.config.client;

import java.util.Collections;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import io.github.joyoungc.batch.config.BatchProperties;
import io.github.joyoungc.batch.config.Constants;
import io.github.joyoungc.common.interceptor.RestClientLoggingInterceptor;
import io.github.joyoungc.common.model.User;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author joyoungc
 * @date 2018.06.25
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "rest.trans-type", havingValue = Constants.SYNC)
public class SyncRestClient implements RestClient<User, User> {

	private final RestTemplate restTemplate;

	public SyncRestClient(WebClient.Builder builder, BatchProperties prop) {
		SimpleClientHttpRequestFactory simpleFactory = new SimpleClientHttpRequestFactory();
		simpleFactory.setReadTimeout(prop.getRest().getReadTimeout());
		simpleFactory.setConnectTimeout(prop.getRest().getConnectTimeout());
		RestTemplate rest = new RestTemplate(
				new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		rest.setInterceptors(Collections.singletonList(new RestClientLoggingInterceptor()));
		rest.setUriTemplateHandler(new DefaultUriBuilderFactory(prop.getBatch().getTargetUrl()));
		this.restTemplate = rest;
	}

	@Override
	public User connection(User item, String url) {

		User user = null;

		try {
			ResponseEntity<User> response = restTemplate.postForEntity(url, item, User.class);
			user = response.getBody();
		} catch (ResourceAccessException e) {
			log.error("## ResourceAccessException : {}", e.getMessage());
		} catch (Exception e) {
			log.error("## Exception : {}", e.getMessage());
		}

		return user;
	}

}
