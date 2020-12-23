package study.business.application.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import study.business.domain.model.person.Person;
import study.business.domain.model.person.RelationshipType;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Local
public interface PersonService {

    Person save(SavePersonCommand command);

    Person edit(EditPersonCommand command);

    void delete(@NotNull Long id);

    List<Person> list();

    Person saveRelationship(SaveRelationshipCommand command);

    Person deleteRelationship(DeleteRelationshipCommand command);

    Person savePhone(SavePhoneCommand command);

    Person deletePhone(DeletePhoneCommand command);

    Person get(Long id);

    @ToString(of = {"name", "surname", "email"})
    class SavePersonCommand {

        private String name;
        private String surname;
        private String email;
        private Date birthDate;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    class EditPersonCommand extends SavePersonCommand {
        private Long id;
        private Integer version;
    }

    @Getter
    @Setter
    @ToString
    class SavePhoneCommand {
        private Long ownerPersonId;
        private String countryCode;
        private String areaCode;
        private String number;
    }

    @Getter
    @Setter
    @ToString
    class SaveRelationshipCommand {
        private Long sourcePersonId;
        private Long targetPersonId;
        private RelationshipType relationshipType;
    }

    @Getter
    @Setter
    @ToString
    class DeleteRelationshipCommand {
        private Long sourcePersonId;
        private Long targetPersonId;
    }

    @Getter
    @Setter
    @ToString
    class DeletePhoneCommand {
        private Long ownerPersonId;
        private Long phoneId;
    }

    class PersonNotFoundException extends BusinessException {
        public PersonNotFoundException() {
        }

        public PersonNotFoundException(String message) {
            super(message);
        }
    }

    class RelationshipNotFound extends BusinessException {
        public RelationshipNotFound() {
        }

        public RelationshipNotFound(String message) {
            super(message);
        }
    }

    class PhoneNotFound extends BusinessException {
        public PhoneNotFound() {
        }

        public PhoneNotFound(String message) {
            super(message);
        }
    }

}

