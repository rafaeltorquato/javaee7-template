package study.delivery.person.facade.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import study.business.application.service.PersonService;
import study.business.domain.model.person.Person;
import study.delivery.person.facade.dto.EditPersonCommandDTO;
import study.delivery.person.facade.dto.NewPersonCommandDTO;
import study.delivery.person.facade.dto.PersonDTO;

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

}