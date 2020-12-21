package study.business.domain.model.person;

import lombok.Getter;
import lombok.Setter;
import study.business.domain.converter.PersonNameConverter;
import study.business.domain.model.address.Address;
import study.components.validation.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = Person.LIST_ALL,
                query = "select " +
                        "   distinct p " +
                        "from " +
                        "   Person p " +
                        "left join fetch p.phones " +
                        "left join fetch p.relationships " +
                        "left join fetch p.addresses " +
                        "order by " +
                        "   p.registerDateTime, " +
                        "   p.id"
        )
})
@Entity
@Getter
@Setter
public class Person implements Serializable {

    public static final String LIST_ALL = "Person.listAll";

    @Id
    @GeneratedValue
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
    //Usually address domain isn't be here.
    @Valid
    @ManyToMany
    @JoinTable(name = "PersonAddress")
    private Set<Address> addresses = new HashSet<>();


    @PrePersist
    protected void prePersist() {
        this.registerDateTime = new Date();
    }

}
