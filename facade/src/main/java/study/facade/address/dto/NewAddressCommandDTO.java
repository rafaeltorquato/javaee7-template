package study.facade.address.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class NewAddressCommandDTO implements Serializable {
    private String value;
}
