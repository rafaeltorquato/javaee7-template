package study.business.domain.model;

import study.business.domain.converter.PersonNameConverter;
import study.business.infrastructure.jpa.PersonDaoJpa;
import study.components.validation.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = Person.LIST_ALL,
                query = "select p from Person p join fetch p.phones join fetch p.relationships join fetch p.addresses"
        )
})
@Entity
public class Person {

    public static final String LIST_ALL = "Person.listAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Valid
    @NotNull
    @Column(length = 150, nullable = false)
    @Convert(converter = PersonNameConverter.class)
    private PersonName name;
    @NotEmpty
    @Column(length = 200, nullable = false)
    private String email;
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthDate;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date registerDateTime;
    @Valid
    @Version
    @Column(nullable = false)
    private Integer version;
    @Valid
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Phone> phones = new HashSet<>();
    @Valid
    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL)
    private Set<Relationship> relationships = new HashSet<>();
    @Valid
    @ManyToMany
    @JoinTable(
            name = "person_address",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses = new HashSet<>();


    @PrePersist
    protected void prePersist() {
        this.registerDateTime = new Date();
    }

    //JavaBean specification


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonName getName() {
        return name;
    }

    public void setName(PersonName name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(Date registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public Set<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(Set<Relationship> relationships) {
        this.relationships = relationships;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
