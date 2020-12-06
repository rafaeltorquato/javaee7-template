package study.business.application.service;

import study.business.domain.model.Person;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PersonService {

    Person newPerson(Person person);

    Person editPerson(Person person);

    void deletePerson(Long id);

    List<Person> list();

}

