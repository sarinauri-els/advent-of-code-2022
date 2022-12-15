package dayFive;

import java.util.*;

import static utilities.Utilities.readFile;

public class SupplyStacks {
    public static void main(String[] args) {
        List<String> input = readFile("src/main/java/dayFive/rearrangementProcedure.txt");
        List<List<String>> splitInput = splitInput(input);

        Map<Integer, Stack<String>> stacks = initialiseStacks(splitInput.get(0), splitInput.get(1).get(0).length());
        moveCrates(splitInput.get(2), stacks);
        String topCrates = getTopOfStacks(stacks);
        System.out.println("Part 1 Answer: " + topCrates);

        stacks = initialiseStacks(splitInput.get(0), splitInput.get(1).get(0).length());
        moveMultipleCrates(splitInput.get(2), stacks);
        topCrates = getTopOfStacks(stacks);
        System.out.println("Part 2 Answer: " + topCrates);
    }

    private static List<List<String>> splitInput(List<String> input) {
        List<List<String>> split = new ArrayList<>();

        boolean endOfStartingPositions = false;
        int i = 0;

        while(!endOfStartingPositions) {
            if (input.get(i).charAt(1) == '1') {
                endOfStartingPositions = true;
            } else {
                i++;
            }
        }

        split.add(input.subList(0, i));
        split.add(input.subList(i,i+1));
        split.add(input.subList(i+2, input.size()));
        return split;
    }

    private static Map<Integer, Stack<String>> initialiseStacks(List<String> startingStacksInput, int maxColIndex) {
        Map<Integer, Stack<String>> stacks = new HashMap<>();
        int currColIndex = 1;
        int maxNoStacks = (maxColIndex + 2) / 4;

        for (int stackNo = 1; stackNo <= maxNoStacks; stackNo++) {
            Stack stack = new Stack();
            for (int row = startingStacksInput.size() - 1; row >= 0; row--) {
                if (startingStacksInput.get(row).length() >= currColIndex) {
                    String currCharacter = String.valueOf(startingStacksInput.get(row).charAt(currColIndex));
                    if (currCharacter.matches("[A-Z]")) {
                        stack.push(currCharacter);
                    }
                }
            }

            stacks.put(stackNo, stack);

            if (currColIndex + 4 < maxColIndex) {
                currColIndex = currColIndex + 4;
            } else {
                break;
            }
        }
        return stacks;
    }

    private static void moveCrates(List<String> rearrangementProcedure, Map<Integer, Stack<String>> stacks) {
        for (String instruction : rearrangementProcedure) {
            instruction = instruction.replaceAll("[^0-9 ]", "");
            String[] splitInstructions = instruction.split("  ");

            int cratesToMove = Integer.parseInt(splitInstructions[0].trim());
            int startingStackKey = Integer.parseInt(splitInstructions[1].trim());
            int endStackKey = Integer.parseInt(splitInstructions[2].trim());

            Stack<String> startingStack = stacks.get(startingStackKey);
            Stack<String> endStack = stacks.get(endStackKey);

            for (int c = 1; c <= cratesToMove; c++) {
                String crate = startingStack.pop();
                endStack.push(crate);
            }
        }
    }

    private static void moveMultipleCrates(List<String> rearrangementProcedure, Map<Integer, Stack<String>> stacks)  {
        for (String instruction : rearrangementProcedure) {
            instruction = instruction.replaceAll("[^0-9 ]", "");
            String[] splitInstructions = instruction.split("  ");

            int cratesToMove = Integer.parseInt(splitInstructions[0].trim());
            int startingStackKey = Integer.parseInt(splitInstructions[1].trim());
            int endStackKey = Integer.parseInt(splitInstructions[2].trim());

            Stack<String> startingStack = stacks.get(startingStackKey);
            Stack<String> endStack = stacks.get(endStackKey);

            Stack<String> tempStack = new Stack<>();

            for (int c = 1; c <= cratesToMove; c++) {
                String crate = startingStack.pop();
                tempStack.push(crate);
            }

            for (int c = 1; c <= cratesToMove; c++) {
                String crate = tempStack.pop();
                endStack.push(crate);
            }
        }
    }

    private static String getTopOfStacks(Map<Integer, Stack<String>> stacks) {
        String topCrates = "";
        for (Stack<String> stack : stacks.values()) {
            topCrates+=stack.peek();
        }
        return topCrates;
    }
}
