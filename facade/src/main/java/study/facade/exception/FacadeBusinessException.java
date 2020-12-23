package study.facade.exception;

import lombok.Getter;

import javax.ejb.ApplicationException;

@Getter
@ApplicationException
public class FacadeBusinessException extends RuntimeException {

    private final String businessExceptionClassName;

    public FacadeBusinessException(Throwable cause) {
        super(cause);
        this.businessExceptionClassName = cause.getClass().getName();
    }
}
