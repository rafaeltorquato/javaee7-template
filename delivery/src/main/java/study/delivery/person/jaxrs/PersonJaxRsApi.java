package study.delivery.person.jaxrs;

import study.delivery.person.facade.PersonFacade;
import study.delivery.person.facade.dto.EditPersonCommandDTO;
import study.delivery.person.facade.dto.NewPersonCommandDTO;
import study.delivery.person.facade.dto.PersonDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonJaxRsApi {

    @Context
    private UriInfo uriInfo;

    @Inject
    private PersonFacade personFacade;

    @GET
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
