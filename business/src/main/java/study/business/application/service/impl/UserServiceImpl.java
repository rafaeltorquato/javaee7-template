package study.business.application.service.impl;

import lombok.SneakyThrows;
import study.business.application.service.UserService;
import study.business.domain.model.user.Role;
import study.business.domain.model.user.User;
import study.business.domain.model.user.UserDao;
import study.components.interceptor.Logged;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.*;

import static study.business.domain.model.user.Role.*;

@Logged
@Startup
@Singleton
public class UserServiceImpl implements UserService {

    @EJB
    private UserDao userDao;

    @PostConstruct
    @SneakyThrows
    public void init() {
        final Map<String, Set<Role>> map = new HashMap<>();
        map.put("torquato", new HashSet<>(Arrays.asList(AUTHENTICATED, ADMINISTRATOR)));
        map.put("torquato2", new HashSet<>(Arrays.asList(AUTHENTICATED, ADD_ADDRESS, LIST_ADDRESS, DELETE_ADDRESS)));
        map.put("torquato3", new HashSet<>(Arrays.asList(AUTHENTICATED, ADD_PERSON, DELETE_PERSON, LIST_PERSON)));
        for (Map.Entry<String, Set<Role>> entry : map.entrySet()) {
            final String adminName = entry.getKey();
            User admin = userDao.findByUsername(adminName);
            if (admin == null) {
                admin = new User();
                admin.getRoles().addAll(entry.getValue());
                admin.setUsername(adminName);
                admin.setName("Rafael Torquato");
                final MessageDigest md = MessageDigest.getInstance("MD5");
                md.update("123456".getBytes());
                final byte[] digest = md.digest();
                final String password = DatatypeConverter.printBase64Binary(digest);
                admin.setPassword(password);
                userDao.save(admin);
            }
        }
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
