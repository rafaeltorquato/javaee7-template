package study.business.application.service;

import study.business.domain.model.Address;

import javax.ejb.Local;

@Local
public interface AddressService {

    Address save(Address address);

    void delete(Long id);

}
