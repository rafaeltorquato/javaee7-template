package study.business.application.service;

import study.business.domain.model.user.User;
import study.components.validation.NotEmpty;

import javax.validation.constraints.NotNull;

public interface UserService {

    User findByUsername(@NotNull @NotEmpty String username);

}
