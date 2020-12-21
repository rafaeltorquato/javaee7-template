package study.business.infrastructure.jpa.dao;

import study.business.domain.model.user.User;
import study.business.domain.model.user.UserDao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UserDaoJpa implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            user =  (User) entityManager.createNamedQuery(User.FIND_BY_USERNAME)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException ignored) {
        }
        return user;
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }
}
