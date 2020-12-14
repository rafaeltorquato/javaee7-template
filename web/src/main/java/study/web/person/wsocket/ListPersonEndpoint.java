package study.web.person.wsocket;

import com.google.gson.Gson;
import study.web.person.facade.PersonFacade;
import study.web.person.facade.dto.PersonDTO;
import study.web.util.GsonProvider;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;

@ServerEndpoint("/socket/person/list")
public class ListPersonEndpoint {

    @Inject
    private PersonFacade personFacade;
    @Inject
    private GsonProvider gsonProvider;

    @OnOpen
    public void open(Session session,
                     EndpointConfig conf) {
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        try {
            final List<PersonDTO> dtoList = personFacade.list();
            final Gson gson = gsonProvider.get();
            session.getBasicRemote().sendText(gson.toJson(dtoList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void close(Session session,
                      CloseReason reason) {
    }


    @OnError
    public void error(Session session,
                      Throwable error) {
    }

}
