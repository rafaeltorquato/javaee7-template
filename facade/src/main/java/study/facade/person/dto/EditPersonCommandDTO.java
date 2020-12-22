package study.facade.person.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditPersonCommandDTO extends NewPersonCommandDTO {
    private Long id;
}
