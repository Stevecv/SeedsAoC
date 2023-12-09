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
}
