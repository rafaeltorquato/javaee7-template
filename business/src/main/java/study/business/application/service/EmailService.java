package study.business.application.service;

import study.components.validation.NotEmpty;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;
import java.util.concurrent.Future;

@Local
public interface EmailService {

    Boolean send(@NotNull @NotEmpty String email, @NotNull @NotEmpty String subject, @NotNull @NotEmpty String message);

    Future<Boolean> sendAsync(@NotNull @NotEmpty String email, @NotNull @NotEmpty String subject, @NotNull @NotEmpty String message);

    void queue(@NotNull @NotEmpty String email, @NotNull @NotEmpty String subject, @NotNull @NotEmpty String message);

}
