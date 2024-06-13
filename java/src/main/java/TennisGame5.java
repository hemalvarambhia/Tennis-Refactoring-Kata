import java.util.Map;

public class TennisGame5 implements TennisGame {
    private final TennisPlayer player1;
    private final TennisPlayer player2;

    public TennisGame5(String player1Name, String player2Name) {
        player1 = new TennisPlayer(player1Name);
        player2 = new TennisPlayer(player2Name);
    }

    @Override
    public void wonPoint(String playerName) {
        if (playerName.equals(player1.getName())) {
            player1.wonPoint();
        } else if (playerName.equals(player2.getName())) {
            player2.wonPoint();
        } else
            throw new IllegalArgumentException("Invalid player name.");
    }

    @Override
    public String getScore() {
        if(player1.getPoints() > 16 || player2.getPoints() > 16) throw new IllegalArgumentException("Invalid score.");
        if(isDeuce()) return "Deuce";
        if(isTied()) return String.format("%s-All", player1.runningScore());

        if(advantagePlayer1()) return String.format("Advantage %s", player1);
        if(player1HasWon()) return String.format("Win for %s", player1);
        if(advantagePlayer2()) return String.format("Advantage %s", player2);
        if(player2HasWon()) return String.format("Win for %s", player2);
        return String.format("%s-%s", player1.runningScore(), player2.runningScore());
    }

    private boolean isTied() {
        return player1.getPoints() < 3 && pointsDifference() == 0;
    }

    private boolean player2HasWon() {
        return player2.hasBeaten(player1);
    }

    private boolean advantagePlayer2() {
        return player2.hasAdvantageOver(player1);
    }

    private boolean advantagePlayer1() {
        return player1.hasAdvantageOver(player2);
    }

    private boolean player1HasWon() {
        return player1.hasBeaten(player2);
    }

    private boolean isDeuce() {
        return player1.getPoints() >= 3 && pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1.pointsDifference(player2);
    }
}
