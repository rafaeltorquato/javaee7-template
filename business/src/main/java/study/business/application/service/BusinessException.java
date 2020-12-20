package study.business.application.service;

import javax.ejb.ApplicationException;

@ApplicationException
public class BusinessException extends RuntimeException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }
}
