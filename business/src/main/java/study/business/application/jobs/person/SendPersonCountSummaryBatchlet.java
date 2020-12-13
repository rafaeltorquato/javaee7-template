package study.business.application.jobs.person;

import javax.batch.api.Batchlet;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("SendPersonCountSummaryBatchlet")
public class SendPersonCountSummaryBatchlet implements Batchlet {

    @Override
    public String process() throws Exception {
        return null;
    }

    @Override
    public void stop() throws Exception {

    }
}
