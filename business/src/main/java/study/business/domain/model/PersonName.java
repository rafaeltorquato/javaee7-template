package study.business.domain.model;

import study.components.validation.NotEmpty;

import java.io.Serializable;

public class PersonName implements Serializable {

    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;

    public PersonName() {
    }

    public PersonName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
