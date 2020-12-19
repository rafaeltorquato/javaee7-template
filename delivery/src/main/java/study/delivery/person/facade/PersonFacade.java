package study.delivery.person.facade;

import study.delivery.person.facade.dto.EditPersonCommandDTO;
import study.delivery.person.facade.dto.NewPersonCommandDTO;
import study.delivery.person.facade.dto.PersonDTO;

import java.util.List;

public interface PersonFacade {
    List<PersonDTO> list();
    PersonDTO save(NewPersonCommandDTO command);
    void delete(Long id);
    PersonDTO edit(EditPersonCommandDTO command);
}
