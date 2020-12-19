package study.business.application.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import study.business.domain.model.address.Address;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

@Local
public interface AddressService {

    Address newAddress(NewAddressCommand command);

    void delete(@NotNull Long id);


    @ToString
    @Getter
    @Setter
    class NewAddressCommand {
        private String value;
    }

}
