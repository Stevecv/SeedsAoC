import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static HashMap<String, String> puzzleInput = new PuzzleInput().puzzleInput;
    public static ArrayList<Long> seedStarts = new ArrayList<>();
    public static ArrayList<Long> seedRanges = new ArrayList<>();
    public static void main(String[] args) {
        sortSeedArrays();
        long location = 0;
        while (true) {
            long seed = locationToSeed(location);
            System.out.println("Try: " + location + " // " + seed);
            if (isSeed(seed)) {
                System.out.println(location);
                break;
            }

            location++;
        }
    }

    public static void sortSeedArrays() {
        String seeds = puzzleInput.get("seeds");
        for (String strSeed: seeds.split(";")) {
            System.out.println(seeds);
            String[] parts = strSeed.split(" ");

            seedStarts.add(Long.valueOf(parts[0]));
            seedRanges.add(Long.valueOf(parts[1]));
        }
    }

    public static boolean isSeed(Long possibleSeed) {
        for (int i = 0; i < seedStarts.size(); i++) {
            long seedStart = seedStarts.get(i);
            long seedLength = seedRanges.get(i);

            if (possibleSeed >= seedStart && possibleSeed <= seedStart+seedLength) {
                return true;
            }
        }
        return false;
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

    public static long locationToSeed(Long location) {
        long humidity = new FullMap(puzzleInput.get("humidity-to-location")).convertReverse(location);
        long temperature = new FullMap(puzzleInput.get("temperature-to-humidity")).convertReverse(humidity);
        long light = new FullMap(puzzleInput.get("light-to-temperature")).convertReverse(temperature);
        long water = new FullMap(puzzleInput.get("water-to-light")).convertReverse(light);
        long fertilizer = new FullMap(puzzleInput.get("fertilizer-to-water")).convertReverse(water);
        long soil = new FullMap(puzzleInput.get("soil-to-fertilizer")).convertReverse(fertilizer);
        long seed = new FullMap(puzzleInput.get("seed-to-soil")).convertReverse(soil);

        return seed;
    }
}
