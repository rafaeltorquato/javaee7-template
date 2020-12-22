package study.facade.person.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class RelationshipDTO implements Serializable {
    private PersonDTO person;
    private String type;
}
