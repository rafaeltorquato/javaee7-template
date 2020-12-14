package study.business.application.jobs.person.listener;

import lombok.extern.slf4j.Slf4j;

import javax.batch.api.chunk.listener.ItemReadListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Slf4j
@Dependent
@Named("ItemReadListenerImpl")
public class ItemReadListenerImpl implements ItemReadListener {


    @Override
    public void beforeRead() throws Exception {
        log.info("Reading...");
    }

    @Override
    public void afterRead(Object item) throws Exception {
        log.info("Read: {}", item);
    }

    @Override
    public void onReadError(Exception ex) throws Exception {
        log.info("Read error!");
    }
}
