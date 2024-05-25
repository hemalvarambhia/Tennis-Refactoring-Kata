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

        if(serverHasAdvantage())
            return advantage(servingPlayer);

        if (receiverHasAdvantage())
            return advantage(receivingPlayer);

        if(serverHasWon())
            return winFor(servingPlayer);

        if(receiverHasWon())
            return winFor(receivingPlayer);

        return runningScore();
    }

    private String winFor(TennisPlayer player) {
        return String.format("Win for %s", player.getName());
    }

    private String advantage(TennisPlayer player) {
        return String.format("Advantage %s", player.getName());
    }

    private boolean receiverHasAdvantage() {
        return receivingPlayer.hasAdvantageOver(servingPlayer);
    }

    private boolean serverHasAdvantage() {
        return servingPlayer.hasAdvantageOver(receivingPlayer);
    }

    private boolean receiverHasWon() {
        return receivingPlayer.hasBeaten(servingPlayer);
    }

    private boolean serverHasWon() {
        return servingPlayer.hasBeaten(receivingPlayer);
    }

    private String runningScore() {
        if (servingPlayer.pointsDifference(receivingPlayer) == 0)
            return servingPlayer.runningScore() + "-All";
        return String.format(
                "%s-%s", servingPlayer.runningScore(), receivingPlayer.runningScore()
        );
    }

    private boolean isDeuce() {
        return servingPlayer.getPoints() >=3 && receivingPlayer.getPoints() >=3
                && servingPlayer.pointsDifference(receivingPlayer) == 0;
    }
}
