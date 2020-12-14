package study.web.util;

import com.google.gson.Gson;

import javax.inject.Singleton;

@Singleton
public class GsonProviderImpl implements GsonProvider {
    private final Gson gson = new Gson();

    @Override
    public Gson get() {
        return gson;
    }

}
