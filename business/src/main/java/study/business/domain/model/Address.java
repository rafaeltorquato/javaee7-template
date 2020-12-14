package study.business.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import study.components.validation.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


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
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Address implements Serializable {

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

}
