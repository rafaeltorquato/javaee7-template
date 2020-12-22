package study.delivery.person.wsocket;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import study.delivery.person.facade.PersonFacade;
import study.delivery.person.facade.dto.NewPersonCommandDTO;
import study.delivery.person.facade.dto.PersonDTO;
import study.delivery.util.GlobalExceptionHandler;

import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;

@Slf4j
@ServerEndpoint(
        value = "/secure/socket/persons/add",
        encoders = {PersonDTOJsonEncoder.class, PersonDTOJsonArrayEncoder.class, ErrorMessageEncoder.class},
        decoders = {NewPersonCommandDTOJsonDecoder.class}
)
public class AddPersonEndpoint {

    @Inject
    private PersonFacade personFacade;
    @Inject
    private SharedSessions sharedSessions;
    @Inject
    private GlobalExceptionHandler globalExceptionHandler;
    @Inject
    private Gson gson;

    @OnMessage
    public void onMessage(Session session, NewPersonCommandDTO message) {
        log.info("Message received!");
        try {
            PersonDTO saved = personFacade.save(message);
            session.getBasicRemote().sendObject(gson.toJson(saved));
        } catch (Exception exception) {
            GlobalExceptionHandler.ErrorMessage errorMessage = globalExceptionHandler.handle(exception);
            try {
                session.getBasicRemote().sendObject(errorMessage);
            } catch (IOException | EncodeException e) {
                log.error(e.getMessage(), e);
            }
        }
        sendToAll();
    }

    private void sendToAll() {
        //Send to all sessions a new Person
        //TODO Best way todo this is with a topic by sent the message to all nodes.
        final List<Session> openedSessions = sharedSessions.getListSessions();
        for (Session s : openedSessions) {
            try {
                final List<PersonDTO> dtoList = personFacade.list();
                s.getBasicRemote().sendObject(dtoList);
            } catch (IOException | EncodeException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

}
