package study.delivery.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class GsonProducer {

    private static Gson GSON;

    @Produces
    public Gson produces() {
        return create();
    }

    public static Gson create() {
        if (GSON == null) {
            synchronized (GsonProducer.class) {
                if (GSON == null) {
                    GSON = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                            .create();
                }
            }
        }
        return GSON;
    }
}
