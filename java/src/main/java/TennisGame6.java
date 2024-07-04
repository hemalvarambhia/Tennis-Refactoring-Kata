import java.util.Map;

public class TennisGame6 implements TennisGame {
    private final String player1Name;
    private int player1Score;

    private TennisPlayer player1;

    private final String player2Name;
    private int player2Score;
    private TennisPlayer player2;

    private static final Map<Integer, String> runningScoreMap = Map.of(
            0, "Love",
            1, "Fifteen",
            2, "Thirty",
            3, "Forty"
            );

    public TennisGame6(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        player1 = new TennisPlayer(player1Name);
        this.player2Name = player2Name;
        player2 = new TennisPlayer(player2Name);
    }

    @Override
    public void wonPoint(String playerName) {
        if (playerName.equals("player1")) {
            player1.wonPoint();
            player1Score = player1.getPoints();
        } else if(playerName.equals(player2Name)) {
            player2.wonPoint();
            player2Score = player2.getPoints();
        } else {
            throw new IllegalArgumentException("Invalid player name.");
        }
    }

    public String getScore()
    {
        if (isDeuce()) { return "Deuce"; }

        if (player1HasAdvantage()) { return advantageTo(player1Name); }
        if (player2HasAdvantage()) { return advantageTo(player2Name); }

        if (player1HasWon()) { return won(player1Name); }
        if (player2HasWon()) { return won(player2Name); }

        if(scoresAreTied()) { return String.format("%s-All", runningScore(player1Score)); }
        return String.format("%s-%s", runningScore(player1Score), runningScore(player2Score));
    }

    private boolean player2HasWon() {
        return (player1Score > 3 || player2Score > 3) && pointsDifference() <= -2;
    }

    private boolean player1HasWon() {
        return (player1Score > 3 || player2Score > 3) && pointsDifference() >= 2;
    }

    private boolean player2HasAdvantage() {
        return (player1Score > 3 || player2Score > 3) && pointsDifference() == -1;
    }

    private boolean player1HasAdvantage() {
        return (player1Score > 3 || player2Score > 3) && pointsDifference() == 1;
    }

    private boolean isDeuce() {
        return player1Score >= 3 && pointsDifference() == 0;
    }

    private boolean scoresAreTied() {
        return player1Score < 3 && pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1Score - player2Score;
    }

    private static String advantageTo(String player) {
        return String.format("Advantage %s", player);
    }

    private static String won(String player) {
        return String.format("Win for %s", player);
    }

    private static String runningScore(Integer playerScore) {
       return runningScoreMap.get(playerScore);
    }
}
