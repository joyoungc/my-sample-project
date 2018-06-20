package io.github.joyoungc.batch.batch.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author joyoungc
 * @date 2018.06.19
 */
@Slf4j
@Component
public class UserChunkListener implements ChunkListener {

	private static final int LOGGING_INTERVAL = 100;

	@Override
	public void beforeChunk(ChunkContext context) {
	}

	@Override
	public void afterChunk(ChunkContext context) {
		int count = context.getStepContext().getStepExecution().getReadCount();
		// If the number of records processed so far is a multiple of the logging
		// interval then output a log message.
		if (count > 0 && count % LOGGING_INTERVAL == 0) {
			String fileName = context.getStepContext().getStepExecution().getJobParameters().getString("targetFile");
			log.info("[{}] {} items processed", fileName, count);
		}

	}

	@Override
	public void afterChunkError(ChunkContext context) {
	}

}
