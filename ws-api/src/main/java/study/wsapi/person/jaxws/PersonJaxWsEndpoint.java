package study.wsapi.person.jaxws;

import study.facade.person.PersonFacade;
import study.facade.person.dto.NewPersonCommandDTO;
import study.facade.person.dto.PersonDTO;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class PersonJaxWsEndpoint {

    @EJB
    private PersonFacade personFacade;

    @WebMethod
    public PersonDTO save(NewPersonCommandDTO dto) {
        return personFacade.save(dto);
    }

    @WebMethod
    public List<PersonDTO> list() {
        return personFacade.list();
    }

}
