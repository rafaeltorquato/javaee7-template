package study.wsapi.person.wsocket;

import com.google.gson.Gson;
import study.wsapi.util.GlobalExceptionHandler;
import study.wsapi.util.GsonProducer;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ErrorMessageEncoder implements Encoder.Text<GlobalExceptionHandler.ErrorMessage> {

    private Gson gson;

    @Override
    public String encode(GlobalExceptionHandler.ErrorMessage object) throws EncodeException {
        return gson.toJson(object);
    }

    @Override
    public void init(EndpointConfig config) {
        this.gson = GsonProducer.create();
    }

    @Override
    public void destroy() {

    }
}
