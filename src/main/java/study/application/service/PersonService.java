package study.application.service;

import study.domain.model.Person;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PersonService {

    Person newPerson(Person person);

    Person editPerson(Person person);

    void deletePerson(Long id);

    List<Person> list();

}

