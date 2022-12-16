package daySix;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TuningTrouble {
    public static void main(String[] args) throws IOException {
        Stream<String> lines = Files.lines(Path.of("src/main/java/daySix/datastream.txt"), StandardCharsets.UTF_8);
        String input = lines.collect(Collectors.joining());

        int packetMarkerSize = 4;
        int messageMarkerSize = 14;

        int packetMarker = getMarker(input, packetMarkerSize);
        System.out.println("Part 1 Answer: " + packetMarker);

        int messageMarker = getMarker(input, messageMarkerSize);
        System.out.println("Part 2 Answer: " + messageMarker);
    }

    private static int getMarker(String input, int markerSize) {
        for (int i = 0; i < input.length() - markerSize; i++) {
            String substring = input.substring(i, i+markerSize);
            if (substring.chars().distinct().count() == markerSize) {
                return i + markerSize;
            }
        }
        return -1;
    }
}
