package study.business.domain.model.administrator;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = Administrator.FIND_BY_USERNAME,
                query = "select " +
                        "   a " +
                        "from " +
                        "   Administrator a " +
                        "   join fetch a.roles " +
                        "where " +
                        "   a.username = :username"
        )
})
@Getter
@Setter
@Entity
public class Administrator {

    public static final String FIND_BY_USERNAME = "Administrator.FIND_BY_USERNAME";

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "Roles")
    private Set<Role> roles = new HashSet<>();

}
