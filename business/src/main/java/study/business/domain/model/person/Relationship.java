package study.business.domain.model.person;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "type"})
public class Relationship implements Serializable {
    @Valid
    @EmbeddedId
    @NotNull
    private RelationshipId id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @ManyToOne
    @JoinColumn(name = "sourceId", referencedColumnName = "id", insertable = false, updatable = false)
    private Person source;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "targetId", referencedColumnName = "id", insertable = false, updatable = false)
    private Person target;
    @NotNull
    @Column(nullable = false)
    private RelationshipType type;

    public Relationship(Person source, Person target, RelationshipType type) {
        this.source = source;
        this.target = target;
        this.type = type;
        this.id = new RelationshipId(source.getId(), target.getId());
    }
}
