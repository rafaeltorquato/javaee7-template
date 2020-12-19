package study.business.application.jobs.person;

import lombok.extern.slf4j.Slf4j;
import study.business.domain.model.person.Person;
import study.business.domain.model.summary.PersonCountSummary;
import study.business.domain.model.summary.PersonCountSummaryDao;

import javax.batch.api.chunk.ItemProcessor;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Slf4j
@Dependent
@Named("PersonCountSummaryProcessor")
public class PersonCountSummaryProcessor implements ItemProcessor {

    //TODO Create Dao
    @EJB
    private PersonCountSummaryDao personCountSummaryDao;

    @Override
    public Object processItem(Object item) throws Exception {
        final Person person = (Person) item;
        PersonCountSummary personCountSummary;
        personCountSummary = personCountSummaryDao.findById(person.getId());
        if(personCountSummary == null) {
            personCountSummary = new PersonCountSummary();
            personCountSummary.setPersonId(person.getId());
        }

        personCountSummary.setAddressesCount(personCountSummary.getAddressesCount() + person.getAddresses().size());
        personCountSummary.setPhonesCount(personCountSummary.getPhonesCount() + person.getPhones().size());
        personCountSummary.setRelationshipsCount(personCountSummary.getRelationshipsCount() + person.getRelationships().size());

        return personCountSummary;
    }
}
