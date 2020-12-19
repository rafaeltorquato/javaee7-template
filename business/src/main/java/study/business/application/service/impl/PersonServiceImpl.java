package study.business.application.service.impl;

import study.business.application.event.PersonDeletedEvent;
import study.business.application.service.PersonService;
import study.business.domain.model.person.Person;
import study.business.domain.model.person.PersonDao;
import study.business.domain.model.person.PersonName;
import study.components.interceptor.Logged;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

@Logged
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PersonServiceImpl implements PersonService {

    @EJB
    private PersonDao personDao;
    @Inject
    private Event<PersonDeletedEvent> personDeletedEvent;

    @Override
    public Person save(NewPersonCommand command) {
        final Person person = new Person();
        setPerson(command, person);
        this.personDao.save(person);
        return person;
    }

    @Override
    public Person edit(EditPersonCommand command) {
        final Person person = personDao.findById(command.getId());
        if(person != null) {
            setPerson(command, person);
        }
        return person;
    }

    @Override
    public void delete(Long id) {
        Person person = personDao.findById(id);
        if(person != null) {
            personDao.delete(person);
            personDeletedEvent.fire(new PersonDeletedEvent(id));
        }
    }

    @Override
    public List<Person> list() {
        return personDao.list();
    }

    private void setPerson(NewPersonCommand command, Person person) {
        person.setName(new PersonName(command.getName(), command.getSurname()));
        person.setBirthDate(command.getBirthDate());
        person.setEmail(command.getEmail());
    }
}
