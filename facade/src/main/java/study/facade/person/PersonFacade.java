package study.facade.person;

import study.facade.person.dto.EditPersonCommandDTO;
import study.facade.person.dto.NewPersonCommandDTO;
import study.facade.person.dto.PersonDTO;

import java.util.List;

public interface PersonFacade {
    List<PersonDTO> list();
    PersonDTO save(NewPersonCommandDTO command);
    void delete(Long id);
    PersonDTO edit(EditPersonCommandDTO command);
}
