package study.business.domain.model.administrator;

public interface AdministratorDao {

    Administrator findByUsername(String username);

    Administrator save(Administrator administrator);

}
