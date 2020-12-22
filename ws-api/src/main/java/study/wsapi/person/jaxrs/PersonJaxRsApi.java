package study.wsapi.person.jaxrs;


import study.facade.person.PersonFacade;
import study.facade.person.dto.EditPersonCommandDTO;
import study.facade.person.dto.NewPersonCommandDTO;
import study.facade.person.dto.PersonDTO;

import javax.annotation.security.DenyAll;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/persons")
@DenyAll
@Produces(MediaType.APPLICATION_JSON)
public class PersonJaxRsApi {

    @Context
    private UriInfo uriInfo;
    @EJB
    private PersonFacade personFacade;

    @GET
    @DenyAll
    public List<PersonDTO> list() {
        return personFacade.list();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        personFacade.delete(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(NewPersonCommandDTO command) {
        PersonDTO save = personFacade.save(command);
        URI absolutePath = uriInfo.getAbsolutePath();
        URI newURI = absolutePath.resolve(save.getId().toString());
        return Response
                .created(newURI)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public PersonDTO edit(@PathParam("id") Long id, EditPersonCommandDTO command) {
        command.setId(id);
        return personFacade.edit(command);
    }

}
