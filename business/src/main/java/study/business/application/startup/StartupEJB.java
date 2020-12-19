package study.business.application.startup;

import study.business.domain.model.administrator.Administrator;
import study.business.domain.model.administrator.Role;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Startup
@Singleton
public class StartupEJB {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        try {
            Administrator administrator = new Administrator();
            administrator.getRoles().add(Role.FULL);
            administrator.setUsername("torquato");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update("123456".getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            administrator.setPassword(myHash);
            entityManager.persist(administrator);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No admininistrator has been configured", e);
        }
    }

}
