package study.facade.address;

import study.facade.address.dto.AddressDTO;
import study.facade.address.dto.EditAddressCommandDTO;
import study.facade.address.dto.NewAddressCommandDTO;

import java.util.List;

public interface AddressFacade {

    void save(NewAddressCommandDTO command);

    void edit(EditAddressCommandDTO command);

    void delete(Long id);

    List<AddressDTO> list();

}

