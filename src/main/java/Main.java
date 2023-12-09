import java.util.HashMap;

public class Main {
    public static HashMap<String, String> puzzleInput = new PuzzleInput().puzzleInput;
    public static void main(String[] args) {
        long smallest = -1;
        for (String strSeed: puzzleInput.get("seeds").split(" ")) {
            long seed = Long.parseLong(strSeed);
            long location = seedToLocation(seed);

            if (smallest == -1) {
                smallest = location;
            } else if (location < smallest) {
                smallest = location;
            }
        }
        System.out.println(smallest);
    }

    public static long seedToLocation(Long seed) {
        long soil = new FullMap(puzzleInput.get("seed-to-soil")).convert(seed);
        long fertilizer = new FullMap(puzzleInput.get("soil-to-fertilizer")).convert(soil);
        long water = new FullMap(puzzleInput.get("fertilizer-to-water")).convert(fertilizer);
        long light = new FullMap(puzzleInput.get("water-to-light")).convert(water);
        long temperature = new FullMap(puzzleInput.get("light-to-temperature")).convert(light);
        long humidity = new FullMap(puzzleInput.get("temperature-to-humidity")).convert(temperature);
        long location = new FullMap(puzzleInput.get("humidity-to-location")).convert(humidity);

        return location;
    }
}
