package study.business.domain.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "AuthGroup")
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "AuthGropuRoles")
    private Set<Role> roles = new HashSet<>();

}
