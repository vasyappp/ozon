package Utils;

import java.util.HashMap;
import java.util.Map;

public class Stash {
    private static Stash ourInstance = new Stash();
    private Map<String, Object> stash;

    public static String cartProductNames = "CartProductNames";

    public static Stash getInstance() {
        return ourInstance;
    }

    private Stash() {
        this.stash = new HashMap<>();
    }

    public void put(String key, Object value) {
        stash.put(key, value);
    }

    public Object get(String key) {
        return stash.get(key);
    }
}
