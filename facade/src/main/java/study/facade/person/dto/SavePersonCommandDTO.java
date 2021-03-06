package study.facade.person.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SavePersonCommandDTO implements Serializable {
    @XmlElement
    private String name;
    @XmlElement
    private String email;
    @XmlElement
    private String surname;
    @XmlElement
    private Date birthDate;
}
