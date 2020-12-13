package study.business.application.jobs.person.listener;

import javax.batch.api.listener.StepListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.util.logging.Logger;

@Dependent
@Named("StepListenerImpl")
public class StepListenerImpl implements StepListener {

    private static final Logger logger = Logger.getLogger(StepListenerImpl.class.getSimpleName());

    @Override
    public void beforeStep() throws Exception {
        logger.info("Step starting...");
    }

    @Override
    public void afterStep() throws Exception {
        logger.info("Step done!");
    }
}
