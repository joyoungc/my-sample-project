package io.github.joyoungc.common.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author joyoungc
 * @date 2018.06.08
 */
@Slf4j
public class RestClientLoggingInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
			final ClientHttpRequestExecution execution) throws IOException {
		traceRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		traceResponse(response);
		return response;
	}

	private void traceRequest(final HttpRequest request, final byte[] body) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("=========================== request begin ================================================");
			log.debug("URI         : {}", request.getURI());
			log.debug("Method      : {}", request.getMethod());
			log.debug("Headers     : {}", request.getHeaders());
			log.debug("Request body: {}", new String(body, Charset.defaultCharset()));
			log.debug("========================== request end ================================================");
		}
	}

	private void traceResponse(final ClientHttpResponse response) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("============================ response begin ==========================================");
			log.debug("Status code  : {}", response.getStatusCode());
			log.debug("Status text  : {}", response.getStatusText());
			log.debug("Headers      : {}", response.getHeaders());
			log.debug("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
			log.debug("=======================response end=================================================");
		}
	}

}
