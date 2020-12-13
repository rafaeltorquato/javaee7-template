package study.business.application.jobs.person;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Dependent
@Named("PersonCountSummaryWriter")
public class PersonCountSummaryWriter implements ItemWriter {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void open(Serializable checkpoint) throws Exception {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object o : items) {
            if (entityManager.contains(o)) {
                entityManager.merge(o);
            } else {
                entityManager.persist(o);
            }
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
