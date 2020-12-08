package study.business.application.service;

import study.business.domain.model.Person;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Local
public interface PersonService {

    Person newPerson(NewPersonCommand person);

    Person editPerson(EditPersonCommand person);

    void deletePerson(@NotNull Long id);

    List<Person> list();

    class NewPersonCommand {

        private String name;
        private String email;
        private String surname;
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

    class EditPersonCommand extends NewPersonCommand {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

}

