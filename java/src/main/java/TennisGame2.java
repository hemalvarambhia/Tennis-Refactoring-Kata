import java.util.Map;

public class TennisGame2 implements TennisGame
{
    public TennisGame2(String player1Name, String player2Name) {
        player1 = new TennisPlayer(player1Name);
        player2 = new TennisPlayer(player2Name);
    }

    public String getScore(){
        if (isDeuce()) return "Deuce";
        if (player1.hasAdvantageOver(player2)) return String.format("Advantage %s", player1.getName());
        if (player2.hasAdvantageOver((player1))) return "Advantage player2";
        if (player1.hasBeaten(player2)) return "Win for player1";
        if (player2.hasBeaten(player1)) return "Win for player2";

        if (scoresAreEqual())
        {
            return String.format("%s-All", player1.runningScore());
        }
        return String.format(
                "%s-%s", player1.runningScore(), player2.runningScore()
        );
    }

    private boolean isDeuce() {
        return scoresAreEqual() && player1.getPoints() >= 3;
    }

    private boolean scoresAreEqual() {
        return pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1.pointsDifference((player2));
    }

    public void wonPoint(String player) {
        if (player.equals(player1.getName())) {
            player1.wonPoint();
        } else {
            player2.wonPoint();
        }
    }

    private TennisPlayer player1;

    private TennisPlayer player2;

    private static final Map<Integer, String> POINTS_TO_RUNNING_SCORE =
        Map.of(
            0, "Love",
                1, "Fifteen",
                2, "Thirty",
                3, "Forty"
        );
}