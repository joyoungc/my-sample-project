package io.github.joyoungc.batch.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author joyoungc
 * @date 2018.06.18
 */
@Slf4j
@Component
public class UserStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("## beforeStep ReadCount : {}", stepExecution.getReadCount());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("## afterStep ReadCount : {}", stepExecution.getReadCount());
        return null;
    }

}
