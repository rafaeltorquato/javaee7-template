package study.business.infrastructure.jpa.dao;

import study.business.domain.model.summary.PersonCountSummary;
import study.business.domain.model.summary.PersonCountSummaryDao;

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
    public PersonCountSummary save(PersonCountSummary summary) {
        return em.merge(summary);
    }

    @Override
    public PersonCountSummary findById(Long id) {
        return em.find(PersonCountSummary.class, id);
    }

    @Override
    public void delete(PersonCountSummary summary) {
        em.remove(summary);
    }
}
