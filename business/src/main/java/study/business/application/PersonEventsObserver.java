package study.business.application;

import study.business.application.evt.PersonDeletedEvent;
import study.business.application.service.EmailService;
import study.business.domain.model.Person;
import study.business.domain.model.PersonDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

@Stateless
public class PersonEventsObserver {

    @EJB
    private EmailService emailService;
    @EJB
    private PersonDao personDao;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void whenDeleted(@Observes(during = TransactionPhase.AFTER_COMPLETION) PersonDeletedEvent event) {
        Person person = personDao.findById(event.getId());
        //TODO Send Goodbye email message
    }

}
