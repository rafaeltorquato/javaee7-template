package study.jsfview.controller.secure;

import lombok.Getter;
import lombok.Setter;
import study.facade.person.PersonFacade;
import study.facade.person.dto.PersonDTO;
import study.jsfview.controller.BaseController;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Collections;
import java.util.List;

@ViewScoped
@ManagedBean
public class PersonController extends BaseController {

    @EJB
    private PersonFacade personFacade;

    @Getter
    private List<PersonDTO> list = Collections.emptyList();

    @Getter
    @Setter
    private PersonDTO editedPerson;

    public void init() {
        if (this.list.isEmpty()) {
            this.list = personFacade.list();
        }
    }

    public void edit(PersonDTO person) {
        this.editedPerson = person;
    }

    public void delete(PersonDTO person) {
        this.personFacade.delete(person.getId());
        this.list.remove(person);
        if (person.equals(this.editedPerson)) {
            this.editedPerson = null;
        }
        addSuccessMessage("view_person_index_person_deleted", person.getName());
    }

}
