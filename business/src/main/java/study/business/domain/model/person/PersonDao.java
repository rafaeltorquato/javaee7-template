package study.business.domain.model.person;

import java.util.List;

public interface PersonDao {

    void save(Person person);

    void delete(Person person);

    List<Person> list();

    Person findById(Long id);

}
