package io.github.joyoungc.batch.batch.item;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.github.joyoungc.common.model.User;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author joyoungc
 * @date 2018.06.15
 */
@Slf4j
public class UserItemProcessor implements ItemProcessor<User, User> {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public User process(User item) throws Exception {
		log.debug("## item in : {}", item);

		User user = null;

		try {
			ResponseEntity<User> response = restTemplate.postForEntity("/test-login", item, User.class);
			user = response.getBody();
		} catch (Exception e) {
			log.debug("## error : {}", e.getMessage());
		}

		return user;
	}

}
