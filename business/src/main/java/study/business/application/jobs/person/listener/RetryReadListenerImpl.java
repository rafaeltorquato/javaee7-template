package study.business.application.jobs.person.listener;

import javax.batch.api.chunk.listener.RetryReadListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("RetryReadListenerImpl")
public class RetryReadListenerImpl implements RetryReadListener {

    @Override
    public void onRetryReadException(Exception ex) throws Exception {

    }
}
