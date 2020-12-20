package study.delivery.person.wsocket;

import lombok.extern.slf4j.Slf4j;
import study.delivery.person.facade.PersonFacade;
import study.delivery.person.facade.dto.NewPersonCommandDTO;
import study.delivery.person.facade.dto.PersonDTO;
import study.delivery.util.GlobalErrorMessageHandler;

import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;

@Slf4j
@ServerEndpoint(
        value = "/socket/persons/add",
        encoders = {PersonDTOJsonEncoder.class, PersonDTOJsonArrayEncoder.class, ErrorMessageEncoder.class},
        decoders = {NewPersonCommandDTOJsonDecoder.class}
)
public class AddPersonEndpoint {

    @Inject
    private PersonFacade personFacade;
    @Inject
    private SharedSessions sharedSessions;
    @Inject
    private GlobalErrorMessageHandler globalErrorMessageHandler;


    @OnMessage
    public void onMessage(Session session, NewPersonCommandDTO message) {
        log.info("Message received!");
        try {
            PersonDTO saved = personFacade.save(message);
        } catch (Exception exception) {
            GlobalErrorMessageHandler.ErrorMessage errorMessage = globalErrorMessageHandler.handle(exception);
            try {
                session.getBasicRemote().sendObject(errorMessage);
            } catch (IOException | EncodeException e) {
                log.error(e.getMessage(), e);
            }
        }
        //Send to all sessions a new Person
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
