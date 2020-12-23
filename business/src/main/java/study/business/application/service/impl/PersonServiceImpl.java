package study.business.application.service.impl;

import lombok.extern.slf4j.Slf4j;
import study.business.application.service.PersonService;
import study.business.domain.model.person.*;
import study.components.interceptor.Logged;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Iterator;
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
    @RolesAllowed({"ADMINISTRATOR", "SAVE_PERSON"})
    public Person save(SavePersonCommand command) {
        final Person person = new Person();
        setPerson(command, person);
        this.personDao.save(person);
        personRecipient.notify(new PersonSavedEvent(person));
        return person;
    }

    @Override
    @RolesAllowed({"ADMINISTRATOR", "EDIT_PERSON"})
    public Person edit(EditPersonCommand command) {
        final Person person = personDao.findById(command.getId());
        if (person == null) throw new PersonNotFoundException();
        person.setVersion(command.getVersion());

        setPerson(command, person);
        return person;
    }

    @Override
    @RolesAllowed({"ADMINISTRATOR", "DELETE_PERSON"})
    public void delete(Long id) {
        final Person person = personDao.findById(id);
        if (person == null) throw new PersonNotFoundException();

        personDao.delete(person);
        personRecipient.notify(new PersonDeletedEvent(person));
    }

    @Override
    @RolesAllowed({"ADMINISTRATOR", "LIST_PERSON"})
    public List<Person> list() {
        return personDao.list();
    }

    @Override
    @RolesAllowed({"ADMINISTRATOR", "EDIT_PERSON"})
    public Person saveRelationship(SaveRelationshipCommand command) {
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
        return source;
    }

    @Override
    @RolesAllowed({"ADMINISTRATOR", "EDIT_PERSON"})
    public Person deleteRelationship(DeleteRelationshipCommand command) {
        final Person source = personDao.findById(command.getSourcePersonId());
        if (source == null) throw new PersonNotFoundException("Source person not found!");

        final Iterator<Relationship> iterator = source.getRelationships().iterator();
        while(iterator.hasNext()) {
            final Relationship relationship = iterator.next();
            if(relationship.getTarget().getId().equals(command.getTargetPersonId())) {
                iterator.remove();
                personDao.save(source);
                return source;
            }
        }

        throw new RelationshipNotFound();
    }

    @Override
    @RolesAllowed({"ADMINISTRATOR", "EDIT_PERSON"})
    public Person savePhone(SavePhoneCommand command) {
        final Person owner = personDao.findById(command.getOwnerPersonId());
        if (owner == null) throw new PersonNotFoundException();

        final Phone phone = new Phone();
        phone.setOwner(owner);
        phone.setAreaCode(command.getAreaCode());
        phone.setNumber(command.getNumber());
        phone.setCountryCode(command.getCountryCode());
        owner.getPhones().add(phone);
        personDao.save(owner);

        return owner;
    }

    @Override
    @RolesAllowed({"ADMINISTRATOR", "EDIT_PERSON"})
    public Person deletePhone(DeletePhoneCommand command) {
        final Person owner = personDao.findById(command.getOwnerPersonId());
        if (owner == null) throw new PersonNotFoundException();

        final Iterator<Phone> iterator = owner.getPhones().iterator();
        while(iterator.hasNext()) {
            Phone phone = iterator.next();
            if(phone.getId().equals(command.getPhoneId())) {
                iterator.remove();
                personDao.save(owner);
                return owner;
            }
        }

        throw new PhoneNotFound();
    }

    @Override
    @RolesAllowed({"ADMINISTRATOR", "EDIT_PERSON"})
    public Person get(Long id) {
        return personDao.findById(id);
    }

    private void setPerson(SavePersonCommand command, Person person) {
        person.setName(new PersonName(command.getName(), command.getSurname()));
        person.setBirthDate(command.getBirthDate());
        person.setEmail(command.getEmail());
    }
}
