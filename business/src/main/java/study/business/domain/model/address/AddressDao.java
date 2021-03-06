package study.business.domain.model.address;

import java.util.List;

public interface AddressDao {

    void save(Address address);

    void delete(Address address);

    List<Address> list();

    List<Address> search(String term);

    Address findById(Long id);
}
