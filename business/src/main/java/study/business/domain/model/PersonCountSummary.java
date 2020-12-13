package study.business.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getAddressesCount() {
        return addressesCount;
    }

    public void setAddressesCount(Long addressesCount) {
        this.addressesCount = addressesCount;
    }

    public Long getPhonesCount() {
        return phonesCount;
    }

    public void setPhonesCount(Long phonesCount) {
        this.phonesCount = phonesCount;
    }

    public Long getRelationshipsCount() {
        return relationshipsCount;
    }

    public void setRelationshipsCount(Long relationshipsCount) {
        this.relationshipsCount = relationshipsCount;
    }
}
