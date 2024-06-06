import java.util.HashMap;
import java.util.Map;

public class TennisGame5 implements TennisGame {

    private final String player1Name;
    private final String player2Name;
    private int player1Score;
    private int player2Score;

    public TennisGame5(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    @Override
    public void wonPoint(String playerName) {
        if (playerName.equals("player1"))
            player1Score++;
        else if (playerName.equals("player2"))
            player2Score++;
        else
            throw new IllegalArgumentException("Invalid player name.");
    }

    @Override
    public String getScore() {
        while (player1Score > 4 || player2Score > 4) {
            player1Score--;
            player2Score--;
        }
        Map<Map.Entry<Integer, Integer>, String> lookup = getScoreMap();

        if(isDeuce()) return "Deuce";
        if(player1Score < 3 && pointsDifference() == 0) return String.format("%s-All", toRunningScore(player1Score));

        var score = Map.entry(player1Score, player2Score);
        if(!lookup.containsKey(score)) throw new IllegalArgumentException("Invalid score.");

        if(player1Score > 3 && pointsDifference() >= 2) return "Win for player1";
        if(player2Score > 3 && pointsDifference() <= -2) return "Win for player2";
        return lookup.get(score);
    }

    private boolean isDeuce() {
        return player1Score >= 3 && pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1Score - player2Score;
    }

    private Map<Map.Entry<Integer, Integer>, String> getScoreMap() {
        Map<Map.Entry<Integer, Integer>, String> lookup = new HashMap<>();
        lookup.put(Map.entry(0, 1), String.format("%s-%s", toRunningScore(0), toRunningScore(1)));
        lookup.put(Map.entry(0, 2), String.format("%s-%s", toRunningScore(0), toRunningScore(2)));
        lookup.put(Map.entry(0, 3), String.format("%s-%s", toRunningScore(0), toRunningScore(3)));
        lookup.put(Map.entry(1, 2), String.format("%s-%s", toRunningScore(1), toRunningScore(2)));
        lookup.put(Map.entry(1, 3), String.format("%s-%s", toRunningScore(1), toRunningScore(3)));
        lookup.put(Map.entry(2, 0), String.format("%s-%s", toRunningScore(2), toRunningScore(0)));
        lookup.put(Map.entry(2, 1), String.format("%s-%s", toRunningScore(2), toRunningScore(1)));
        lookup.put(Map.entry(2, 3), String.format("%s-%s", toRunningScore(2), toRunningScore(3)));
        lookup.put(Map.entry(1, 0), String.format("%s-%s", toRunningScore(1), toRunningScore(0)));
        lookup.put(Map.entry(3, 0), String.format("%s-%s", toRunningScore(3), toRunningScore(0)));
        lookup.put(Map.entry(3, 1), String.format("%s-%s", toRunningScore(3), toRunningScore(1)));
        lookup.put(Map.entry(3, 2), String.format("%s-%s", toRunningScore(3), toRunningScore(2)));
        lookup.put(Map.entry(4, 3), "Advantage player1");
        lookup.put(Map.entry(3, 4), "Advantage player2");
        lookup.put(Map.entry(4, 0), null);
        lookup.put(Map.entry(4, 1), null);
        lookup.put(Map.entry(4, 2), null);
        lookup.put(Map.entry(2, 4), null);
        lookup.put(Map.entry(1, 4), null);
        lookup.put(Map.entry(0, 4), null);
        return lookup;
    }

    private String toRunningScore(Integer score) {
        return RUNNING_SCORE.get(score);
    }

    private static final Map<Integer, String> RUNNING_SCORE = Map.of(
            0, "Love",
            1, "Fifteen",
            2, "Thirty",
            3, "Forty"
    );
}
