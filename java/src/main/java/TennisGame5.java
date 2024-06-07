import java.util.HashMap;
import java.util.Map;

public class TennisGame5 implements TennisGame {

    private final String player1Name;
    private final String player2Name;
    private int player1Score;
    private int player2Score;

    private Map<Map.Entry<Integer, Integer>, String> lookup;

    public TennisGame5(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        lookup = getScoreMap();
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
        if(player1Score > 16 || player2Score > 16) throw new IllegalArgumentException("Invalid score.");
        if(isDeuce()) return "Deuce";
        if(player1Score < 3 && pointsDifference() == 0) return String.format("%s-All", toRunningScore(player1Score));

        if(advantagePlayer1()) return "Advantage player1";
        if(player1HasWon()) return "Win for player1";
        if(advantagePlayer2()) return "Advantage player2";
        if(player2HasWon()) return "Win for player2";
        return String.format("%s-%s", toRunningScore(player1Score), toRunningScore(player2Score));
    }

    private boolean player2HasWon() {
        return player2Score > 3 && pointsDifference() <= -2;
    }

    private boolean advantagePlayer2() {
        return player2Score > 3 && pointsDifference() == -1;
    }

    private boolean advantagePlayer1() {
        return player1Score > 3 && pointsDifference() == 1;
    }

    private boolean player1HasWon() {
        return player1Score > 3 && pointsDifference() >= 2;
    }

    private boolean isDeuce() {
        return player1Score >= 3 && pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1Score - player2Score;
    }

    private Map<Map.Entry<Integer, Integer>, String> getScoreMap() {
        Map<Map.Entry<Integer, Integer>, String> lookup = new HashMap<>();
        lookup.put(Map.entry(0, 1), null);
        lookup.put(Map.entry(0, 2), null);
        lookup.put(Map.entry(0, 3), null);
        lookup.put(Map.entry(1, 2), null);
        lookup.put(Map.entry(1, 3), null);
        lookup.put(Map.entry(2, 0), null);
        lookup.put(Map.entry(2, 1), null);
        lookup.put(Map.entry(2, 3), null);
        lookup.put(Map.entry(1, 0), null);
        lookup.put(Map.entry(3, 0), null);
        lookup.put(Map.entry(3, 1), null);
        lookup.put(Map.entry(3, 2), null);
        lookup.put(Map.entry(4, 3), null);
        lookup.put(Map.entry(3, 4), null);
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
