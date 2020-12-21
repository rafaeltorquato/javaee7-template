package study.business.application.recipient;

import study.business.domain.model.person.PersonDeletedEvent;
import study.business.domain.model.person.PersonRecipient;
import study.business.domain.model.person.PersonSavedEvent;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Stateless
public class PersonRecipientProducer implements PersonRecipient {

    @Inject
    private Event<PersonDeletedEvent> personDeletedEvent;
    @Inject
    private Event<PersonSavedEvent> personSavedEventEvent;

    @Override
    public void notify(PersonDeletedEvent event) {
        personDeletedEvent.fire(event);
    }

    @Override
    public void notify(PersonSavedEvent event) {
        personSavedEventEvent.fire(event);
    }
}
