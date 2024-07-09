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

        if (player1.hasAdvantageOver(player2)) { return advantage(player1); }
        if (player2.hasAdvantageOver(player1)) { return advantage(player2); }

        if (player1.hasBeaten(player2)) { return won(player1); }
        if (player2.hasBeaten(player1)) { return won(player2); }

        if(scoresAreTied()) { return String.format("%s-All", player1.runningScore()); }
        return String.format("%s-%s", player1.runningScore(), player2.runningScore());
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

    private String won(TennisPlayer player) {
        return String.format("Win for %s", player);
    }

    private String advantage(TennisPlayer player) {
        return TennisGame6.advantage(player.getName());
    }

}
