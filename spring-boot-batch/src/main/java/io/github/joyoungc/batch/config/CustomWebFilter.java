package io.github.joyoungc.batch.config;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

/**
 *
 * @author joyoungc
 * @date 2018.06.22
 */
@Component
public class CustomWebFilter implements WebFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		if (exchange.getRequest().getURI().getPath().equals("/")) {
			return chain.filter(
					exchange.mutate().request(exchange.getRequest().mutate().path("/index.html").build()).build());
		}
		return chain.filter(exchange);
	}

}
