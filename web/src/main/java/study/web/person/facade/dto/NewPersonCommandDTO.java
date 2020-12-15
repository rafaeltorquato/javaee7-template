package study.web.person.facade.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class NewPersonCommandDTO implements Serializable {
    private String name;
    private String email;
    private String surname;
    private Date birthDate;
}
