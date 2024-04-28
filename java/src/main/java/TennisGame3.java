import java.util.Map;

public class TennisGame3 implements TennisGame {
    
    private final String player1Name;
    private final TennisPlayer player1;

    private final String player2Name;
    private final TennisPlayer player2;

    public TennisGame3(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        player1 = new TennisPlayer(player1Name);
        this.player2Name = player2Name;
        player2 = new TennisPlayer(player2Name);
    }

    public String getScore() {
        if(thereIsAWinner()) {
            return win(leadingPlayer());
        }

        if (isAdvantage()) {
            return advantage(leadingPlayer());
        }

        if(player1.getPoints() >= 3 && scoresAreEqual()) {
            return "Deuce";
        }

        if (scoresAreEqual())
            return String.format("%s-All", toRunningScore(player1.getPoints()));

        return String.format("%s-%s", toRunningScore(player1.getPoints()), toRunningScore(player2.getPoints()));
    }

    private boolean thereIsAWinner() {
        return (player1.getPoints() > 3 || player2.getPoints() > 3) && Math.abs(pointsDifference()) >= 2;
    }

    private static String win(String leadingPlayer) {
        return String.format("Win for %s", leadingPlayer);
    }

    private boolean isAdvantage() {
        return (player1.getPoints() > 3 || player2.getPoints() > 3) && Math.abs(pointsDifference()) == 1;
    }

    private static String advantage(String leadingPlayer) {
        return String.format("Advantage %s", leadingPlayer);
    }

    private int pointsDifference() {
        return player1.getPoints() - player2.getPoints();
    }

    private String leadingPlayer() {
        return player1.getPoints() > player2.getPoints() ? player1Name : player2Name;
    }

    private boolean scoresAreEqual() {
        return pointsDifference() == 0;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1.wonPoint();
        } else {
            player2.wonPoint();
        }
    }

    private String toRunningScore(Integer points) {
        return POINTS_TO_RUNNING_SCORE.get(points);
    }

    private static final Map<Integer, String> POINTS_TO_RUNNING_SCORE = Map.of(
            0, "Love",
            1, "Fifteen",
            2, "Thirty",
            3, "Forty"
    );

}
