package study.business.application.jobs;


import study.business.application.service.EmailService;
import study.business.application.service.PersonService;
import study.business.domain.model.Person;
import study.components.DateTimeFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class SendPersonSummaryJob {

    @EJB
    private PersonService personService;
    @EJB
    private EmailService emailService;
    @Resource
    private TimerService timerService;
    @Inject
    private DateTimeFactory dateTimeFactory;

    @PostConstruct
    public void initialize() {
        ScheduleExpression expression = new ScheduleExpression()
                .dayOfMonth("*")
                .hour("00")
                .minute("0")
                .second("0");
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo("Send Person Daily Summary.");
        timerService.createCalendarTimer(expression, timerConfig);
    }


    @Timeout
    public void timeout(Timer timer) {
        Date today = dateTimeFactory.today();
        Date yesterday = dateTimeFactory.yesterday();
        List<Person> persons = personService.list();
        List<Person> dailyRegisteredPersons = new ArrayList<>();
        for (Person p : persons) {
            if((p.getRegisterDateTime().before(today) || p.getRegisterDateTime().equals(today))
                    && (p.getRegisterDateTime().after(yesterday) || p.getRegisterDateTime().equals(yesterday))) {
                dailyRegisteredPersons.add(p);
            }
        }
        if(!dailyRegisteredPersons.isEmpty()) {
            //TODO Create a message and send it
        }

    }

}
