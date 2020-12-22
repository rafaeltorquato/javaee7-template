package study.business.application.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import study.business.domain.model.address.Address;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;
import java.util.List;

@Local
public interface AddressService {

    void save(NewAddressCommand command);

    void edit(EditAddressCommand command);

    void delete(@NotNull Long id);

    List<Address> list();


    @ToString
    @Getter
    @Setter
    class NewAddressCommand {
        private String value;
    }

    @ToString
    @Getter
    @Setter
    class EditAddressCommand extends NewAddressCommand {
        private Long id;
    }

    class AddressNotFoundException extends BusinessException {
        public AddressNotFoundException() {
        }

        public AddressNotFoundException(String message) {
            super(message);
        }
    }

}

