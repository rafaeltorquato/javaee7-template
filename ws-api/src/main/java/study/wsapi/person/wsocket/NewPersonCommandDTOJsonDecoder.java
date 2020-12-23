package study.wsapi.person.wsocket;

import com.google.gson.Gson;
import study.facade.person.dto.SavePersonCommandDTO;
import study.wsapi.util.GsonProducer;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class NewPersonCommandDTOJsonDecoder implements Decoder.Text<SavePersonCommandDTO> {

    private Gson gson;

    @Override
    public SavePersonCommandDTO decode(String s) throws DecodeException {
        return gson.fromJson(s, SavePersonCommandDTO.class);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null && !s.isEmpty();
    }

    @Override
    public void init(EndpointConfig config) {
        this.gson = GsonProducer.create();
    }

    @Override
    public void destroy() {

    }
}
