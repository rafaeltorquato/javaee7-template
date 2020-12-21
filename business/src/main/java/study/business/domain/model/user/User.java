package study.business.domain.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = User.FIND_BY_USERNAME,
                query = "select " +
                        "   a " +
                        "from " +
                        "   User a " +
                        "   left join fetch a.groups g " +
                        "   left join fetch g.roles gr " +
                        "   left join fetch a.roles r " +
                        "where " +
                        "   a.username = :username"
        )
})
//"select u.username, r.role from AuthUser u inner join AuthUserRoles ur on ur.AuthUser_id = u.id where u.username=?"
@Entity
@Table(name = "AuthUser")
@Getter
@Setter
public class User {

    public static final String FIND_BY_USERNAME = "User.FIND_BY_USERNAME";

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToMany
    @JoinTable(name = "UserAuthGroup")
    private Set<Group> groups = new HashSet<>();
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "AuthUserRoles")
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getAllRoles() {
        final HashSet<Role> roles = new HashSet<>(this.roles);
        for (Group g : groups) {
            roles.addAll(g.getRoles());
        }
        return roles;
    }

}
