package study.web.person.facade;

import study.web.person.facade.dto.PersonDTO;

import java.util.List;

public interface PersonFacade {
    List<PersonDTO> list();
    PersonDTO save(PersonDTO personDTO);
}
