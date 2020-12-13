package study.business.application.jobs.person;

import study.business.domain.model.Person;
import study.business.domain.model.PersonDao;

import javax.batch.api.chunk.ItemReader;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Dependent
@Named("PersonCountSummaryReader")
public class PersonCountSummaryReader implements ItemReader {

    @EJB
    private PersonDao personDao;

    private PersonCountSummaryCheckpoint checkpoint;
    private List<Person> personList = null;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        if(checkpoint != null) {
            this.checkpoint = (PersonCountSummaryCheckpoint) checkpoint;
            this.checkpoint.next();
        } else {
            this.checkpoint = new PersonCountSummaryCheckpoint();
            personList = personDao.list();
        }
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public Object readItem() throws Exception {
        try {
            final Person person = personList.get(this.checkpoint.getIndex());
            return person;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }
}
