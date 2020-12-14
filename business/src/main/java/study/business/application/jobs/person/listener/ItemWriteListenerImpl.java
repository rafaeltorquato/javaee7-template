package study.business.application.jobs.person.listener;

import lombok.extern.slf4j.Slf4j;

import javax.batch.api.chunk.listener.ItemWriteListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.util.List;

@Slf4j
@Dependent
@Named("ItemWriteListenerImpl")
public class ItemWriteListenerImpl implements ItemWriteListener {

    @Override
    public void beforeWrite(List<Object> items) throws Exception {
        log.info("Writing {} items...", items.size());
    }

    @Override
    public void afterWrite(List<Object> items) throws Exception {
        log.info("Written {} items!", items.size());
    }

    @Override
    public void onWriteError(List<Object> items, Exception ex) throws Exception {
        log.info("Write error! Items count: {}, ex: {}", items.size(), ex);
    }
}
