package com.example.batch_template.batch.SpringBoot_Version3X;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ManyStepBatch_Version3X extends DefaultBatchConfiguration {
    // 다른 @Service 비즈니스 로직도 DI로 사용 가능
    // private final BatchService batchService;
    private final JobRepository jobRepository;

    @Bean
    public Job testJob() {
        return new JobBuilder("testJob", jobRepository)
                // testStep1 시작
                .start(testStep1())
                // testStep1 수행 결과 ExitStatus.FAILED 일 경우
                .on("FAILED")
                // failStep1 수행
                .to(failStep1())
                // failStep1 수행 결과에 상관 없이
                .on("*")
                // testStep1 종료
                .end()
                // testStep1 후에
                .from(testStep1())
                // testStep1 수행 결과에 상관 없이
                .on("*")
                // tesetStep2로 다음 Step 수행
                .to(testStep2())
                // testStep2 수행 결과에 상관 없이
                // 다음 수행으로 testStep3 지정
                .next(testStep3())
                // testStep3 수행 결과에 상관없이
                .on("*")
                // stepFlow 종료(FlowBuilder 반환)
                .end()
                // 해당 Job 종료(FlowBuilder 종료)
                .end()
                .build();
    }

    /**
     * Tasklet 사용
     */
    @Bean
    public Step testStep1() {
        return new StepBuilder("testStep1", jobRepository)
                .tasklet((contribution, chunkContext) ->{

                    // Batch 로직 구현
                    //...

                    // 아래 구문으로 로직 도중 FAILED 처리 가능
                    // ExitStatus 안의 값에 따라 분기 가능
                    contribution.setExitStatus(ExitStatus.FAILED);

                    // 정상 종료 시 상태 Return
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step failStep1() {
        return new StepBuilder("failStep1", jobRepository)
                .tasklet((contribution, chunkContext) ->{

                    // Batch 로직 fail 분기 로직
                    //...

                    // 정상 종료 시 상태 Return
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step testStep2() {
        return new StepBuilder("testStep2", jobRepository)
                .tasklet((contribution, chunkContext) ->{

                    // Batch 로직 구현
                    //...

                    // 정상 종료 시 상태 Return
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step testStep3() {
        return new StepBuilder("testStep3", jobRepository)
                .tasklet((contribution, chunkContext) ->{

                    // Batch 로직 구현
                    //...

                    // 정상 종료 시 상태 Return
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}