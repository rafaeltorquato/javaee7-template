package study.facade.person.internal;

import study.business.application.service.BusinessException;
import study.business.application.service.PersonService;
import study.business.domain.model.person.Person;
import study.facade.exception.FacadeBusinessException;
import study.facade.person.PersonFacade;
import study.facade.person.dto.*;
import study.facade.person.mapper.PersonMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PersonFacadeImpl implements PersonFacade {

    @EJB
    private PersonService personService;
    @Inject
    private PersonMapper personMapper;

    @Override
    public List<PersonDTO> list() {
        try {
            final List<Person> list = personService.list();
            return personMapper.toListDTO(list);
        } catch (BusinessException exception) {
            throw new FacadeBusinessException(exception);
        }
    }

    @Override
    public PersonDTO save(SavePersonCommandDTO command) {
        try {
            final Person person = personService.save(personMapper.fromDTO(command));
            return personMapper.toDTO(person);
        } catch (BusinessException exception) {
            throw new FacadeBusinessException(exception);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            personService.delete(id);
        } catch (BusinessException exception) {
            throw new FacadeBusinessException(exception);
        }
    }

    @Override
    public PersonDTO edit(EditPersonCommandDTO command) {
        try {
            final Person person = personService.edit(personMapper.fromDTO(command));
            return personMapper.toDTO(person);
        } catch (BusinessException exception) {
            throw new FacadeBusinessException(exception);
        }
    }

    @Override
    public PersonDTO saveRelationship(SaveRelationshipCommandDTO command) {
        try {
            final Person person = personService.saveRelationship(personMapper.fromDTO(command));
            return personMapper.toDTO(person);
        } catch (BusinessException exception) {
            throw new FacadeBusinessException(exception);
        }
    }

    @Override
    public PersonDTO deleteRelationship(DeleteRelationshipCommandDTO command) {
        try {
            final Person person = personService.deleteRelationship(personMapper.fromDTO(command));
            return personMapper.toDTO(person);
        } catch (BusinessException exception) {
            throw new FacadeBusinessException(exception);
        }
    }

    @Override
    public PersonDTO savePhone(SavePhoneCommandDTO command) {
        try {
            final Person person = personService.savePhone(personMapper.fromDTO(command));
            return personMapper.toDTO(person);
        } catch (BusinessException exception) {
            throw new FacadeBusinessException(exception);
        }
    }

    @Override
    public PersonDTO deletePhone(DeletePhoneCommandDTO command) {
        try {
            final Person person = personService.deletePhone(personMapper.fromDTO(command));
            return personMapper.toDTO(person);
        } catch (BusinessException exception) {
            throw new FacadeBusinessException(exception);
        }
    }

}
