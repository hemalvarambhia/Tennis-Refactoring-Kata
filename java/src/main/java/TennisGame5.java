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
        if(p1 < 3 && p1 - p2 == 0) return String.format("%s-All", toRunningScore(p1));

        Map<Map.Entry<Integer, Integer>, String> lookup = new HashMap<>();
        lookup.put(Map.entry(0, 1), String.format("%s-%s", toRunningScore(p1), toRunningScore(p2)));
        lookup.put(Map.entry(0, 2), String.format("%s-%s", toRunningScore(p1), toRunningScore(p2)));
        lookup.put(Map.entry(0, 3), String.format("%s-%s", toRunningScore(p1), toRunningScore(p2)));
        lookup.put(Map.entry(1, 2), String.format("%s-%s", toRunningScore(p1), toRunningScore(p2)));
        lookup.put(Map.entry(1, 3), String.format("%s-%s", toRunningScore(p1), toRunningScore(p2)));
        lookup.put(Map.entry(2, 0), String.format("%s-%s", toRunningScore(p1), toRunningScore(p2)));
        lookup.put(Map.entry(2, 1), String.format("%s-%s", toRunningScore(p1), toRunningScore(p2)));
        lookup.put(Map.entry(2, 3), String.format("%s-%s", toRunningScore(p1), toRunningScore(p2)));
        lookup.put(Map.entry(1, 0), String.format("%s-%s", toRunningScore(p1), toRunningScore(p2)));
        lookup.put(Map.entry(3, 0), String.format("%s-%s", toRunningScore(p1), toRunningScore(p2)));
        lookup.put(Map.entry(3, 1), String.format("%s-%s", toRunningScore(p1), toRunningScore(p2)));
        lookup.put(Map.entry(3, 2), String.format("%s-%s", toRunningScore(p1), toRunningScore(p2)));
        lookup.put(Map.entry(4, 3), "Advantage player1");
        lookup.put(Map.entry(3, 4), "Advantage player2");
        lookup.put(Map.entry(4, 0), null);
        lookup.put(Map.entry(4, 1), null);
        lookup.put(Map.entry(4, 2), null);
        lookup.put(Map.entry(2, 4), null);
        lookup.put(Map.entry(1, 4), null);
        lookup.put(Map.entry(0, 4), null);

        var entry = Map.entry(p1, p2);

        if(!lookup.containsKey(entry)) throw new IllegalArgumentException("Invalid score.");

        if(p1 > 3 && p1 - p2 >= 2) return "Win for player1";
        if(p2 > 3 && p2 - p1 >= 2) return "Win for player2";
        return lookup.get(entry);
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
