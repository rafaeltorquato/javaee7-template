package study.domain.model;

import study.validation.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 1, max = 3)
    @Column(length = 3, nullable = false)
    private String countryCode;
    @NotEmpty
    @Size(min = 1, max = 5)
    @Column(length = 5, nullable = false)
    private String areaCode;
    @NotEmpty
    @Size(min = 5, max = 10)
    @Column(length = 10, nullable = false)
    private String number;
    @Version
    @Column(nullable = false)
    private Integer version;
    @NotNull
    @ManyToOne
    private Person owner;

    //JavaBean specification
    public Long getId() {
        return id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
