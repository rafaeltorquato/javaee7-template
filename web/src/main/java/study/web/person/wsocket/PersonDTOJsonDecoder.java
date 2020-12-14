package study.web.person.wsocket;

import com.google.gson.Gson;
import study.web.person.facade.dto.PersonDTO;
import study.web.util.GsonInstance;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class PersonDTOJsonDecoder implements Decoder.Text<PersonDTO> {

    private final Gson gson = GsonInstance.getGson();

    @Override
    public PersonDTO decode(String s) throws DecodeException {
        return gson.fromJson(s, PersonDTO.class);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null && !s.isEmpty();
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {

    }
}
