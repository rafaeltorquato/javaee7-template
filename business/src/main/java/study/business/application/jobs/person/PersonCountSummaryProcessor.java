package study.business.application.jobs.person;

import lombok.extern.slf4j.Slf4j;
import study.business.domain.model.Person;
import study.business.domain.model.PersonCountSummary;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Dependent
@Named("PersonCountSummaryProcessor")
public class PersonCountSummaryProcessor implements ItemProcessor {

    //TODO Create Dao
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object processItem(Object item) throws Exception {
        final Person person = (Person) item;
        PersonCountSummary personCountSummary;
        personCountSummary = entityManager.find(PersonCountSummary.class, person.getId());
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
