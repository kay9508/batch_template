package com.example.batch_template;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing // 배치 프로젝트에서 설정해 주어야 하는 부분
@SpringBootApplication
public class BatchTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchTemplateApplication.class, args);
    }

}
