package io.github.joyoungc.batch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author joyoungc
 * @date 2018.06.19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBatchTest {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job userJob;

	@Test
	public void TestStartBatch() throws Exception {

		JobParameters params = new JobParametersBuilder().addString("targetFile", "test-data01.dat")
				.toJobParameters();
		JobExecution job = jobLauncher.run(userJob, params);

		assertThat(job.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
		
	}

}
