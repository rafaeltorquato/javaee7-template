package study.business.application.jobs.person.listener;

import javax.batch.api.chunk.listener.ItemReadListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.util.logging.Logger;

@Dependent
@Named("ItemReadListenerImpl")
public class ItemReadListenerImpl implements ItemReadListener {

    private static final Logger logger = Logger.getLogger(ItemReadListenerImpl.class.getSimpleName());

    @Override
    public void beforeRead() throws Exception {
        logger.info("Reading...");
    }

    @Override
    public void afterRead(Object item) throws Exception {
        logger.info("Read: " + item);
    }

    @Override
    public void onReadError(Exception ex) throws Exception {
        logger.info("Read error!");
    }
}
