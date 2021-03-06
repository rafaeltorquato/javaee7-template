package study.wsapi.person.wsocket;

import lombok.extern.slf4j.Slf4j;
import study.facade.person.PersonFacade;
import study.facade.person.dto.PersonDTO;

import javax.annotation.security.RunAs;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

@Slf4j
@ServerEndpoint(
        value = "/socket/persons/list",
        encoders = {PersonDTOJsonEncoder.class, PersonDTOJsonArrayEncoder.class},
        decoders = {NewPersonCommandDTOJsonDecoder.class}
)
@RunAs("ADMINISTRATOR")
public class ListPersonEndpoint {

    @Inject
    private PersonFacade personFacade;
    @Inject
    private SharedSessions sharedSessions;

    @OnOpen
    public void open(Session session, EndpointConfig conf) {
        sharedSessions.getListSessions().add(session);
    }

    @OnMessage
    public void textMessage(Session session, String msg) {
        try {
            final List<PersonDTO> dtoList = personFacade.list();
            session.getBasicRemote().sendObject(dtoList);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void binaryMessage(Session session, ByteBuffer msg) {
        log.info("Binary message: {}", msg.toString());
    }

    @OnMessage
    public void pongMessage(Session session, PongMessage msg) {
        log.info("Pong message: {}", msg.getApplicationData().toString());
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
        sharedSessions.getListSessions().remove(session);
    }


    @OnError
    public void error(Session session, Throwable error) {
    }

}
