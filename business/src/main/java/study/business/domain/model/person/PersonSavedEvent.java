package study.business.domain.model.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PersonSavedEvent implements Serializable {
    private String id = UUID.randomUUID().toString();
    private Person person;

    public PersonSavedEvent(Person person) {
        this.person = person;
    }
}
