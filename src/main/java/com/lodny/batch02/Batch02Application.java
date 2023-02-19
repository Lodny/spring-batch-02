package com.lodny.batch02;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class Batch02Application {
    public static void main(String[] args) {
        SpringApplication.run(Batch02Application.class, args);
    }
}
