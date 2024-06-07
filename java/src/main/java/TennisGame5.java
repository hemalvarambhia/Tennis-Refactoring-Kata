import java.util.HashMap;
import java.util.Map;

public class TennisGame5 implements TennisGame {

    private final String player1Name;
    private int player1Score;
    private final String player2Name;
    private int player2Score;

    public TennisGame5(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    @Override
    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1Score++;
        else if (playerName.equals(player2Name))
            player2Score++;
        else
            throw new IllegalArgumentException("Invalid player name.");
    }

    @Override
    public String getScore() {
        if(player1Score > 16 || player2Score > 16) throw new IllegalArgumentException("Invalid score.");
        if(isDeuce()) return "Deuce";
        if(player1Score < 3 && pointsDifference() == 0) return String.format("%s-All", toRunningScore(player1Score));

        if(advantagePlayer1()) return String.format("Advantage %s", player1Name);
        if(player1HasWon()) return String.format("Win for %s", player1Name);
        if(advantagePlayer2()) return String.format("Advantage %s", player2Name);
        if(player2HasWon()) return String.format("Win for %s", player2Name);
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
