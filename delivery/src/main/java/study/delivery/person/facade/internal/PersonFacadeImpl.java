package study.delivery.person.facade.internal;

import study.business.application.service.PersonService;
import study.business.domain.model.person.Person;
import study.delivery.person.facade.PersonFacade;
import study.delivery.person.facade.dto.EditPersonCommandDTO;
import study.delivery.person.facade.dto.NewPersonCommandDTO;
import study.delivery.person.facade.dto.PersonDTO;
import study.delivery.person.facade.mapper.PersonMapper;

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
        final List<Person> list = personService.list();
        return personMapper.toListDTO(list);
    }

    @Override
    public PersonDTO save(NewPersonCommandDTO command) {
        final Person person = personService.save(personMapper.fromDTO(command));
        return personMapper.toDTO(person);
    }

    @Override
    public void delete(Long id) {
        personService.delete(id);
    }

    @Override
    public PersonDTO edit(EditPersonCommandDTO command) {
        final Person person = personService.edit(personMapper.fromDTO(command));
        return personMapper.toDTO(person);
    }

}
