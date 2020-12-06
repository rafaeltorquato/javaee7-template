package study.application.service.impl;

import study.application.service.AddressService;
import study.application.misc.Logged;
import study.domain.model.Address;
import study.domain.model.AddressDao;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Logged
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AddressServiceImpl implements AddressService {

    @Resource
    private UserTransaction tx;
    @EJB
    private AddressDao addressDao;

    @Override
    public Address save(Address address) {
        try {
            tx.begin();
            addressDao.save(address);
            tx.commit();
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (SystemException systemException) {
                throw new RuntimeException(systemException);
            }
        }
        return address;
    }

    @Override
    public void delete(Long id) {
        try {
            tx.begin();
            Address address = addressDao.findById(id);
            if(address != null) {
                addressDao.delete(address);
                tx.commit();
            }
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (SystemException systemException) {
                throw new RuntimeException(systemException);
            }
        }
    }
}
