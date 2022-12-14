package dayFour;

import java.util.Arrays;
import java.util.List;

import static utilities.Utilities.readFile;

public class CampCleanup {
    public static void main(String[] args) {
        List<String> assignments = readFile("src/main/java/dayFour/assignments.txt");
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

    private static boolean isFullyContained(int[] assignmentOne, int[] assignmentTwo) {
        return (assignmentOne[0] >= assignmentTwo[0] && assignmentOne[1] <= assignmentTwo[1]) ||
                (assignmentTwo[0] >= assignmentOne[0] && assignmentTwo[1] <= assignmentOne[1]);
    }

    private static boolean overlaps(int[] assignmentOne, int[] assignmentTwo) {
        return (assignmentOne[0] >= assignmentTwo[0] && assignmentOne[0] <= assignmentTwo[1]) ||
                (assignmentOne[1] >= assignmentTwo[0] && assignmentOne[0] <= assignmentTwo[1]);
    }
}
