public class TennisGame3 implements TennisGame {

    private final TennisPlayer player1;
    private final TennisPlayer player2;

    public TennisGame3(String player1Name, String player2Name) {
        player1 = new TennisPlayer(player1Name);
        player2 = new TennisPlayer(player2Name);
    }

    public String getScore() {
        if(isDeuce()) return "Deuce";
        if (player1.hasAdvantageOver(player2)) return advantage(player1.getName());
        if (player2.hasAdvantageOver(player1)) return advantage(player2.getName());
        if (player1.hasBeaten(player2)) return winner(player1);
        if(player2.hasBeaten(player1)) return winner(player2);

        if (scoresAreTied())
            return String.format("%s-All", player1.runningScore());

        return String.format("%s-%s", player1.runningScore(), player2.runningScore());
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1.getName())) {
            player1.wonPoint();
        } else {
            player2.wonPoint();
        }
    }

    private boolean isDeuce() {
        return player1.getPoints() >= 3 && scoresAreTied();
    }

    private boolean scoresAreTied() {
        return pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1.pointsDifference(player2);
    }

    private static String winner(TennisPlayer player) {
        return String.format("Win for %s", player);
    }

    private static String advantage(String leadingPlayer) {
        return String.format("Advantage %s", leadingPlayer);
    }

}
