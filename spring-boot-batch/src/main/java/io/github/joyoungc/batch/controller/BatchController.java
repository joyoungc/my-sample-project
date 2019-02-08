package io.github.joyoungc.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author joyoungc
 * @date 2018.06.15
 */
@Slf4j
@RestController
public class BatchController {

    @Autowired
    @Qualifier("userJobLauncher")
    private JobLauncher jobLauncher;

    @Autowired
    private Job userJob;

    @PostMapping("/user/start/{fileName}")
    public String startTest(@PathVariable("fileName") String fileName) throws Exception {
        log.info("## Target File Name : {}", fileName);
        JobParameters params = new JobParametersBuilder().addString("targetFile", fileName)
                .toJobParameters();
        JobExecution job = jobLauncher.run(userJob, params);

        return String.valueOf(job.getJobId());
    }

}
