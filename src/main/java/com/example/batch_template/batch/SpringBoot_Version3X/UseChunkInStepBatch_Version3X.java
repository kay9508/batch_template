package com.example.batch_template.batch.SpringBoot_Version3X;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Member;
import java.util.ArrayList;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class UseChunkInStepBatch_Version3X extends DefaultBatchConfiguration {
    //private final MemberService memberService;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Value("${chunkSize:1000}")
    private int getChunkSize;
    @Bean
    public Job job() {
        return new JobBuilder("job", jobRepository)
                .start(step())
                .build();
    }

    /**
     * Chunk 사용
     */
    @Bean
    public Step step() {
        return new StepBuilder("step", jobRepository)
                .chunk(this.getChunkSize, transactionManager)
                .reader(this.memberReader())
                .writer(this.memberWriter())
                .build();
    }
    /*private ListItemReader<Member> memberReader() {
        return new ListItemReader<>(this.memberService.getAllMember());
    }*/
    private ListItemReader<Member> memberReader() {
        return new ListItemReader<>(new ArrayList<>());
    }

    @Bean
    public ItemWriter<Object> memberWriter() {
        return this::loggingAll;
    }
    private void loggingAll(final Chunk<?> objects) {
        objects.getItems().forEach(item -> log.info(item.toString()));
    }
}