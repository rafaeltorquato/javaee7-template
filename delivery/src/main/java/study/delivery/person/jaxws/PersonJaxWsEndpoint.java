package study.delivery.person.jaxws;

import study.delivery.person.facade.PersonFacade;
import study.delivery.person.facade.dto.NewPersonCommandDTO;
import study.delivery.person.facade.dto.PersonDTO;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class PersonJaxWsEndpoint {

    @Inject
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
