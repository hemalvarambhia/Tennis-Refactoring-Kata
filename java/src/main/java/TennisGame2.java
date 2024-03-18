import java.util.Map;

public class TennisGame2 implements TennisGame
{
    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore(){
        if (isDeuce()) return "Deuce";
        if (player1HasAdvantage()) return "Advantage player1";
        if (player2HasAdvantage()) return "Advantage player2";
        if (player1Won()) return "Win for player1";
        if (player2Won()) return "Win for player2";

        if (scoresAreEqual())
        {
            String score = toRunningScore(player1Point);
            return String.format("%s-All", score);
        }
        return String.format(
                "%s-%s", toRunningScore(player1Point), toRunningScore(player2Point)
        );
    }

    private boolean player2Won() {
        return player2Point >= 4 && pointsDifference() <= -2;
    }

    private boolean player1Won() {
        return player1Point >= 4 && pointsDifference() >= 2;
    }

    private boolean isDeuce() {
        return scoresAreEqual() && player1Point >= 3;
    }

    private boolean player2HasAdvantage() {
        return pointsDifference() == -1 && player2Point > 3;
    }

    private boolean player1HasAdvantage() {
        return pointsDifference() == 1 && player1Point > 3;
    }

    private boolean scoresAreEqual() {
        return pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1Point - player2Point;
    }

    private String toRunningScore(Integer points) {
        return POINTS_TO_RUNNING_SCORE.get(points);
    }

    public void wonPoint(String player) {
        if (player == "player1")
            player1Point++;
        else
            player2Point++;
    }

    private int player1Point = 0;
    private int player2Point = 0;

    private String player1Name;
    private String player2Name;

    private static final Map<Integer, String> POINTS_TO_RUNNING_SCORE =
        Map.of(
            0, "Love",
                1, "Fifteen",
                2, "Thirty",
                3, "Forty"
        );
}