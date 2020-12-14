package study.web.person.wsocket;

import com.google.gson.Gson;
import study.web.person.facade.dto.PersonDTO;
import study.web.util.GsonInstance;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class PersonDTOJsonEncoder implements Encoder.Text<PersonDTO> {

    private final Gson gson = GsonInstance.getGson();

    @Override
    public String encode(PersonDTO object) throws EncodeException {
        return gson.toJson(object);
    }

    @Override
    public void init(EndpointConfig config) { }

    @Override
    public void destroy() { }
}
