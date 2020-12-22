package study.facade.person.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PhoneDTO implements Serializable {
    private Long id;
    private String countryCode;
    private String areaCode;
    private String number;
    private Integer version;
}
