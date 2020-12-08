package study.components.impl;

import study.components.ChosenDateTimeFactory;
import study.components.DateTimeFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class DateTimeFactoryProducer {

    private static final Logger logger = Logger.getLogger(DateTimeFactoryProducer.class.getSimpleName());

    @Inject
    private DateTimeFactoryImpl dateTimeFactory;
    @Inject
    private DateTimeFactoryCalendarImpl dateTimeFactoryCalendar;

    @Produces
    @ChosenDateTimeFactory
    @RequestScoped
    public DateTimeFactory getDateTimeFactory() {
        //if
        ;//return dateTimeFactory;
        //else
        return dateTimeFactoryCalendar;
    }

    public void close(@Disposes @ChosenDateTimeFactory DateTimeFactory dateTimeFactory) {
        logger.info("Disposed " + dateTimeFactory.getClass().getName() + " bean.");
    }

}
