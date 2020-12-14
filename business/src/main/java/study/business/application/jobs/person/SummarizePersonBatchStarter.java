package study.business.application.jobs.person;


import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.ejb.*;
import java.util.Properties;

@Slf4j
@Startup
@Singleton
public class SummarizePersonBatchStarter {

    @Resource
    private TimerService timerService;

    private Long lastJobExecutionId;

    @PostConstruct
    public void initialize() {
        ScheduleExpression expression = new ScheduleExpression()
                .dayOfMonth("*")
                .hour("*")
                .minute("*")
                .second("*/3");
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo("Send Person Daily Summary.");
        timerService.createCalendarTimer(expression, timerConfig);
        log.info("Initialized.");
    }


    @Timeout
    public void timeout(Timer timer) {
        log.info("Timeout!");
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        if(lastJobExecutionId != null) {
            final JobExecution jobExecution = jobOperator.getJobExecution(lastJobExecutionId);
            if(!jobExecution.getBatchStatus().equals(BatchStatus.COMPLETED)) {
                return;
            }
        }
        lastJobExecutionId = jobOperator.start("summarizePersonJob", new Properties());
        log.info("New batch job {}", lastJobExecutionId);
    }

}
