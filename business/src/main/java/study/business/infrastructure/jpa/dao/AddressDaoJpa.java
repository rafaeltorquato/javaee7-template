package study.business.infrastructure.jpa.dao;

import study.business.domain.model.address.Address;
import study.business.domain.model.address.AddressDao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class AddressDaoJpa implements AddressDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Address address) {
        if(em.contains(address)) {
            em.persist(address);
        } else {
            em.merge(address);
        }
    }

    @Override
    public void delete(Address address) {
        em.remove(address);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Address> list() {
        return em.createNamedQuery(Address.LIST_ALL)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Address> search(String term) {
        return em.createNamedQuery(Address.SEARCH_BY_TERM)
                .setParameter("term", "%" + term + "%")
                .getResultList();
    }

    @Override
    public Address findById(Long id) {
        Address address = em.find(Address.class, id);
        if(address != null) {
            em.lock(address, LockModeType.OPTIMISTIC);
        }
        return address;
    }
}
