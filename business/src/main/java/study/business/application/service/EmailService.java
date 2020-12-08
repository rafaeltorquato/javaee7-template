package study.business.application.service;

import javax.ejb.Local;
import java.util.concurrent.Future;

@Local
public interface EmailService {

    Boolean send(String email, String subject, String message);

    Future<Boolean> sendAsync(String email, String subject, String message);

    void queue(String email, String subject, String message);

}
