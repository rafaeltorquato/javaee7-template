package study.business.application.service.impl;

import study.business.domain.model.person.Person;
import study.business.domain.model.person.PersonDao;
import study.business.domain.model.person.PersonName;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Date;

@Startup
@Singleton
public class StartupPersons {

    @EJB
    private PersonDao personDao;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 40; i++) {
            Person person = new Person();
            person.setName(new PersonName("Rafael" + i, "Torquato"));
            person.setEmail("rafael" + i + "@gmail.com");
            person.setBirthDate(new Date());
            personDao.save(person);
        }
    }
}
