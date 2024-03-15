import java.util.stream.Stream;

public class TennisGame1 implements TennisGame {

    private final TennisPlayer player1;
    private final TennisPlayer player2;

    public TennisGame1(String player1Name, String player2Name) {
        player1 = new TennisPlayer(player1Name);
        player2 = new TennisPlayer(player2Name);
    }

    public void wonPoint(String playerName) {
        checkPlayerPresence(playerName);
        if (player1.getName().equals(playerName)) {
            player1.wonPoint();
        } else {
            player2.wonPoint();
        }
    }

    public String getScore() {
        if(isDeuce()) return "Deuce";
        if (player1.hasAdvantageOver(player2)) return advantage(player1);
        if (player2.hasAdvantageOver(player1)) return advantage(player2);

        if (player1.hasBeaten(player2)) {
            return won(player1);
        } else if(player2.hasBeaten(player1)) {
            return won(player2);
        }

        if (scoresAreEqual()) return String.format("%s-All", player1.runningScore());

        return String.format("%s-%s", player1.runningScore(), player2.runningScore());
    }

    private boolean isDeuce() {
        return player1.getPoints() >= 3 && scoresAreEqual();
    }

    private boolean scoresAreEqual() {
        return player1.pointsDifference(player2) == 0;
    }

    private String advantage(TennisPlayer player) {
        return String.format("Advantage %s", player.getName());
    }

    private String won(TennisPlayer winner) {
        return String.format("Win for %s", winner.getName());
    }

    private void checkPlayerPresence(String playerName) {
        Stream.of(player1, player2)
                .map(TennisPlayer::getName)
                .filter(name -> name.equals(playerName))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("%s is not playing the game", playerName)));
    }
}
