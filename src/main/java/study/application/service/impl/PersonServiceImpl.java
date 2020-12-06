package study.application.service.impl;

import study.application.service.PersonService;
import study.domain.model.Person;
import study.domain.model.PersonDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PersonServiceImpl implements PersonService {

    @EJB
    private PersonDao personDao;

    @Override
    public Person newPerson(Person person) {
        this.personDao.save(person);
        return person;
    }

    @Override
    public Person editPerson(Person person) {
        return null;
    }

    @Override
    public void deletePerson(Long id) {

    }

    @Override
    public List<Person> list() {
        return null;
    }
}
