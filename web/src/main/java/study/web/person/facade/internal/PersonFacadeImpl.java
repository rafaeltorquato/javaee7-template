package study.web.person.facade.internal;

import study.business.application.service.PersonService;
import study.business.domain.model.Person;
import study.web.person.facade.PersonFacade;
import study.web.person.facade.dto.NewPersonCommandDTO;
import study.web.person.facade.dto.PersonDTO;
import study.web.person.facade.mapper.PersonMapper;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class PersonFacadeImpl implements PersonFacade {

    @EJB
    private PersonService personService;
    @Inject
    private PersonMapper personMapper;

    @Override
    public List<PersonDTO> list() {
        return personMapper.toListDTO(personService.list());
    }

    @Override
    public PersonDTO save(NewPersonCommandDTO commandDTO) {
        final Person person = personService.newPerson(personMapper.fromDTO(commandDTO));
        return personMapper.toDTO(person);
    }

}
