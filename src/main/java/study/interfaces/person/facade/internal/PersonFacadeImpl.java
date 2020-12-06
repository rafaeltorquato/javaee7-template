package study.interfaces.person.facade.internal;

import study.application.service.EmailService;
import study.application.service.PersonService;
import study.interfaces.person.facade.PersonFacade;
import study.interfaces.person.facade.dto.PersonDTO;

import javax.ejb.EJB;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class PersonFacadeImpl implements PersonFacade {

    @EJB
    private PersonService personService;
    @EJB
    private EmailService emailService;
    //TODO JMS log

    @Override
    public List<PersonDTO> search(String term) {
        return null;
    }

}
