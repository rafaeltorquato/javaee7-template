package study.web.person.facade.dto;

import lombok.Getter;
import lombok.Setter;
import study.business.domain.model.PersonName;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class PersonDTO implements Serializable {

    private Long id;
    private PersonName name;
    private String email;
    private Date birthDate;
    private Date registerDateTime;
    private Integer version;
    
}
