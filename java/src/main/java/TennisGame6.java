import java.util.Map;

public class TennisGame6 implements TennisGame {
    private final TennisPlayer player1;

    private final TennisPlayer player2;

    private static final Map<Integer, String> runningScoreMap = Map.of(
            0, "Love",
            1, "Fifteen",
            2, "Thirty",
            3, "Forty"
            );

    public TennisGame6(String player1Name, String player2Name) {
        player1 = new TennisPlayer(player1Name);
        player2 = new TennisPlayer(player2Name);
    }

    @Override
    public void wonPoint(String playerName) {
        if (playerName.equals(player1.getName())) {
            player1.wonPoint();
        } else if(playerName.equals(player2.getName())) {
            player2.wonPoint();
        } else {
            throw new IllegalArgumentException("Invalid player name.");
        }
    }

    public String getScore()
    {
        if (isDeuce()) { return "Deuce"; }

        if (player1HasAdvantage()) { return advantage(player1.getName()); }
        if (player2HasAdvantage()) { return advantage(player2.getName()); }

        if (player1HasWon()) { return won(player1.getName()); }
        if (player2HasWon()) { return won(player2.getName()); }

        if(scoresAreTied()) { return String.format("%s-All", runningScore(player1.getPoints())); }
        return String.format("%s-%s", runningScore(player1.getPoints()), runningScore(player2.getPoints()));
    }

    private boolean player2HasWon() {
        return player2.hasBeaten(player1);
    }

    private boolean player1HasWon() {
        return player1.hasBeaten(player2);
    }

    private boolean player2HasAdvantage() {
        return player2.hasAdvantageOver(player1);
    }

    private boolean player1HasAdvantage() {
        return player1.hasAdvantageOver(player2);
    }

    private boolean isDeuce() {
        return player1.getPoints() >= 3 && pointsDifference() == 0;
    }

    private boolean scoresAreTied() {
        return player1.getPoints() < 3 && pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1.pointsDifference(player2);
    }

    private static String advantage(String player) {
        return String.format("Advantage %s", player);
    }

    private static String won(String player) {
        return String.format("Win for %s", player);
    }

    private static String runningScore(Integer playerScore) {
       return runningScoreMap.get(playerScore);
    }
}
