package study.delivery.util;

import com.google.gson.Gson;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class GsonProducer {

    private static final Gson GSON = new Gson();

    @Produces
    public Gson produces() {
        return create();
    }

    public static Gson create() {
        return GSON;
    }
}
