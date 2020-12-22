package study.facade.person.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SaveRelationshipCommandDTO implements Serializable {
    private Long sourcePersonId;
    private Long targetPersonId;
    private String relationshipType;
}
