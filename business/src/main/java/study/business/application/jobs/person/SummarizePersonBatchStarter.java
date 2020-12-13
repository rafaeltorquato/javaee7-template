package study.business.application.jobs.person;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.ejb.*;
import java.util.Properties;
import java.util.logging.Logger;

@Startup
@Singleton
public class SummarizePersonBatchStarter {

    private static final Logger logger = Logger.getLogger(SummarizePersonBatchStarter.class.getSimpleName());

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
        logger.info("Initialized.");
    }


    @Timeout
    public void timeout(Timer timer) {
        logger.info("Timeout!");
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        if(lastJobExecutionId != null) {
            final JobExecution jobExecution = jobOperator.getJobExecution(lastJobExecutionId);
            if(!jobExecution.getBatchStatus().equals(BatchStatus.COMPLETED)) {
                return;
            }
        }
        lastJobExecutionId = jobOperator.start("summarizePersonJob", new Properties());
        logger.info("New batch job " + lastJobExecutionId);
    }

}
