package io.github.joyoungc.batch.config.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.joyoungc.batch.config.BatchProperties;
import io.github.joyoungc.batch.config.Constants;
import io.github.joyoungc.common.model.User;
import reactor.core.publisher.Mono;

/**
 *
 * @author joyoungc
 * @date 2018.06.25
 */
@Component
@ConditionalOnProperty(name = "rest.trans-type", havingValue = Constants.ASYNC)
public class AsyncRestClient implements RestClient<User, User> {

	private final WebClient webClient;

	public AsyncRestClient(WebClient.Builder builder, BatchProperties prop) {
		this.webClient = builder.baseUrl(prop.getBatch().getTargetUrl()).build();
	}

	@Override
	public User connection(User item, String url) {
		webClient.post().uri(url).contentType(MediaType.APPLICATION_JSON).body(Mono.just(item), User.class)
		.retrieve().bodyToMono(Void.class).subscribe();
		return null;
	}

}
