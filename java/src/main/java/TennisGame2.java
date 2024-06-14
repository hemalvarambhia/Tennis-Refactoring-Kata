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
        if (player1.hasBeaten(player2)) return String.format("Win for %s", player1.getName());
        if (player2.hasBeaten(player1)) return String.format("Win for %s", player2.getName());

        if (scoresAreTied()) return String.format("%s-All", player1.runningScore());

        return String.format("%s-%s", player1.runningScore(), player2.runningScore());
    }

    private boolean isDeuce() {
        return scoresAreTied() && player1.getPoints() >= 3;
    }

    private boolean scoresAreTied() {
        return pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1.pointsDifference((player2));
    }

    public void wonPoint(String player) {
        if (player.equals(player1.getName())) {
            player1.wonPoint();
        } else {
            player2.wonPoint();
        }
    }

    private String advantage(TennisPlayer player) {
        return String.format("Advantage %s", player);
    }

    private final TennisPlayer player1;

    private final TennisPlayer player2;
}