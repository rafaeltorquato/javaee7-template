package study.business.infrastructure.jpa;

import study.business.domain.model.administrator.Administrator;
import study.business.domain.model.administrator.AdministratorDao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AdministratorDaoJpa implements AdministratorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Administrator findByUsername(String username) {
        Administrator administrator = null;
        try {
            administrator =  (Administrator) entityManager.createNamedQuery(Administrator.FIND_BY_USERNAME)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException ignored) {
        }
        return administrator;
    }

    @Override
    public Administrator save(Administrator administrator) {
        entityManager.persist(administrator);
        return administrator;
    }
}
