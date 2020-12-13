package study.business.application.jobs.person.listener;

import javax.batch.api.chunk.listener.ItemWriteListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Dependent
@Named("ItemWriteListenerImpl")
public class ItemWriteListenerImpl implements ItemWriteListener {

    private static final Logger logger = Logger.getLogger(ItemWriteListenerImpl.class.getSimpleName());

    @Override
    public void beforeWrite(List<Object> items) throws Exception {
        logger.info("Writing " + items.size() + " items...");
    }

    @Override
    public void afterWrite(List<Object> items) throws Exception {
        logger.info("Written " + items.size() + " items!");
    }

    @Override
    public void onWriteError(List<Object> items, Exception ex) throws Exception {
        logger.info("Write error! Items count: " + items.size() + ", ex: " + ex);
    }
}
