package study.business.application.jobs.person.listener;

import javax.batch.api.chunk.listener.ItemProcessListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.util.logging.Logger;

@Dependent
@Named("ItemProcessListenerImpl")
public class ItemProcessListenerImpl implements ItemProcessListener {

    private static final Logger logger = Logger.getLogger(ItemProcessListenerImpl.class.getSimpleName());

    @Override
    public void beforeProcess(Object item) throws Exception {
        logger.info("Processing " + item + "...");
    }

    @Override
    public void afterProcess(Object item, Object result) throws Exception {
        logger.info("Processed: " + item + ", result: " + result);
    }

    @Override
    public void onProcessError(Object item, Exception ex) throws Exception {
        logger.info("Process error! Item: " + item + ", ex: " + ex);
    }
}
