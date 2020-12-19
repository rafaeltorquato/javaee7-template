package study.business.domain.model.person;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
@Getter
@Setter
public class RelationshipId implements Serializable {
    @NotNull
    private Long sourceId;
    @NotNull
    private Long targetId;

    public RelationshipId() {
    }

    public RelationshipId(Long sourceId, Long targetId) {
        this.sourceId = sourceId;
        this.targetId = targetId;
    }
}
