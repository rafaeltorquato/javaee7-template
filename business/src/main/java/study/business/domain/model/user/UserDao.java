package study.business.domain.model.user;

public interface UserDao {

    User findByUsername(String username);

    User save(User user);

}
