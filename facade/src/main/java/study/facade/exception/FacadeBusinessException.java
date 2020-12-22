package study.facade.exception;

import lombok.Getter;

@Getter
public class FacadeBusinessException extends RuntimeException {

    private final String businessExceptionClassName;

    public FacadeBusinessException(Throwable cause) {
        super(cause);
        this.businessExceptionClassName = cause.getClass().getName();
    }
}
