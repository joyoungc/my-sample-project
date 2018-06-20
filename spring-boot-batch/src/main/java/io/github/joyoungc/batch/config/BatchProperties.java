package io.github.joyoungc.batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author joyoungc
 * @date 2018.06.18
 */
@Getter @Setter
@Component
@ConfigurationProperties
public class BatchProperties {

	private Batch batch = new Batch();
	private Rest rest = new Rest();

	@Getter @Setter
	public static class Batch {
		private String targetUrl;
		private int coreTaskPoolSize;
		private int maxTaskPoolSize;
		private int chunkSize;
	}

	@Getter @Setter
	public static class Rest {
		private int readTimeout;
		private int connectTimeout;
	}
}
