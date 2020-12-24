package study.jspview.person.servlet.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Slf4j
@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("HttpSession created!");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("HttpSession destroyed!");
    }
}
