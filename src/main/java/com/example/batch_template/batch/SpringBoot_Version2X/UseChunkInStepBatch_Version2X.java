package com.example.batch_template.batch.SpringBoot_Version2X;
/*

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Member;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class UseChunkInStepBatch_Version2X {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MemberService memberService;
    @Value("${chunkSize:1000}")
    private int getChunkSize;
    @Bean
    public Job memberJob() {
        return jobBuilderFactory.get("memberJob")
                .start(memberStep())
                .build();
    }
    @Bean
    @JobScope
    public Step memberStep() {
        return this.stepBuilderFactory.get("memberStep")
                .<Member, Member>chunk(getChunkSize)
                .reader(this.memberReader())
                .writer(this.memberWireter())
                .build();
    }
    private ListItemReader<Member> memberReader() {
        return new ListItemReader<>(this.memberService.findAll());
    }
    @Bean
    public ItemWriter<Member> memberWriter() {
        return this::all;
    }
    private void all(final List<? extends Member> list) {
        list.forEach(item -> { log.info(item);});
    }
}

*/