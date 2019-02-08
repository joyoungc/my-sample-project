package io.github.joyoungc.batch.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;

import io.github.joyoungc.batch.batch.item.UserItemProcessor;
import io.github.joyoungc.batch.batch.item.UserItemWriter;
import io.github.joyoungc.batch.batch.listener.UserChunkListener;
import io.github.joyoungc.batch.batch.listener.UserStepListener;
import io.github.joyoungc.batch.config.BatchProperties;
import io.github.joyoungc.batch.config.Constants;
import io.github.joyoungc.common.model.User;

/**
 * @author joyoungc
 * @date 2018.06.19
 */
@Configuration
public class UserBatchConfig {

    @Autowired
    private BatchProperties prop;

    @Autowired
    private UserStepListener stepListener;

    @Autowired
    private UserChunkListener chunkListener;

    @Bean(name = "userJobLauncher")
    public JobLauncher jobLauncher(@Qualifier(Constants.JOB_TASK_EXECUTOR) TaskExecutor executor,
                                   JobRepository jobRepository) {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setTaskExecutor(executor);
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }

    @Bean
    public Job userJob(JobBuilderFactory jobBuilderFactory, Step userStep) {
        return jobBuilderFactory.get("userJob").incrementer(new RunIdIncrementer()).flow(userStep).end()
                .build();
    }

    @Bean
    public Step userStep(@Qualifier(Constants.STEP_TASK_EXECUTOR) TaskExecutor executor,
                         StepBuilderFactory stepBuilderFactory,
                         FlatFileItemReader<User> reader, UserItemProcessor processor, UserItemWriter writer) {
        return stepBuilderFactory.get("userStep").<User, User>chunk(prop.getBatch().getChunkSize()).reader(reader)
                .processor(processor).writer(writer).listener(stepListener).listener(chunkListener)
                .taskExecutor(executor).build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<User> reader(@Value("#{jobParameters['targetFile']}") String targetFile) {
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("data/" + targetFile));
        reader.setLineMapper(new DefaultLineMapper<User>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer("|") {
                    {
                        setNames(new String[]{"userId"});
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {
                    {
                        setTargetType(User.class);
                    }
                });
            }
        });
        return reader;
    }

    @Bean
    @StepScope
    public UserItemProcessor processor() {
        return new UserItemProcessor();
    }

    @Bean
    @StepScope
    public UserItemWriter writer() {
        return new UserItemWriter();
    }

}
