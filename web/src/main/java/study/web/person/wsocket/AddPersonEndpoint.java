package study.web.person.wsocket;

import study.web.person.facade.PersonFacade;
import study.web.person.facade.dto.PersonDTO;

import javax.inject.Inject;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import java.util.Set;

public class AddPersonEndpoint extends Endpoint {

    public static final String MY_URL = "/socket/person/add";

    @Inject
    private PersonFacade personFacade;

    @Override
    public void onOpen(final Session session, final EndpointConfig config) {
        config.getDecoders().add(PersonDTOJsonDecoder.class);
        config.getEncoders().add(PersonDTOJsonEncoder.class);

        session.addMessageHandler(new MessageHandler.Whole<PersonDTO>() {
            @Override
            public void onMessage(PersonDTO message) {
                PersonDTO saved = personFacade.save(message);
                //Send to all sessions a new Person
                final Set<Session> openedSessions = session.getOpenSessions();
                for (Session s : openedSessions) {
                    s.getAsyncRemote().sendObject(saved);
                }
            }
        });
    }

}
