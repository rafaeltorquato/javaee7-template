package study.business.application.evt;

import java.io.Serializable;

public class PersonDeletedEvent implements Serializable {

    private Long id;

    public PersonDeletedEvent() {
    }

    public PersonDeletedEvent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
