package study.wsapi.person.wsocket;

import com.google.gson.Gson;
import study.facade.person.dto.PersonDTO;
import study.wsapi.util.GsonProducer;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.List;

public class PersonDTOJsonArrayEncoder implements Encoder.Text<List<PersonDTO>> {

    private final Gson gson = GsonProducer.create();

    @Override
    public String encode(List<PersonDTO> object) throws EncodeException {
        return gson.toJson(object);
    }

    @Override
    public void init(EndpointConfig config) { }

    @Override
    public void destroy() { }
}
