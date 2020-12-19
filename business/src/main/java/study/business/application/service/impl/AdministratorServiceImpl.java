package study.business.application.service.impl;

import lombok.SneakyThrows;
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
import java.util.HashMap;
import java.util.Map;

@Logged
@Startup
@Singleton
public class AdministratorServiceImpl implements AdministratorService {

    @EJB
    private AdministratorDao administratorDao;

    @PostConstruct
    @SneakyThrows
    public void init() {
        Map<String, Role> map = new HashMap<>();
        map.put("torquato", Role.FULL);
        map.put("torquato2", Role.ADDRESS);
        map.put("torquato3", Role.PERSON);
        for(Map.Entry<String, Role> entry: map.entrySet()) {
            String adminName = entry.getKey();
            Administrator admin = administratorDao.findByUsername(adminName);
            if (admin == null) {
                admin = new Administrator();
                admin.getRoles().add(entry.getValue());
                admin.setUsername(adminName);
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update("123456".getBytes());
                byte[] digest = md.digest();
                String password = DatatypeConverter.printHexBinary(digest).toUpperCase();
                admin.setPassword(password);
                administratorDao.save(admin);
            }
        }
    }

    @Override
    public Administrator findByUsername(String username) {
        return administratorDao.findByUsername(username);
    }

}
