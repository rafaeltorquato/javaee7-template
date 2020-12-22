package study.facade.person.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import study.business.application.service.PersonService;
import study.business.domain.model.person.Person;
import study.facade.person.dto.*;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PersonMapper {

    @Mappings({
            @Mapping(target = "name", source = "name.name"),
            @Mapping(target = "surname", source = "name.surname")
    })
    PersonDTO toDTO(Person person);

    List<PersonDTO> toListDTO(List<Person> list);

    PersonService.NewPersonCommand fromDTO(NewPersonCommandDTO dto);

    PersonService.EditPersonCommand fromDTO(EditPersonCommandDTO dto);

    PersonService.SaveRelationshipCommand fromDTO(SaveRelationshipCommandDTO dto);

    PersonService.DeleteRelationshipCommand fromDTO(DeleteRelationshipCommandDTO dto);

    PersonService.SavePhoneCommand fromDTO(SavePhoneCommandDTO dto);

    PersonService.DeletePhoneCommand fromDTO(DeletePhoneCommandDTO dto);

}
