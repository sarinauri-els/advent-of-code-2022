package dayThree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static utilities.Utilities.readFile;

public class RucksackReorganization {
    public static void main(String[] args) {
        List<String> rucksacks = readFile("src/main/java/dayThree/rucksacks.txt");

        List<String> commonItems = new ArrayList<>();
        for (String rucksack : rucksacks) {
            String compartmentA = rucksack.substring(0, rucksack.length() / 2);
            String compartmentB = rucksack.substring(rucksack.length() / 2);
            commonItems.addAll(findCommonItems(compartmentA, compartmentB));
        }

        int sumOfPriorities = sumOfPriorities(commonItems);
        System.out.println("Part 1 Answer: " + sumOfPriorities);

        List<String> badges = new ArrayList<>();
        int index = 0;
        while (index < rucksacks.size()) {
            String rucksackOne = rucksacks.get(index++);
            String rucksackTwo = rucksacks.get(index++);
            String rucksackThree = rucksacks.get(index++);
            badges.addAll(findBadges(rucksackOne, rucksackTwo, rucksackThree));
        }

        int sumOfPrioritiesForBadges = sumOfPriorities(badges);
        System.out.println("Part 2 Answer: " + sumOfPrioritiesForBadges);
    }

    private static List<String> findCommonItems(String compartmentA, String compartmentB) {
        List<String> compartmentAList = List.of(compartmentA.split(""));
        List<String> compartmentBList = List.of(compartmentB.split(""));
        return compartmentAList.stream()
                .filter(compartmentBList::contains)
                .distinct()
                .collect(Collectors.toList());
    }

    private static int sumOfPriorities(List<String> commonItems) {
        String itemPriority = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int total = 0;
        for (String item : commonItems) {
            total += itemPriority.indexOf(item) + 1;
        }
        return total;
    }

    private static List<String> findBadges(String rucksackOne, String rucksackTwo, String rucksackThree) {
        List<String> rucksackOneList = List.of(rucksackOne.split(""));
        List<String> rucksackTwoList = List.of(rucksackTwo.split(""));
        List<String> rucksackThreeList = List.of(rucksackThree.split(""));

        return rucksackOneList.stream()
                .filter(rucksackTwoList::contains)
                .filter(rucksackThreeList::contains)
                .distinct()
                .collect(Collectors.toList());
    }
}
