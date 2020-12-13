package study.business.application.jobs.person;

import study.business.domain.model.Person;
import study.business.domain.model.PersonCountSummary;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Dependent
@Named("PersonCountSummaryProcessor")
public class PersonCountSummaryProcessor implements ItemProcessor {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object processItem(Object item) throws Exception {
        final Person person = (Person) item;
        PersonCountSummary personCountSummary = null;
        try {
            personCountSummary = entityManager.find(PersonCountSummary.class, person.getId());
        } catch (NoResultException ignored) {
            personCountSummary = new PersonCountSummary();
            personCountSummary.setPersonId(person.getId());
        }

        personCountSummary.setAddressesCount(personCountSummary.getAddressesCount() + person.getAddresses().size());
        personCountSummary.setPhonesCount(personCountSummary.getPhonesCount() + person.getPhones().size());
        personCountSummary.setRelationshipsCount(personCountSummary.getRelationshipsCount() + person.getRelationships().size());

        return personCountSummary;
    }
}
