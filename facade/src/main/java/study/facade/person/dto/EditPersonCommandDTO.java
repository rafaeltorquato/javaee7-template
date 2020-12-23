package study.facade.person.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditPersonCommandDTO extends SavePersonCommandDTO {
    private Long id;
    private Integer version;
}
