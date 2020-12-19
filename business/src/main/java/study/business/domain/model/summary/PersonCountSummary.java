package study.business.domain.model.summary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class PersonCountSummary implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private Long personId;
    @NotNull
    @Column(nullable = false)
    private Long addressesCount = 0L;
    @NotNull
    @Column(nullable = false)
    private Long phonesCount = 0L;
    @NotNull
    @Column(nullable = false)
    private Long relationshipsCount = 0L;

}
