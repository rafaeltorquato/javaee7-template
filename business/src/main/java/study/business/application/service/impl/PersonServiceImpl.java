package study.business.application.service.impl;

import lombok.extern.slf4j.Slf4j;
import study.business.application.service.PersonService;
import study.business.domain.model.person.*;
import study.components.interceptor.Logged;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Slf4j
@Logged
@Stateless
public class PersonServiceImpl implements PersonService {

    @EJB
    private PersonDao personDao;
    @EJB
    private PersonRecipient personRecipient;

    @Override
    public Person save(NewPersonCommand command) {
        final Person person = new Person();
        setPerson(command, person);
        this.personDao.save(person);
        personRecipient.notify(new PersonSavedEvent(person));
        return person;
    }

    @Override
    public Person edit(EditPersonCommand command) {
        final Person person = personDao.findById(command.getId());
        if (person == null) throw new PersonNotFoundException();

        setPerson(command, person);
        return person;
    }

    @Override
    public void delete(Long id) {
        final Person person = personDao.findById(id);
        if (person == null) throw new PersonNotFoundException();

        personDao.delete(person);
        personRecipient.notify(new PersonDeletedEvent(person));
    }

    @Override
    public List<Person> list() {
        return personDao.list();
    }

    @Override
    public void addRelationship(AddRelationshipCommand command) {
        final Person source = personDao.findById(command.getSourcePersonId());
        if (source == null) throw new PersonNotFoundException("Source person not found!");
        final Person target = personDao.findById(command.getTargetPersonId());
        if (target == null) throw new PersonNotFoundException("Target person not found!");
        final Relationship relationship = new Relationship(
                source,
                target,
                command.getRelationshipType()
        );
        source.getRelationships().add(relationship);
        personDao.save(source);
    }

    @Override
    public void addPhone(AddPhoneCommand command) {
        final Person owner = personDao.findById(command.getOwnerPersonId());
        if (owner == null) throw new PersonNotFoundException();

        final Phone phone = new Phone();
        phone.setOwner(owner);
        phone.setAreaCode(command.getAreaCode());
        phone.setNumber(command.getNumber());
        phone.setCountryCode(command.getCountryCode());
        owner.getPhones().add(phone);
        personDao.save(owner);
    }

    private void setPerson(NewPersonCommand command, Person person) {
        person.setName(new PersonName(command.getName(), command.getSurname()));
        person.setBirthDate(command.getBirthDate());
        person.setEmail(command.getEmail());
    }
}
