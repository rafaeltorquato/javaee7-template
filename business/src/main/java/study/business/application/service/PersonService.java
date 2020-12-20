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

    Person save(NewPersonCommand command);

    Person edit(EditPersonCommand command);

    void delete(@NotNull Long id);

    List<Person> list();

    void addRelationship(AddRelationshipCommand command);

    void addPhone(AddPhoneCommand command);

    @ToString(of = {"name", "surname", "email"})
    class NewPersonCommand {

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

    @ToString(of = {"id"}, callSuper = true)
    class EditPersonCommand extends NewPersonCommand {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    @Getter
    @Setter
    @ToString
    class AddPhoneCommand {
        private Long ownerPersonId;
        private String countryCode;
        private String areaCode;
        private String number;
    }

    @Getter
    @Setter
    @ToString
    class AddRelationshipCommand {
        @NotNull
        private Long sourcePersonId;
        @NotNull
        private Long targetPersonId;
        @NotNull
        private RelationshipType relationshipType;
    }

    class PersonNotFoundException extends BusinessException {
        public PersonNotFoundException() {
        }

        public PersonNotFoundException(String message) {
            super(message);
        }
    }

}

