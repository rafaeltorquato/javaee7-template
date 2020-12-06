package study.components.impl;

import study.components.DateTimeFactory;

import javax.inject.Singleton;
import java.util.Date;

@Singleton
public class DateTimeFactoryImpl implements DateTimeFactory {

    @Override
    public Date today() {
        Date date = now();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }

    @Override
    public Date yesterday() {
        Date date = today();
        date.setDate(-1);
        return date;
    }

    @Override
    public Date now() {
        return new Date();
    }
}
