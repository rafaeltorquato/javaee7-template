package study.business.application.service.impl;

import study.business.application.service.AddressService;
import study.business.domain.model.address.Address;
import study.business.domain.model.address.AddressDao;
import study.components.interceptor.Logged;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;

@Logged
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AddressServiceImpl implements AddressService {

    @Resource
    private UserTransaction tx;
    @EJB
    private AddressDao addressDao;

    @Override
    @RolesAllowed({"ADMINISTRATOR", "SAVE_ADDRESS"})
    public Address save(NewAddressCommand command) {
        try {
            tx.begin();
            final Address address = new Address();
            address.setValue(command.getValue());
            addressDao.save(address);
            tx.commit();
            return address;
        } catch (Exception e) {
            rollback(e);
            return null; //this never happen
        }
    }

    @Override
    @RolesAllowed({"ADMINISTRATOR", "EDIT_ADDRESS"})
    public Address edit(EditAddressCommand command) {
        try {
            tx.begin();
            final Address address = addressDao.findById(command.getId());
            if (address == null) throw new AddressNotFoundException();

            address.setValue(command.getValue());
            addressDao.save(address);
            tx.commit();
            return address;
        } catch (Exception e) {
            rollback(e);
            return null; //this never happen
        }
    }

    @Override
    @RolesAllowed({"ADMINISTRATOR", "DELETE_ADDRESS"})
    public void delete(Long id) {
        try {
            tx.begin();
            final Address address = addressDao.findById(id);
            if (address == null) throw new AddressNotFoundException();

            addressDao.delete(address);
            tx.commit();
        } catch (Exception e) {
            rollback(e);
        }
    }

    @Override
    @RolesAllowed({"ADMINISTRATOR", "LIST_ADDRESS"})
    public List<Address> list() {
        try {
            tx.begin();
            List<Address> list = addressDao.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            rollback(e);
            return null; //this never happen
        }
    }

    private void rollback(Exception e) {
        try {
            tx.rollback();
            throw new RuntimeException(e);
        } catch (SystemException systemException) {
            throw new RuntimeException(systemException);
        }
    }
}
