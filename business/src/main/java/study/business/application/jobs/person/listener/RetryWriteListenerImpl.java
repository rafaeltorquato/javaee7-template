package study.business.application.jobs.person.listener;

import javax.batch.api.chunk.listener.RetryWriteListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.util.List;

@Dependent
@Named("RetryWriteListenerImpl")
public class RetryWriteListenerImpl implements RetryWriteListener {
    @Override
    public void onRetryWriteException(List<Object> items, Exception ex) throws Exception {

    }
}
