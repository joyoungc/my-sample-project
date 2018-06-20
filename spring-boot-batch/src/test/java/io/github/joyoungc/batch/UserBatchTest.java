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
 * @author BD 정조영
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
	public void TestStartBatch1() throws Exception {

		JobParameters params = new JobParametersBuilder().addString("targetFile", "test-data01.dat")
				.toJobParameters();
		JobExecution job = jobLauncher.run(userJob, params);

		assertThat(job.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

		/*		JobParameters params2 = new JobParametersBuilder().addString("targetFile", "test-data-sample02.dat")
				.toJobParameters();
		jobLauncher.run(userJob, params2);

		// assertThat(job2.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

		JobParameters params3 = new JobParametersBuilder().addString("targetFile", "test-data-sample03.dat")
				.toJobParameters();
		jobLauncher.run(userJob, params3);*/

		// assertThat(job3.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
	}

	//@Test
	public void TestStartBatch2() throws Exception {
		JobParameters params = new JobParametersBuilder().addString("targetFile", "test-data-sample4.dat")
				.toJobParameters();
		JobExecution job = jobLauncher.run(userJob, params);
		assertThat(job.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
	}

	//@Test
	public void TestStartBatch3() throws Exception {
		JobParameters params = new JobParametersBuilder().addString("targetFile", "test-data-sample5.dat")
				.toJobParameters();
		JobExecution job = jobLauncher.run(userJob, params);
		assertThat(job.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
	}

}
