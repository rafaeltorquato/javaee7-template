package study.business.domain.model.person;

public interface PersonRecipient {

    void notify(PersonDeletedEvent event);

    void notify(PersonSavedEvent event);

}
