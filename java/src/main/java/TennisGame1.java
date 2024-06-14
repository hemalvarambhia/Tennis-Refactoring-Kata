import java.util.Objects;

public class TennisGame1 implements TennisGame {

    private final TennisPlayer player1;
    private final TennisPlayer player2;

    public TennisGame1(String player1Name, String player2Name) {
        player1 = new TennisPlayer(player1Name);
        player2 = new TennisPlayer(player2Name);
    }

    /**
     * Awards a point to the player who won it.
     *
     * @param playerName player who won the point.
     *
     */
    public void wonPoint(String playerName) {
        if(isNotPlaying(playerName)){
          throw new RuntimeException(String.format("%s is not playing the game", playerName));
        }
        if (player1.getName().equals(playerName)) {
            player1.wonPoint();
        } else {
            player2.wonPoint();
        }
    }

    /**
     * Computes the running score for a game of tennis.
     * @return the running score e.g. 15-40, Deuce, Advantage Player 1, Win for Player 2.
     */
    public String getScore() {
        if(isDeuce()) return "Deuce";
        if (player1.hasAdvantageOver(player2)) return advantage(player1);
        if (player2.hasAdvantageOver(player1)) return advantage(player2);

        if (player1.hasBeaten(player2)) {
            return won(player1);
        } else if(player2.hasBeaten(player1)) {
            return won(player2);
        }

        if (scoresAreTied()) return String.format("%s-All", player1.runningScore());

        return String.format("%s-%s", player1.runningScore(), player2.runningScore());
    }

    /**
     * Classifies whether the game was a deuce.
     * @return game is a deuce (true) or it is not (false).
     */
    private boolean isDeuce() {
        return player1.getPoints() >= 3 && scoresAreTied();
    }

    private boolean scoresAreTied() {
        return pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1.pointsDifference(player2);
    }

    /**
     * Computes a string form of the outcome when a player has advantage (e.g. Advantage player 1).
     * @param player player with the advantage.
     * @return Advantage {name of player}
     */
    private String advantage(TennisPlayer player) {
        return String.format("Advantage %s", player.getName());
    }

    /**
     * Computes a string form of the outcome when a player has advantage (e.g. Advantage player 1).
     * @param winner the winner of the game.
     * @return Win for {name of player}
     */
    private String won(TennisPlayer winner) {
        return String.format("Win for %s", winner.getName());
    }

    private boolean isNotPlaying(String playerName) {
        return !Objects.equals(player1.getName(), playerName) && !Objects.equals(player2.getName(), playerName);
    }
}
