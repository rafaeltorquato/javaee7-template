package study.business.application.jobs.person.listener;

import javax.batch.api.chunk.listener.SkipWriteListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.util.List;

@Dependent
@Named("SkipWriteListener")
public class SkipWriteListenerImpl implements SkipWriteListener {
    @Override
    public void onSkipWriteItem(List<Object> items, Exception ex) throws Exception {

    }
}
