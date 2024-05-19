public class TennisGame4 implements TennisGame {

    private final TennisPlayer servingPlayer;
    private final TennisPlayer receivingPlayer;

    public TennisGame4(String player1, String player2) {
        this.servingPlayer = new TennisPlayer(player1);
        this.receivingPlayer = new TennisPlayer(player2);
    }

    @java.lang.Override
    public void wonPoint(String playerName) {
        if (getServer().equals(playerName)) {
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
            return String.format("Advantage %s", getServer());

        if (receiverHasAdvantage())
            return String.format("Advantage %s", getReceiver());

        if(serverHasWon())
            return String.format("Win for %s", getServer());

        if(receiverHasWon())
            return String.format("Win for %s", getReceiver());

        return runningScore();
    }

    private Integer serverScore() {
        return servingPlayer.getPoints();
    }

    private String getServer() {
        return servingPlayer.getName();
    }

    private String getReceiver() {
        return receivingPlayer.getName();
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
        if (serverScore().equals(receivingPlayer.getPoints()))
            return toRunningScore(servingPlayer.getPoints()) + "-All";
        return servingPlayer.runningScore() + "-" + receivingPlayer.runningScore();
    }

    private boolean isDeuce() {
        return servingPlayer.getPoints() >=3 && receivingPlayer.getPoints() >=3 && servingPlayer.pointsDifference(receivingPlayer) == 0;
    }

    private static final String[] SCORES = {"Love", "Fifteen", "Thirty", "Forty"};

    private static String toRunningScore(Integer score) {
        return SCORES[score];
    }
}
