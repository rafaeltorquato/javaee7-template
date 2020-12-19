package study.delivery.person.servlet.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class ServletContextAttributeListenerImpl implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        log.info("Context attribute [{}:{}] added!", event.getName(), event.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        log.info("Context attribute [{}:{}] removed!", event.getName(), event.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        log.info("Context attribute [{}:{}] replaced!", event.getName(), event.getValue());
    }
}
