package study.domain.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Relationship {
    @Valid
    @EmbeddedId
    @NotNull
    private RelationshipId id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @ManyToOne
    @JoinColumn(name = "sourceId", referencedColumnName = "id")
    private Person source;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "targetId", referencedColumnName = "id")
    private Person target;
    @NotNull
    @Column(nullable = false)
    private RelationshipType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relationship that = (Relationship) o;
        return id.equals(that.id) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    //Javabeans specification

    public RelationshipId getId() {
        return id;
    }

    public void setId(RelationshipId id) {
        this.id = id;
    }

    public Person getSource() {
        return source;
    }

    public void setSource(Person source) {
        this.source = source;
    }

    public Person getTarget() {
        return target;
    }

    public void setTarget(Person target) {
        this.target = target;
    }

    public RelationshipType getType() {
        return type;
    }

    public void setType(RelationshipType type) {
        this.type = type;
    }
}
