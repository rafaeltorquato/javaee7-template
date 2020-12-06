package study.business.infrastructure.jpa;

import study.business.domain.model.Person;
import study.business.domain.model.PersonDao;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = PersonDaoJpa.LIST_ALL,
                query = "select p from Person p join fetch p.phones join fetch p.relationships join fetch p.addresses"
        )
})
@Stateless
public class PersonDaoJpa implements PersonDao {

    public static final String LIST_ALL = "Person.listAll";

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
        return em.createNamedQuery(PersonDaoJpa.LIST_ALL)
                .getResultList();
    }

    @Override
    public Person findById(Long id) {
        Person person = em.find(Person.class, id);
        if (person != null) {
            em.lock(person, LockModeType.OPTIMISTIC);
        }
        return person;
    }
}
