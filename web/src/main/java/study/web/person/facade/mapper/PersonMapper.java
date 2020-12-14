package study.web.person.facade.mapper;

import org.mapstruct.Mapper;
import study.business.domain.model.Person;
import study.web.person.facade.dto.PersonDTO;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PersonMapper {

    PersonDTO toDTO(Person person);

    List<PersonDTO> toListDTO(List<Person> list);

    Person fromDTO(PersonDTO personDTO);

}
