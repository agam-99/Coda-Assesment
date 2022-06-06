package Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Commons {
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public static <T> String toJson(T t) {
        return gson.toJson(t);
    }
}
