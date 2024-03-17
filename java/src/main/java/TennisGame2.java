import java.util.Map;

public class TennisGame2 implements TennisGame
{
    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore(){
        if (scoresAreEqual() && player1Point >=3)
            return "Deuce";

        if (player1HasAdvantage()) return "Advantage player1";
        if (player2HasAdvantage()) return "Advantage player2";
        if (player1Point >=4 && pointsDifference() >=2)
        {
            return "Win for player1";
        }
        if (player2Point >=4 && (player2Point - player1Point)>=2)
        {
            return "Win for player2";
        }


        if (scoresAreEqual())
        {
            String score = POINTS_TO_RUNNING_SCORE.get(player1Point);
            return String.format("%s-All", score);
        }
        player1RunningScore = POINTS_TO_RUNNING_SCORE.get(player1Point);
        player2RunningScore = POINTS_TO_RUNNING_SCORE.get(player2Point);
        String score = player1RunningScore + "-" + player2RunningScore;

        return score;
    }

    private boolean player2HasAdvantage() {
        return player2Point - player1Point == 1 && player2Point > 3;
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

    public void wonPoint(String player) {
        if (player == "player1")
            player1Point++;
        else
            player2Point++;
    }

    private int player1Point = 0;
    private int player2Point = 0;

    private String player1RunningScore = "";
    private String player2RunningScore = "";
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