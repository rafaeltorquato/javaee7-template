package study.business.application.jobs.person.listener;

import javax.batch.api.chunk.listener.RetryProcessListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("RetryProcessListenerImpl")
public class RetryProcessListenerImpl implements RetryProcessListener {
    @Override
    public void onRetryProcessException(Object item, Exception ex) throws Exception {

    }
}
