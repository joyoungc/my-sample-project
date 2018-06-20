package io.github.joyoungc.batch.config;

import java.util.Collections;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import io.github.joyoungc.common.interceptor.RestClientLoggingInterceptor;

/**
 *
 * @author BD 정조영
 * @date 2018.06.15
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private BatchProperties prop;

	@Bean(name = Constants.STEP_TASK_EXECUTOR)
	public TaskExecutor batchExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(prop.getBatch().getCoreTaskPoolSize());
		executor.setMaxPoolSize(prop.getBatch().getMaxTaskPoolSize());
		return executor;
	}

	@Bean(name = Constants.JOB_TASK_EXECUTOR)
	public TaskExecutor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(30);
		return executor;
	}

	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory simpleFactory = new SimpleClientHttpRequestFactory();
		simpleFactory.setReadTimeout(prop.getRest().getReadTimeout());
		simpleFactory.setConnectTimeout(prop.getRest().getConnectTimeout());
		RestTemplate rest = new RestTemplate(
				new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		rest.setInterceptors(Collections.singletonList(new RestClientLoggingInterceptor()));
		rest.setUriTemplateHandler(new DefaultUriBuilderFactory(prop.getBatch().getTargetUrl()));
		return rest;
	}

}
