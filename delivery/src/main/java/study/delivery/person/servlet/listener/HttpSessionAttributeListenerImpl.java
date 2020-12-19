package study.delivery.person.servlet.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@Slf4j
@WebListener
public class HttpSessionAttributeListenerImpl implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        log.info("HttpSession attribute [{}:{}] added!", event.getName(), event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        log.info("HttpSession attribute [{}:{}] removed!", event.getName(), event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        log.info("HttpSession attribute [{}:{}] replaced!", event.getName(), event.getValue());
    }
}
