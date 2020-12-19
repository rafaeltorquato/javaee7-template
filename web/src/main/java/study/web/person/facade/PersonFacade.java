package study.web.person.facade;

import study.web.person.facade.dto.EditPersonCommandDTO;
import study.web.person.facade.dto.NewPersonCommandDTO;
import study.web.person.facade.dto.PersonDTO;

import java.util.List;

public interface PersonFacade {
    List<PersonDTO> list();
    PersonDTO save(NewPersonCommandDTO command);
    void delete(Long id);
    PersonDTO edit(EditPersonCommandDTO command);
}
