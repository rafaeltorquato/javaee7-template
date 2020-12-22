package study.facade.person.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SavePhoneCommandDTO implements Serializable {
    private Long ownerPersonId;
    private String countryCode;
    private String areaCode;
    private String number;
}
