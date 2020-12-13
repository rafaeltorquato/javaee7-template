package study.business.application.jobs.person.listener;

import javax.batch.api.chunk.listener.SkipProcessListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("SkipProcessListener")
public class SkipProcessListenerImpl implements SkipProcessListener {
    @Override
    public void onSkipProcessItem(Object item, Exception ex) throws Exception {

    }
}
