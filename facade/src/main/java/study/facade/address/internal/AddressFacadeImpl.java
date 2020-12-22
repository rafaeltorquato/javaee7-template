package study.facade.address.internal;

import study.business.application.service.AddressService;
import study.business.application.service.BusinessException;
import study.business.domain.model.address.Address;
import study.facade.address.AddressFacade;
import study.facade.address.dto.AddressDTO;
import study.facade.address.dto.EditAddressCommandDTO;
import study.facade.address.dto.NewAddressCommandDTO;
import study.facade.address.mapper.AddressMapper;
import study.facade.exception.FacadeBusinessException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AddressFacadeImpl implements AddressFacade {

    @EJB
    private AddressService addressService;
    @Inject
    private AddressMapper addressMapper;

    @Override
    public void save(NewAddressCommandDTO command) {
        try {
            AddressService.NewAddressCommand cmd = addressMapper.fromDTO(command);
            addressService.save(cmd);
        } catch (BusinessException exception) {
            throw new FacadeBusinessException(exception);
        }
    }

    @Override
    public void edit(EditAddressCommandDTO command) {
        try {
            AddressService.EditAddressCommand cmd = addressMapper.fromDTO(command);
            addressService.edit(cmd);
        } catch (BusinessException exception) {
            throw new FacadeBusinessException(exception);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            addressService.delete(id);
        } catch (BusinessException exception) {
            throw new FacadeBusinessException(exception);
        }
    }

    @Override
    public List<AddressDTO> list() {
        try {
            List<Address> list = addressService.list();
            return addressMapper.toDTOList(list);
        } catch (BusinessException exception) {
            throw new FacadeBusinessException(exception);
        }
    }
}
