package study.web.person.wsocket;

import lombok.extern.slf4j.Slf4j;
import study.web.person.facade.PersonFacade;
import study.web.person.facade.dto.PersonDTO;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

@Slf4j
@ServerEndpoint(
        value = "/socket/person/list",
        encoders = {PersonDTOJsonEncoder.class},
        decoders = {PersonDTOJsonDecoder.class}
)
public class ListPersonEndpoint {


    @Inject
    private PersonFacade personFacade;

    private Session session;

    @OnOpen
    public void open(Session session, EndpointConfig conf) {
        this.session = session;
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
    }


    @OnError
    public void error(Session session, Throwable error) {
    }

}
