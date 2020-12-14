package study.business.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
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

}
