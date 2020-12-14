package study.business.application.jobs.person.listener;

import lombok.extern.slf4j.Slf4j;

import javax.batch.api.listener.JobListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Slf4j
@Dependent
@Named("SummarizePersonJobListener")
public class SummarizePersonJobListener implements JobListener {

    @Override
    public void beforeJob() throws Exception {
        log.info("Batch starting...");
    }

    @Override
    public void afterJob() throws Exception {
        log.info("Batch done!");
    }

}
