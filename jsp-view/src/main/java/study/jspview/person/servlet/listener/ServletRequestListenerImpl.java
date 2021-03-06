package study.jspview.person.servlet.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class ServletRequestListenerImpl implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("Servlet Request initialized!");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("Servlet Request destroyed!");
    }
}
