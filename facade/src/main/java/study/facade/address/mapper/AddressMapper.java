package study.facade.address.mapper;

import org.mapstruct.Mapper;
import study.business.application.service.AddressService;
import study.business.domain.model.address.Address;
import study.facade.address.dto.AddressDTO;
import study.facade.address.dto.EditAddressCommandDTO;
import study.facade.address.dto.NewAddressCommandDTO;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface AddressMapper {

    AddressService.NewAddressCommand fromDTO(NewAddressCommandDTO command);

    AddressService.EditAddressCommand fromDTO(EditAddressCommandDTO command);

    List<AddressDTO> toDTOList(List<Address> address);

}
