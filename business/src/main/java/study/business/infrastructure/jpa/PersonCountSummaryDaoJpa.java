package study.business.infrastructure.jpa;

import study.business.domain.model.PersonCountSummary;
import study.business.domain.model.PersonCountSummaryDao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class PersonCountSummaryDaoJpa implements PersonCountSummaryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public PersonCountSummary save(PersonCountSummary personCountSummary) {
        return em.merge(personCountSummary);
    }

    @Override
    public PersonCountSummary findById(Long id) {
        return em.find(PersonCountSummary.class, id);
    }
}
