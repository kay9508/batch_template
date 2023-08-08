package com.example.batch_template.batch.SpringBoot_Version2X;
/*

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ManyStepBatch_Version2X {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    // 다른 @Service 비즈니스 로직도 DI로 사용 가능
    // private final BatchService batchService;

    @Bean
    public Job testJob() {
        return jobBuilderFactory.get("testJob")
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

    @Bean
    public Step testStep1() {
        return stepBuilderFactory.get("testStep1")
                .tasklet((contribution, chunkContext) ->{

                    // Batch 로직 구현
                    ...

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
        return stepBuilderFactory.get("failStep1")
                .tasklet((contribution, chunkContext) ->{

                    // Batch 로직 fail 분기 로직
                    ...

                    // 정상 종료 시 상태 Return
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step testStep2() {
        return stepBuilderFactory.get("testStep2")
                .tasklet((contribution, chunkContext) ->{

                    // Batch 로직 구현
                    ...

                    // 정상 종료 시 상태 Return
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step testStep3() {
        return stepBuilderFactory.get("testStep3")
                .tasklet((contribution, chunkContext) ->{

                    // Batch 로직 구현
                    ...

                    // 정상 종료 시 상태 Return
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}

*/