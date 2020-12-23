package study.facade.person.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDTO implements Serializable {

    @XmlElement
    private Long id;
    @XmlElement
    private String name;
    @XmlElement
    private String surname;
    @XmlElement
    private String email;
    @XmlElement
    private Date birthDate;
    @XmlElement
    private Date registerDateTime;
    @XmlElement
    private Integer version;
    @XmlElement
    private Set<PhoneDTO> phones = new HashSet<>();
    @XmlElement
    private Set<RelationshipDTO> relationships = new HashSet<>();

    public void setRelationships(Set<RelationshipDTO> relationships) {
        if(relationships != null) {
            this.relationships = relationships;
        }
    }

    public void setPhones(Set<PhoneDTO> phones) {
        if(phones != null) {
            this.phones = phones;
        }
    }
}
