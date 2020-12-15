package study.web.person.wsocket;

import com.google.gson.Gson;
import study.web.person.facade.dto.PersonDTO;
import study.web.util.GsonInstance;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.List;

public class PersonDTOJsonArrayEncoder implements Encoder.Text<List<PersonDTO>> {

    private final Gson gson = GsonInstance.getGson();

    @Override
    public String encode(List<PersonDTO> object) throws EncodeException {
        return gson.toJson(object);
    }

    @Override
    public void init(EndpointConfig config) { }

    @Override
    public void destroy() { }
}
