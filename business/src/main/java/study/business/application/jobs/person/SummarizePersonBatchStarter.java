package study.business.application.jobs.person;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.ejb.*;
import javax.enterprise.context.Dependent;
import java.util.Properties;

@Dependent
@Singleton
public class SummarizePersonBatchStarter {

    @Resource
    private TimerService timerService;

    private Long lastJobExecutionId;

    @PostConstruct
    public void initialize() {
        ScheduleExpression expression = new ScheduleExpression()
                .dayOfMonth("*")
                .hour("00")
                .minute("0")
                .second("0");
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo("Send Person Daily Summary.");
        timerService.createCalendarTimer(expression, timerConfig);
    }


    @Timeout
    public void timeout(Timer timer) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        if(lastJobExecutionId != null) {
            final JobExecution jobExecution = jobOperator.getJobExecution(lastJobExecutionId);
            if(!jobExecution.getBatchStatus().equals(BatchStatus.COMPLETED)) {
                return;
            }
        }
        lastJobExecutionId = jobOperator.start("summarizePersonJob", new Properties());
    }

}
