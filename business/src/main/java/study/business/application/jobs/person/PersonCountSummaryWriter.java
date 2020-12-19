package study.business.application.jobs.person;

import study.business.domain.model.PersonCountSummary;
import study.business.domain.model.PersonCountSummaryDao;

import javax.batch.api.chunk.ItemWriter;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Dependent
@Named("PersonCountSummaryWriter")
public class PersonCountSummaryWriter implements ItemWriter {

    @EJB
    private PersonCountSummaryDao personCountSummaryDao;

    @Override
    public void open(Serializable checkpoint) throws Exception {
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object o : items) {
            personCountSummaryDao.save((PersonCountSummary) o);
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
