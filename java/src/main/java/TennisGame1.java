import java.util.Arrays;
import java.util.List;

public class TennisGame1 implements TennisGame {

    private final TennisPlayer player1;
    private final TennisPlayer player2;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1 = new TennisPlayer(player1Name);
        this.player2 = new TennisPlayer(player2Name);
    }

    public void wonPoint(String playerName) {
        List<String> players = Arrays.asList(player1.getName(), player2.getName());
        if(!players.contains(playerName)){
          throw new RuntimeException(String.format("%s is not playing the game", playerName));
        }
        if (player1.getName().equals(playerName)) {
            player1.wonPoint();
        } else {
            player2.wonPoint();
        }
    }

    public String getScore() {
        if(isDeuce()) return "Deuce";
        if (isAdvantagePlayer1()) return advantage(player1);
        if (isAdvantagePlayer2()) return advantage(player2);

        if (player1Won()) {
            return won(player1);
        } else if(player2Won()) {
            return won(player2);
        }

        if (scoresAreEqual()) return String.format("%s-All", player1.runningScore());

        return String.format("%s-%s", player1.runningScore(), player2.runningScore());
    }

    private boolean isDeuce() {
        return player1.getPoints() >= 3 && scoresAreEqual();
    }

    private boolean scoresAreEqual() {
        return pointsDifference() == 0;
    }

    private boolean isAdvantagePlayer1() {
        return player1.hasAdvantageOver(player2);
    }

    private boolean isAdvantagePlayer2() {
        return  player2.hasAdvantageOver(player1);
    }

    private String advantage(TennisPlayer player) {
        return String.format("Advantage %s", player.getName());
    }

    private boolean player1Won() {
        return player1.hasBeaten(player2);
    }

    private boolean player2Won() {
        return player2.hasBeaten(player1);
    }

    private String won(TennisPlayer winner) {
        return String.format("Win for %s", winner.getName());
    }

    private int pointsDifference() {
        return player1.pointsDifference(player2);
    }
}
