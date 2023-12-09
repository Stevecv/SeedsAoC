import java.util.HashMap;

public class Map {
    public String map;
    public Long source;
    public Long destination;
    public Long length;

    public Map(String map) {
        this.map = map;

        String[] splitMap = map.split(" ");
        destination = Long.parseLong(splitMap[0]);
        source = Long.parseLong(splitMap[1]);
        length = Long.parseLong(splitMap[2]);
    }

}
