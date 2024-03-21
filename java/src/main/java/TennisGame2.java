import java.util.Map;

public class TennisGame2 implements TennisGame
{
    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
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
            return String.format("%s-All", toRunningScore(player1.getPoints()));
        }
        return String.format(
                "%s-%s", toRunningScore(player1.getPoints()), toRunningScore(player2.getPoints())
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

    private String toRunningScore(Integer points) {
        return POINTS_TO_RUNNING_SCORE.get(points);
    }

    public void wonPoint(String player) {
        if (player == "player1") {
            player1.wonPoint();
        } else {
            player2.wonPoint();
        }
    }

    private String player1Name;
    private TennisPlayer player1;
    private String player2Name;

    private TennisPlayer player2;

    private static final Map<Integer, String> POINTS_TO_RUNNING_SCORE =
        Map.of(
            0, "Love",
                1, "Fifteen",
                2, "Thirty",
                3, "Forty"
        );
}