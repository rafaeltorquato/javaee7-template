package study.delivery.person.jaxrs.provider;

import study.delivery.util.GlobalErrorMessageHandler;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Inject
    private GlobalErrorMessageHandler globalErrorMessageHandler;

    @Override
    public Response toResponse(Exception exception) {
        GlobalErrorMessageHandler.ErrorMessage errorMessage = globalErrorMessageHandler.handle(exception);
        return Response.status(errorMessage.getCode())
                .entity(errorMessage)
                .build();

    }
}
