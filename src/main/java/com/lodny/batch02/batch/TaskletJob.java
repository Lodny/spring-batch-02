package com.lodny.batch02.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class TaskletJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Value("${spring.batch.job.names}")
    private String jobName;

    @Bean
    public Job taskletJob_batchBuild() {
        log.info(">>> taskletJob_batchBuild() : jobName = {}", jobName);

        return jobBuilderFactory.get("taskletJob")
                .start(taskletJob_step1())
                .next(taskletJob_step2(null))
                .build();
    }

    @Bean
    public Step taskletJob_step1() {
        log.info(">>> taskletJob_step1() : jobName = {}", jobName);

        return stepBuilderFactory.get("taskletJob_step1")
                .tasklet((a, b) -> {
                    log.info("-> job -> step1");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    @JobScope
    public Step taskletJob_step2(@Value("#{jobParameters[date]}") String date) {
        log.info(">>> taskletJob_step2() : jobName = {}", jobName);

        return stepBuilderFactory.get("taskletJob_step2")
                .tasklet((a, b) -> {
                    log.info("-> step1 -> step2 : {}", date);
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
