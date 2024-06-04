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
        int p1 = player1Score;
        int p2 = player2Score;

        while (p1 > 4 || p2 > 4) {
            p1--;
            p2--;
        }
        if(p1 >= 3 && p1 - p2 == 0) return "Deuce";

        var lookup = new HashMap<Map.Entry, String>();
        lookup.put(Map.entry(0, 1), String.format("%s-%s", toRunningScore(0), toRunningScore(1)));
        lookup.put(Map.entry(0, 2), String.format("%s-%s", toRunningScore(0), toRunningScore(2)));
        lookup.put(Map.entry(0, 3), String.format("%s-%s", toRunningScore(0), toRunningScore(3)));
        lookup.put(Map.entry(1, 2), String.format("%s-%s", toRunningScore(1), toRunningScore(2)));
        lookup.put(Map.entry(1, 3), String.format("%s-%s", toRunningScore(1), toRunningScore(3)));
        lookup.put(Map.entry(2, 0), String.format("%s-%s", toRunningScore(2), toRunningScore(0)));
        lookup.put(Map.entry(2, 1), String.format("%s-%s", toRunningScore(2), toRunningScore(1)));
        lookup.put(Map.entry(2, 3), String.format("%s-%s", toRunningScore(2), toRunningScore(3)));
        lookup.put(Map.entry(1, 0), String.format("%s-%s", toRunningScore(1), toRunningScore(0)));
        lookup.put(Map.entry(3, 0), "Forty-Love");
        lookup.put(Map.entry(3, 1), "Forty-Fifteen");
        lookup.put(Map.entry(3, 2), "Forty-Thirty");
        lookup.put(Map.entry(0, 0), "Love-All");
        lookup.put(Map.entry(1, 1), "Fifteen-All");
        lookup.put(Map.entry(2, 2), "Thirty-All");
        lookup.put(Map.entry(4, 3), "Advantage player1");
        lookup.put(Map.entry(3, 4), "Advantage player2");
        lookup.put(Map.entry(4, 0), "Win for player1");
        lookup.put(Map.entry(4, 1), "Win for player1");
        lookup.put(Map.entry(4, 2), "Win for player1");
        lookup.put(Map.entry(2, 4), "Win for player2");
        lookup.put(Map.entry(1, 4), "Win for player2");
        lookup.put(Map.entry(0, 4), "Win for player2");

        var entry = Map.entry(p1, p2);
        if (lookup.containsKey(entry)) {
            return lookup.get(entry);
        } else {
            throw new IllegalArgumentException("Invalid score.");
        }
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
