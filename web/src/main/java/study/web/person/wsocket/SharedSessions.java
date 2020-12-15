package study.web.person.wsocket;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class SharedSessions {

    private final List<Session> listSessions = Collections.synchronizedList(new ArrayList<Session>());

    private final List<Session> addSessions = Collections.synchronizedList(new ArrayList<Session>());

    public List<Session> getListSessions() {
        return listSessions;
    }

    public List<Session> getAddSessions() {
        return addSessions;
    }
}
