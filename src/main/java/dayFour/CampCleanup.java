package dayFour;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CampCleanup {
    public static void main(String[] args) {
        List<String> assignments = readFile();
        int fullyContained = 0;
        int overlaps = 0;
        for (String assignment : assignments) {
            String[] split = assignment.split(",");
            int[] elfOneAssignment = Arrays.stream(split[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] elfTwoAssignment = Arrays.stream(split[1].split("-")).mapToInt(Integer::parseInt).toArray();
            if (isFullyContained(elfOneAssignment, elfTwoAssignment)) {
                fullyContained++;
            }
            if (overlaps(elfOneAssignment, elfTwoAssignment)) {
                overlaps++;
            }
        }
        System.out.println("Part 1 Answer: " + fullyContained);
        System.out.println("Part 2 Answer: " + overlaps);
    }

    private static List<String> readFile() {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get("src/main/java/dayFour/assignments.txt"))) {
            while(scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static boolean isFullyContained(int[] assignmentOne, int[] assignmentTwo) {
        return (assignmentOne[0] >= assignmentTwo[0] && assignmentOne[1] <= assignmentTwo[1]) ||
                (assignmentTwo[0] >= assignmentOne[0] && assignmentTwo[1] <= assignmentOne[1]);
    }

    private static boolean overlaps(int[] assignmentOne, int[] assignmentTwo) {
        return (assignmentOne[0] >= assignmentTwo[0] && assignmentOne[0] <= assignmentTwo[1]) ||
                (assignmentOne[1] >= assignmentTwo[0] && assignmentOne[0] <= assignmentTwo[1]);
    }
}
