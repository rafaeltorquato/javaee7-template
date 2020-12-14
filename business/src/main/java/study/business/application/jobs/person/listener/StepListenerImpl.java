package study.business.application.jobs.person.listener;

import lombok.extern.slf4j.Slf4j;

import javax.batch.api.listener.StepListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Slf4j
@Dependent
@Named("StepListenerImpl")
public class StepListenerImpl implements StepListener {

    @Override
    public void beforeStep() throws Exception {
        log.info("Step starting...");
    }

    @Override
    public void afterStep() throws Exception {
        log.info("Step done!");
    }
}
