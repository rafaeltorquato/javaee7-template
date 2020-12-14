package study.components.impl;

import lombok.extern.slf4j.Slf4j;
import study.components.ChosenDateTimeFactory;
import study.components.DateTimeFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.logging.Logger;

@Slf4j
@ApplicationScoped
public class DateTimeFactoryProducer {

    private static final Logger logger = Logger.getLogger(DateTimeFactoryProducer.class.getSimpleName());

    @Inject
    private DateTimeFactoryImpl dateTimeFactory;
    //private DateTimeFactoryCalendarImpl dateTimeFactoryCalendar;

    @Produces
    @ChosenDateTimeFactory
    @ApplicationScoped
    public DateTimeFactory getDateTimeFactory() {
        //if
        ;//return dateTimeFactory;
        //else
        return new DateTimeFactoryCalendarImpl();
    }

    public void close(@Disposes @ChosenDateTimeFactory DateTimeFactory dateTimeFactory) {
        log.info("Disposed {} bean.", dateTimeFactory.getClass().getName());
    }

}
