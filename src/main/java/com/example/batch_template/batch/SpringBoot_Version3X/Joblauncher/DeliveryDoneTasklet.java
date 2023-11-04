package com.example.batch_template.batch.SpringBoot_Version3X.Joblauncher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryDoneTasklet implements Tasklet {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeliveryDoneService deliveryDoneService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        try {
            logger.debug("-----------------------배송 완료처리 시작-------------------------");
            deliveryDoneService.deliveryDoneProc();
            logger.debug("-----------------------배송 완료처리 종료-------------------------");
        } catch (Exception e) {
            logger.debug("-----------------------배송 완료처리 에러 시작-------------------------");
            logger.error(e.getMessage(), e);
            logger.debug("-----------------------배송 완료처리 에러 종료-------------------------");
        }

        return RepeatStatus.FINISHED;
    }
}
