package study.jspview.person.servlet.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

@Slf4j
//@WebListener FIXME Wildfly bug when this listener became activemust implement at least one listener interface
public class HttpSessionActivationListenerImpl implements HttpSessionActivationListener {

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        log.info("Session {} will passivate.", se.getSession().getId());
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        log.info("Session {} did activate.", se.getSession().getId());
    }
}
