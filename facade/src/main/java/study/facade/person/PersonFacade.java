package study.facade.person;

import study.facade.person.dto.*;

import java.util.List;

public interface PersonFacade {
    List<PersonDTO> list();
    PersonDTO save(SavePersonCommandDTO command);
    void delete(Long id);
    PersonDTO edit(EditPersonCommandDTO command);
    PersonDTO saveRelationship(SaveRelationshipCommandDTO command);
    PersonDTO deleteRelationship(DeleteRelationshipCommandDTO command);
    PersonDTO savePhone(SavePhoneCommandDTO command);
    PersonDTO deletePhone(DeletePhoneCommandDTO command);
}
