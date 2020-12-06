package study.business.infrastructure.jpa;

import study.business.domain.model.Address;
import study.business.domain.model.AddressDao;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = AddressDaoJpa.SEARCH_BY_TERM,
                query = "select a from Address a where a.value like :term"
        ),
        @NamedQuery(
                name = AddressDaoJpa.LIST_ALL,
                query = "select a from Address"
        )
})
@Stateless
public class AddressDaoJpa implements AddressDao {

    public static final String SEARCH_BY_TERM = "Address.searchByTerm";
    public static final String LIST_ALL = "Address.listAll";

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
        return em.createNamedQuery(LIST_ALL)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Address> search(String term) {
        return em.createNamedQuery(SEARCH_BY_TERM)
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
