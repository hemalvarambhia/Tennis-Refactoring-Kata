public class TennisGame4 implements TennisGame {

    private final TennisPlayer servingPlayer;
    private final TennisPlayer receivingPlayer;

    public TennisGame4(String player1, String player2) {
        this.servingPlayer = new TennisPlayer(player1);
        this.receivingPlayer = new TennisPlayer(player2);
    }

    @java.lang.Override
    public void wonPoint(String playerName) {
        if (servingPlayer.getName().equals(playerName)) {
            this.servingPlayer.wonPoint();
        } else {
            this.receivingPlayer.wonPoint();
        }
    }

    @java.lang.Override
    public String getScore() {
        if(isDeuce())
            return "Deuce";

        if(servingPlayer.hasAdvantageOver(receivingPlayer))
            return advantage(servingPlayer);

        if (receivingPlayer.hasAdvantageOver(servingPlayer))
            return advantage(receivingPlayer);

        if(servingPlayer.hasBeaten(receivingPlayer))
            return won(servingPlayer);

        if(receivingPlayer.hasBeaten(servingPlayer))
            return won(receivingPlayer);

        if (scoresAreTied())
            return servingPlayer.runningScore() + "-All";
        return String.format(
                "%s-%s", servingPlayer.runningScore(), receivingPlayer.runningScore()
        );
    }

    private boolean isDeuce() {
        return servingPlayer.getPoints() >=3 && receivingPlayer.getPoints() >=3
                && scoresAreTied();
    }


    private boolean scoresAreTied() {
        return servingPlayer.pointsDifference(receivingPlayer) == 0;
    }

    private static String won(TennisPlayer player) {
        return String.format("Win for %s", player);
    }

    private static String advantage(TennisPlayer player) {
        return String.format("Advantage %s", player);
    }

}
