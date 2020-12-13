package study.business.application.jobs.person.listener;

import javax.batch.api.chunk.listener.SkipReadListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("SkipReadListener")
public class SkipReadListenerImpl implements SkipReadListener {
    @Override
    public void onSkipReadItem(Exception ex) throws Exception {

    }
}
