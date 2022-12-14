package dayTwo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static utilities.Utilities.readFile;

public class RockPaperScissors {
    public static void main(String[] args) {
        List<String> matches = readFile("src/main/java/dayTwo/strategy.txt");

        int partOneScore = scoreTournament(matches);
        System.out.println("Part one total score: " + partOneScore);

        List<String> partTwoMatches = chooseShape(matches);
        int partTwoScore = scoreTournament(partTwoMatches);
        System.out.println("Part two total score: " + partTwoScore);
    }

    private static int scoreTournament(List<String> matches) {
        int totalScore = 0;

        Map<String, Integer> lose = Map.of(
                "A Z", 3,
                "B X", 1,
                "C Y", 2
        );

        Map<String, Integer> win = Map.of(
                "A Y", 2,
                "B Z", 3,
                "C X", 1
        );

        Map<String, Integer> draw = Map.of(
                "A X", 1,
                "B Y", 2,
                "C Z", 3
        );

        for (String match : matches) {
            if (win.containsKey(match)) {
                totalScore += (win.get(match) + 6);
            }

            if (lose.containsKey(match)) {
                totalScore += lose.get(match);
            }

            if (draw.containsKey(match)) {
                totalScore += (draw.get(match) + 3);
            }
        }

        return totalScore;
    }

    private static List<String> chooseShape(List<String> matches) {
        List<String> finalMatches = new ArrayList<>();

        String  playerOneMove = "";
        String playerTwoGoal = "";

        for (String match : matches) {
            String[] moves = match.split(" ");
            playerOneMove = moves[0];
            playerTwoGoal = moves[1];

            String playerTwoMove = "";

            switch(playerTwoGoal) {
                case "X":
                    playerTwoMove = losingShape(playerOneMove);
                    break;
                case "Y":
                    playerTwoMove = drawingShape(playerOneMove);
                    break;
                case "Z":
                    playerTwoMove = winningShape(playerOneMove);
                    break;
            }
            finalMatches.add(playerOneMove + " "  +  playerTwoMove);
        }
        return finalMatches;
    }

    private static String losingShape(String playerOneMove){
        return switch(playerOneMove) {
            case "A" -> "Z";
            case "B" -> "X";
            case "C" -> "Y";
            default -> throw new IllegalStateException("Unexpected value");
        };
    }

    private static String winningShape(String playerOneMove){
        return switch(playerOneMove) {
            case "A" -> "Y";
            case "B" -> "Z";
            case "C" -> "X";
            default -> throw new IllegalStateException("Unexpected value");
        };
    }

    private static String drawingShape(String playerOneMove) {
        return switch(playerOneMove) {
            case "A" -> "X";
            case "B" -> "Y";
            case "C" -> "Z";
            default -> throw new IllegalStateException("Unexpected value");
        };
    }
}
