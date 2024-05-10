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

    public Integer serverScore() {
        return servingPlayer.getPoints();
    }

    public Integer receiverScore() {
        return receivingPlayer.getPoints();
    }

    public String getServer() {
        return servingPlayer.getName();
    }

    public String getReceiver() {
        return receivingPlayer.getName();
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

        DefaultResult startingResult = new DefaultResult(this);
        return startingResult.runningScore();

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

    private boolean isDeuce() {
        return servingPlayer.getPoints() >=3 && receivingPlayer.getPoints() >=3 && servingPlayer.pointsDifference(receivingPlayer) == 0;
    }
}

class TennisResult {
    String serverScore;
    String receiverScore;

    TennisResult(String serverScore, String receiverScore) {
        this.serverScore = serverScore;
        this.receiverScore = receiverScore;
    }

    String format() {
        if ("".equals(this.receiverScore))
            return this.serverScore;
        if (serverScore.equals(receiverScore))
            return serverScore + "-All";
        return this.serverScore + "-" + this.receiverScore;
    }
}

class DefaultResult {

    private static final String[] scores = {"Love", "Fifteen", "Thirty", "Forty"};

    private final TennisGame4 game;

    public DefaultResult(TennisGame4 game) {
        this.game = game;
    }

    public TennisResult getResult() {
        return new TennisResult(scores[game.serverScore()], scores[game.receiverScore()]);
    }

    public String runningScore() { return getResult().format(); }
}
