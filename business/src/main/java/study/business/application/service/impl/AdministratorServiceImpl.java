package study.business.application.service.impl;

import study.business.application.service.AdministratorService;
import study.business.domain.model.administrator.Administrator;
import study.business.domain.model.administrator.AdministratorDao;
import study.business.domain.model.administrator.Role;
import study.components.interceptor.Logged;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Logged
@Startup
@Singleton
public class AdministratorServiceImpl implements AdministratorService {

    @EJB
    private AdministratorDao administratorDao;

    @PostConstruct
    public void init() {
        try {
            String adminName = "torquato";
            Administrator admin = administratorDao.findByUsername(adminName);
            if (admin == null) {
                admin = new Administrator();
                admin.getRoles().add(Role.FULL);
                saveAdmin(adminName, admin);
            }
            adminName = "torquato2";
            Administrator admin2 = administratorDao.findByUsername(adminName);
            if (admin2 == null) {
                admin2 = new Administrator();
                admin2.getRoles().add(Role.ADDRESS);
                saveAdmin(adminName, admin2);
            }
            adminName = "torquato3";
            Administrator admin3 = administratorDao.findByUsername(adminName);
            if (admin3 == null) {
                admin3 = new Administrator();
                admin3.getRoles().add(Role.PERSON);
                saveAdmin(adminName, admin3);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No administrator has been configured!", e);
        }
    }

    @Override
    public Administrator findByUsername(String username) {
        return administratorDao.findByUsername(username);
    }

    private void saveAdmin(String adminName, Administrator admin) throws NoSuchAlgorithmException {
        admin.setUsername(adminName);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update("123456".getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        admin.setPassword(myHash);
        administratorDao.save(admin);
    }
}
