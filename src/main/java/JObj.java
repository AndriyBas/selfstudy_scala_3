import java.util.Map;

/**
 * Created by bamboo on 03.08.14.
 */
public class JObj implements JSON {

    private Map<String, JSON> map;

    public JObj(Map<String, JSON> map) {
        this.map = map;
    }

    public Map<String, JSON> getMap() {
        return map;
    }

    public void setMap(Map<String, JSON> map) {
        this.map = map;
    }
}
