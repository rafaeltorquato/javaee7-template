package study.web.util;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GsonInstance {

    private static final Gson GSON = new Gson();

    public static Gson getGson() {
        return GSON;
    }
}
