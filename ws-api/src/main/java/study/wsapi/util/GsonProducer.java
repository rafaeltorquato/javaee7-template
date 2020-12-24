package study.wsapi.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class GsonProducer {

    private static final Gson GSON = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();;

    @Produces
    public Gson produces() {
        return create();
    }

    public static Gson create() {
        return GSON;
    }
}
