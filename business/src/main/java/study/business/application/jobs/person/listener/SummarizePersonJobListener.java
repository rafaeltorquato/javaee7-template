package study.business.application.jobs.person.listener;

import javax.batch.api.listener.JobListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.util.logging.Logger;

@Dependent
@Named("SummarizePersonJobListener")
public class SummarizePersonJobListener implements JobListener {

    private static final Logger logger = Logger.getLogger(SummarizePersonJobListener.class.getSimpleName());

    @Override
    public void beforeJob() throws Exception {
        logger.info("Batch starting...");
    }

    @Override
    public void afterJob() throws Exception {
        logger.info("Batch done!");
    }

}
