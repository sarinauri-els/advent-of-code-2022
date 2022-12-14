package dayOne;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class CalorieCounting {
    public static void main(String[] args) {
        List<String> calories = new ArrayList<>();
        List<Integer> totalCalories = new ArrayList<>();

        try (Scanner scanner = new Scanner(Paths.get("src/main/java/dayOne/calories.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("")) {
                    Integer sum = calories.stream()
                            .mapToInt(Integer::parseInt)
                            .sum();
                    totalCalories.add(sum);
                    calories.clear();
                } else {
                    calories.add(line);
                }
            }
            Integer sum = calories.stream()
                    .mapToInt(Integer::parseInt)
                    .sum();
            totalCalories.add(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Part One: " + Collections.max(totalCalories));
        System.out.println("Part Two: " + totalCalories.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(x -> x)
                .sum());
    }
}
