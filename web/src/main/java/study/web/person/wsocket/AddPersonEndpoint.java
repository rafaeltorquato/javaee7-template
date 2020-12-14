package study.web.person.wsocket;

import com.google.gson.Gson;
import study.web.person.facade.PersonFacade;
import study.web.person.facade.dto.PersonDTO;
import study.web.util.GsonProvider;

import javax.inject.Inject;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import java.io.IOException;

public class AddPersonEndpoint extends Endpoint {

    @Inject
    private PersonFacade personFacade;
    @Inject
    private GsonProvider gsonProvider;

    @Override
    public void onOpen(final Session session, final EndpointConfig config) {

        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                try {
                    final Gson gson = gsonProvider.get();
                    final PersonDTO personDTO = gson.fromJson(message, PersonDTO.class);
                    PersonDTO saved = personFacade.save(personDTO);
                    session.getBasicRemote().sendText(gson.toJson(saved));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
