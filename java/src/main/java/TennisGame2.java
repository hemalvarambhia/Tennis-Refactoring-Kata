import java.util.Objects;

public class TennisGame2 implements TennisGame
{
    public TennisGame2(String player1Name, String player2Name) {
        player1 = new TennisPlayer(player1Name);
        player2 = new TennisPlayer(player2Name);
    }

    public String getScore(){
        if (isDeuce()) return "Deuce";
        if (player1.hasAdvantageOver(player2)) return advantage(player1);
        if (player2.hasAdvantageOver((player1))) return advantage(player2);
        if (player1.hasBeaten(player2)) return won(player1);
        if (player2.hasBeaten(player1)) return won(player2);

        if (scoresAreTied()) return String.format("%s-All", player1.runningScore());

        return String.format("%s-%s", player1.runningScore(), player2.runningScore());
    }

    private boolean isDeuce() {
        return player1.getPoints() >= 3 && scoresAreTied();
    }

    private boolean scoresAreTied() {
        return pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1.pointsDifference((player2));
    }

    public void wonPoint(String player) {
        if(isNotPlaying(player)){
            throw new RuntimeException(String.format("%s is not playing the game", player));
        }
        if (player.equals(player1.getName())) {
            player1.wonPoint();
        } else {
            player2.wonPoint();
        }
    }

    private boolean isNotPlaying(String playerName) {
        return !Objects.equals(player1.getName(), playerName) && !Objects.equals(player2.getName(), playerName);
    }

    private String advantage(TennisPlayer player) {
        return String.format("Advantage %s", player);
    }

    private String won(TennisPlayer player) {
        return String.format("Win for %s", player.getName());
    }

    private final TennisPlayer player1;

    private final TennisPlayer player2;
}