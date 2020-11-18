package study.interfaces.person.facade;

import study.interfaces.person.facade.dto.PersonDTO;

import java.util.List;

public interface PersonFacade {
    List<PersonDTO> search(String term);
}
