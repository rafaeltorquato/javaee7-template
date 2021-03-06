package study.wsapi.util;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import study.facade.exception.FacadeBusinessException;

import javax.ejb.EJBAccessException;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@ApplicationScoped
public class GlobalExceptionHandler {

    public ErrorMessage handle(Exception exception) {
        Throwable cause = getRootCause(exception);
        ErrorMessage.ErrorMessageBuilder builder = ErrorMessage.builder()
                .type(cause.getClass().getName())
                .message(exception.getMessage());
        if (cause instanceof FacadeBusinessException) {
            builder.code(Response.Status.BAD_REQUEST.getStatusCode());
        } else if (cause instanceof ConstraintViolationException) {
            builder.code(Response.Status.BAD_REQUEST.getStatusCode());
            List<ErrorMessage> errors = extractErrors((ConstraintViolationException) cause);
            builder.children(errors);
        } else if (cause instanceof EJBAccessException) {
            builder.code(Response.Status.FORBIDDEN.getStatusCode());
        } else {
            log.error(cause.getMessage(), exception);
        }
        return builder.build();
    }

    @Getter
    @Builder
    @ToString
    public static class ErrorMessage {
        private final String type;
        private final String message;
        private final String detail;
        private final Integer code;
        private final List<ErrorMessage> children;
    }

    private List<ErrorMessage> extractErrors(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        List<ErrorMessage> messages = new ArrayList<>(violations.size());
        for (ConstraintViolation<?> cv : violations) {
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message(cv.getMessage())
                    .type(cv.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName())
                    .detail(cv.getPropertyPath().toString())
                    .build();
            messages.add(errorMessage);
        }
        return messages;
    }

    private Throwable getRootCause(Throwable exception) {
        if(exception.getCause() != null) {
            return getRootCause(exception.getCause());
        }
        return exception;
    }
}

