package study.jsfview.controller.secure;

import lombok.Getter;
import lombok.Setter;
import study.facade.person.PersonFacade;
import study.facade.person.dto.PersonDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@ViewScoped
@ManagedBean
public class PersonController implements Serializable {

    @EJB
    private PersonFacade personFacade;

    @Getter
    private List<PersonDTO> list = Collections.emptyList();

    @Getter
    @Setter
    private PersonDTO editedPerson;

    public void init() {
        if(list.isEmpty()) {
            list = personFacade.list();
        }
    }

    public void edit(PersonDTO person) {
        this.editedPerson = person;
    }

    public void delete(PersonDTO person) {
        personFacade.delete(person.getId());
        list.remove(person);
        if(this.editedPerson != null && this.editedPerson.equals(person)) {
            this.editedPerson = null;
        }
    }

}
