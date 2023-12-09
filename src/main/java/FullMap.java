public class FullMap {
    String fullMap;
    public FullMap(String fullMap) {
        this.fullMap = fullMap;
    }

    public Long convert(Long destination) {
        for (String entry: fullMap.split(";")) {
            Map map = new Map(entry);

            if (map.source <= destination && map.source+map.length >= destination) {
                long change = destination-map.source;
                return map.destination+change;
            }
        }
        return destination;
    }

    public Long convertReverse(Long source) {
        for (String entry: fullMap.split(";")) {
            Map map = new Map(entry);

            if (map.destination <= source && map.destination+map.length >= source) {
                long change = source-map.destination;
                return map.source+change;
            }
        }
        return source;
    }
}
