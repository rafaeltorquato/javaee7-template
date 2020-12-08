package study.components.impl;

import study.components.DateTimeFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Calendar;
import java.util.Date;

@Alternative
@ApplicationScoped
public class DateTimeFactoryCalendarImpl implements DateTimeFactory {

    @Override
    public Date today() {
        Calendar instance = calendarToday();
        return instance.getTime();
    }

    @Override
    public Date yesterday() {
        Calendar calendar = calendarToday();
        calendar.set(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    @Override
    public Date now() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    private Calendar calendarToday() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance;
    }
}
