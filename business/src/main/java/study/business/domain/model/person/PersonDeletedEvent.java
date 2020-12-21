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
public class PersonDeletedEvent implements Serializable {
    private String id = UUID.randomUUID().toString();
    private Person person;

    public PersonDeletedEvent(Person person) {
        this.person = person;
    }
}
