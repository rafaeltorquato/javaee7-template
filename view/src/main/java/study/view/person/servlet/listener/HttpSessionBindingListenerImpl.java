package study.view.person.servlet.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

@Slf4j
//@WebListener FIXME Wildfly bug when this listener became activemust implement at least one listener interface
public class HttpSessionBindingListenerImpl implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        log.info("HttpSession value [{}:{}] bound!", event.getName(), event.getValue());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        log.info("HttpSession value [{}:{}] unbound!", event.getName(), event.getValue());
    }
}
