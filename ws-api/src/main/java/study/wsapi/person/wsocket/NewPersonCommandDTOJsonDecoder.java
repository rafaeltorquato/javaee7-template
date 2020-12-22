package study.wsapi.person.wsocket;

import com.google.gson.Gson;
import study.facade.person.dto.NewPersonCommandDTO;
import study.wsapi.util.GsonProducer;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class NewPersonCommandDTOJsonDecoder implements Decoder.Text<NewPersonCommandDTO> {

    private final Gson gson = GsonProducer.create();

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
