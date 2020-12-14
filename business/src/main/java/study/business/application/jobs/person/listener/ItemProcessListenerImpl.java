package study.business.application.jobs.person.listener;

import lombok.extern.slf4j.Slf4j;

import javax.batch.api.chunk.listener.ItemProcessListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Slf4j
@Dependent
@Named("ItemProcessListenerImpl")
public class ItemProcessListenerImpl implements ItemProcessListener {

    @Override
    public void beforeProcess(Object item) throws Exception {
        log.info("Processing {}...", item);
    }

    @Override
    public void afterProcess(Object item, Object result) throws Exception {
        log.info("Processed: {}, result: {}", item, result);
    }

    @Override
    public void onProcessError(Object item, Exception ex) throws Exception {
        log.info("Process error! Item: {}, ex: {}",item, ex);
    }
}
