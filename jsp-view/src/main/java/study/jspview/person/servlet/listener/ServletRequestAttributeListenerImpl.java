package study.jspview.person.servlet.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class ServletRequestAttributeListenerImpl implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        log.info("ServletRequest attribute [{}:{}] added!", srae.getName(), srae.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        log.info("ServletRequest attribute [{}:{}] removed!", srae.getName(), srae.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        log.info("ServletRequest attribute [{}:{}] replaced!", srae.getName(), srae.getValue());
    }
}
