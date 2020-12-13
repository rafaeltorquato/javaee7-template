package study.business.domain.model;

import study.business.infrastructure.jpa.AddressDaoJpa;
import study.components.validation.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;


@NamedQueries({
        @NamedQuery(
                name = Address.SEARCH_BY_TERM,
                query = "select a from Address a where a.value like :term"
        ),
        @NamedQuery(
                name = Address.LIST_ALL,
                query = "select a from Address a"
        )
})
@Entity
public class Address {

    public static final String SEARCH_BY_TERM = "Address.searchByTerm";
    public static final String LIST_ALL = "Address.listAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(length = 1000, nullable = false)
    private String value;
    @Version
    private Integer version;
    @NotNull
    @Column(length = 1000, nullable = false)
    private Date registerDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
