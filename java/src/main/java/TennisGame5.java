import java.util.Map;

public class TennisGame5 implements TennisGame {

    private final String player1Name;
    private int player1Score;
    private final TennisPlayer player1;
    private final String player2Name;
    private int player2Score;
    private final TennisPlayer player2;

    public TennisGame5(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        player1 = new TennisPlayer(player1Name);
        this.player2Name = player2Name;
        player2 = new TennisPlayer(player2Name);
    }

    @Override
    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score++;
            player1.wonPoint();
        } else if (playerName.equals(player2Name)) {
            player2Score++;
            player2.wonPoint();
        } else
            throw new IllegalArgumentException("Invalid player name.");
    }

    @Override
    public String getScore() {
        if(player1.getPoints() > 16 || player2.getPoints() > 16) throw new IllegalArgumentException("Invalid score.");
        if(isDeuce()) return "Deuce";
        if(player1.getPoints() < 3 && pointsDifference() == 0) return String.format("%s-All", player1.runningScore());

        if(advantagePlayer1()) return String.format("Advantage %s", player1.getName());
        if(player1HasWon()) return String.format("Win for %s", player1.getName());
        if(advantagePlayer2()) return String.format("Advantage %s", player2.getName());
        if(player2HasWon()) return String.format("Win for %s", player2.getName());
        return String.format("%s-%s", player1.runningScore(), player2.runningScore());
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
        return player1.hasBeaten(player2);
    }

    private boolean isDeuce() {
        return player1Score >= 3 && pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1.pointsDifference(player2);
    }
}
