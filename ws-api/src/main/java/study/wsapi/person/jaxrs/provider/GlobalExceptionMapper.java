package study.wsapi.person.jaxrs.provider;

import study.wsapi.util.GlobalExceptionHandler;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Inject
    private GlobalExceptionHandler globalExceptionHandler;

    @Override
    public Response toResponse(Exception exception) {
        GlobalExceptionHandler.ErrorMessage errorMessage = globalExceptionHandler.handle(exception);
        return Response.status(errorMessage.getCode())
                .entity(errorMessage)
                .build();

    }
}
