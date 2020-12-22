package study.facade.person.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class DeletePhoneCommandDTO implements Serializable {
    private Long personId;
    private Long phoneId;
}

