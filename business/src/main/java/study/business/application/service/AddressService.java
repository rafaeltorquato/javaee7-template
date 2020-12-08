package study.business.application.service;

import study.business.domain.model.Address;

import javax.ejb.Local;

@Local
public interface AddressService {

    Address newAddress(NewAddressCommand command);

    void delete(Long id);


    class NewAddressCommand {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
