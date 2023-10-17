package com.example.batch_template.batch.SpringBoot_Version3X.Joblauncher;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class DeliveryDoneConfig extends DefaultBatchConfiguration {


    //브랜치를 테스트해보기위해 작성합니다3.@@@@@@@@@@@@@@@@@@@@@@@@@
    //브랜치를 테스트해보기위해 작성합니다3.@@@@@@@@@@@@@@@@@@@@@@@@@
    //브랜치를 테스트해보기위해 작성합니다3.@@@@@@@@@@@@@@@@@@@@@@@@@
    //브랜치를 테스트해보기위해 작성합니다3.@@@@@@@@@@@@@@@@@@@@@@@@@
    //브랜치를 테스트해보기위해 작성합니다3.@@@@@@@@@@@@@@@@@@@@@@@@@
    //브랜치를 테스트해보기위해 작성합니다3.@@@@@@@@@@@@@@@@@@@@@@@@@
    //브랜치를 테스트해보기위해 작성합니다3.@@@@@@@@@@@@@@@@@@@@@@@@@
    //브랜치를 테스트해보기위해 작성합니다3.@@@@@@@@@@@@@@@@@@@@@@@@@

    public final JobLauncher jobLauncher;

    private final DeliveryDoneTasklet deliveryDoneTasklet;

    private final JobRepository jobRepository;

    // 4분마다 실행
    @Scheduled(cron="0 */30 * * * *")
    public String runDeliveryDone() throws Exception {
        JobParameters param = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();

        JobExecution execution = jobLauncher.run(deliveryDone(), param);

        return execution.getStatus().toString();
    }

    @Bean
    public Job deliveryDone() {

        return new JobBuilder("deliveryDoneStep", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .start(deliveryDoneStep())
                .build();
    }

    @Bean
    public Step deliveryDoneStep() {

        return new StepBuilder("deliveryDoneStep", jobRepository)
                .tasklet(deliveryDoneTasklet)
                .build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionNotificationListener();
    }
}
