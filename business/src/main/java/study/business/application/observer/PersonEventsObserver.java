package study.business.application.observer;

import lombok.extern.slf4j.Slf4j;
import study.business.application.service.EmailService;
import study.business.domain.model.person.Person;
import study.business.domain.model.person.PersonDeletedEvent;
import study.business.domain.model.person.PersonSavedEvent;
import study.business.domain.model.summary.PersonCountSummary;
import study.business.domain.model.summary.PersonCountSummaryDao;

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
    @EJB
    private PersonCountSummaryDao personCountSummaryDao;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void whenDeleted(@Observes(during = TransactionPhase.AFTER_SUCCESS) PersonDeletedEvent event) {
        final Person person = event.getPerson();
        log.info("Sending goodbye e-mail to {}", person.getEmail());
        PersonCountSummary summary = personCountSummaryDao.findById(person.getId());
        if(summary != null) {
            personCountSummaryDao.delete(summary);
        }
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
