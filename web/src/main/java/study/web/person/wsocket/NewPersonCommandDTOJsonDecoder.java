package study.web.person.wsocket;

import com.google.gson.Gson;
import study.web.person.facade.dto.NewPersonCommandDTO;
import study.web.util.GsonInstance;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class NewPersonCommandDTOJsonDecoder implements Decoder.Text<NewPersonCommandDTO> {

    private final Gson gson = GsonInstance.getGson();

    @Override
    public NewPersonCommandDTO decode(String s) throws DecodeException {
        return gson.fromJson(s, NewPersonCommandDTO.class);
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
