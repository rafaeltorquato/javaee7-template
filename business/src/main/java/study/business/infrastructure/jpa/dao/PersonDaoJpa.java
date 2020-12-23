package study.business.infrastructure.jpa.dao;

import study.business.domain.model.person.Person;
import study.business.domain.model.person.PersonDao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class PersonDaoJpa implements PersonDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Person person) {
        if (em.contains(person)) {
            em.persist(person);
        } else {
            em.merge(person);
        }
    }

    @Override
    public void delete(Person person) {
        em.remove(person);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> list() {
        EntityGraph entityGraph = em.getEntityGraph("fetch-all-limited-relationship");
        return em.createNamedQuery(Person.LIST_ALL)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();
    }

    @Override
    public Person findById(Long id) {
        EntityGraph entityGraph = em.getEntityGraph("fetch-all-limited-relationship");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);

        Person person = em.find(Person.class, id, properties);
        if (person != null) {
            em.lock(person, LockModeType.OPTIMISTIC);
        }
        return person;
    }

}
