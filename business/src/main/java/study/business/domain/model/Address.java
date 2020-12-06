package study.business.domain.model;

import study.components.validation.NotEmpty;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-gen-address")
    @SequenceGenerator(name = "seq-gen-address", sequenceName = "seq-address", initialValue = 1, allocationSize = 100)
    private Long id;
    @NotEmpty
    @Column(length = 1000, nullable = false)
    private String value;
    @Version
    private Integer version;

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
}
