import java.util.HashMap;
import java.util.Map;

/**
 * Created by bamboo on 03.08.14.
 */
public class Main {
    public static void main(String[] args) {
        Map<String, JSON> map = new HashMap<String, JSON>();
        map.put("first_name", new JStr("Andriy"));
        map.put("second_name", new JStr("Bas"));

    }
}
