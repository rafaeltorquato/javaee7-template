package study.business.application.jobs.person;

import lombok.extern.slf4j.Slf4j;
import study.business.domain.model.Person;
import study.business.domain.model.PersonDao;

import javax.batch.api.chunk.ItemReader;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Slf4j
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
        } else {
            this.checkpoint = new PersonCountSummaryCheckpoint();
        }
        log.info("Selecting new list...");
        personList = personDao.list();
        log.info("Person size: " + personList.size());
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public Object readItem() throws Exception {
        try {
            log.info("Reading index:" + this.checkpoint.getIndex());
            final Person person = personList.get(this.checkpoint.getIndex());
            checkpoint.next();
            return person;
        } catch (IndexOutOfBoundsException e) {
            this.checkpoint = new PersonCountSummaryCheckpoint();
            return null;
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }
}
