import java.util.Map;

public class TennisGame3 implements TennisGame {

    private final TennisPlayer player1;
    private final TennisPlayer player2;

    public TennisGame3(String player1Name, String player2Name) {
        player1 = new TennisPlayer(player1Name);
        player2 = new TennisPlayer(player2Name);
    }

    public String getScore() {
        if(thereIsAWinner()) {
            return win(leadingPlayer());
        }

        if (isAdvantage()) {
            return advantage(leadingPlayer());
        }

        if(isDeuce()) {
            return "Deuce";
        }

        if (scoresAreEqual())
            return String.format("%s-All", runningScoreFor(player1));

        return String.format("%s-%s", runningScoreFor(player1), runningScoreFor(player2));
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1.getName())) {
            player1.wonPoint();
        } else {
            player2.wonPoint();
        }
    }

    private boolean thereIsAWinner() {
        return player1.hasBeaten(player2) || player2.hasBeaten(player1);
    }

    private static String win(String leadingPlayer) {
        return String.format("Win for %s", leadingPlayer);
    }

    private boolean isAdvantage() {
        return player1.hasAdvantageOver(player2) || player2.hasAdvantageOver(player1);
    }

    private static String advantage(String leadingPlayer) {
        return String.format("Advantage %s", leadingPlayer);
    }

    private int pointsDifference() {
        return player1.pointsDifference(player2);
    }

    private String leadingPlayer() {
        return player1.getPoints() > player2.getPoints() ? player1.getName() : player2.getName();
    }

    private boolean isDeuce() {
        return player1.getPoints() >= 3 && scoresAreEqual();
    }

    private boolean scoresAreEqual() {
        return pointsDifference() == 0;
    }

    private String runningScoreFor(TennisPlayer player) {
        return player.runningScore();
    }
}
