package study.business.application.service;

import study.business.domain.model.administrator.Administrator;
import study.components.validation.NotEmpty;

import javax.validation.constraints.NotNull;

public interface AdministratorService {

    Administrator findByUsername(@NotNull @NotEmpty String username);

}
