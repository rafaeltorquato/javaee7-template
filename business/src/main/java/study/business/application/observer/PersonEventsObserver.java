package study.business.application.observer;

import lombok.extern.slf4j.Slf4j;
import study.business.application.service.EmailService;
import study.business.domain.model.person.Person;
import study.business.domain.model.person.PersonDeletedEvent;
import study.business.domain.model.person.PersonSavedEvent;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

@Slf4j
@Stateless
public class PersonEventsObserver {

    @EJB
    private EmailService emailService;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void whenDeleted(@Observes(during = TransactionPhase.AFTER_SUCCESS) PersonDeletedEvent event) {
        final Person person = event.getPerson();
        log.info("Sending goodbye e-mail to {}", person.getEmail());
//        emailService.send(
//                person.getEmail(),
//                "Goodbye",
//                "You was deleted!"
//        );
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void whenSaved(@Observes(during = TransactionPhase.AFTER_SUCCESS) PersonSavedEvent event) {
        final Person person = event.getPerson();
        log.info("Sending well come e-mail to {}", person.getEmail());
//        emailService.send(
//                person.getEmail(),
//                "Well come ",
//                "You was registered!"
//        );
    }

}
