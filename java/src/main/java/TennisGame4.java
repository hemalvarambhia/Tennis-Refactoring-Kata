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
            return new TennisResult("Advantage " + getServer(), "").format();

        if (receiverHasAdvantage())
            return String.format("Advantage %s", getReceiver());

        if(serverHasWon())
            return String.format("Win for %s", getServer());

        if(receiverHasWon())
            return new TennisResult("Win for " + getReceiver(), "").format();

        ResultProvider startingResult = new DefaultResult(this);
        return startingResult.getResult().format();
    }

    private boolean receiverHasAdvantage() {
        return receivingPlayer.hasAdvantageOver(servingPlayer);
    }

    private boolean serverHasAdvantage() {
        return servingPlayer.hasAdvantageOver(receivingPlayer);
    }

    boolean receiverHasWon() {
        return receivingPlayer.hasBeaten(servingPlayer);
    }

    boolean serverHasWon() {
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

interface ResultProvider {
    TennisResult getResult();
}

class Deuce implements ResultProvider {
    private final ResultProvider nextResult;

    public Deuce(TennisGame4 game, ResultProvider nextResult) {
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        return this.nextResult.getResult();
    }
}

class GameServer implements ResultProvider {
    private final ResultProvider nextResult;

    public GameServer(TennisGame4 game, ResultProvider nextResult) {
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        return this.nextResult.getResult();
    }
}

class GameReceiver implements ResultProvider {
    private final TennisGame4 game;
    private final ResultProvider nextResult;

    public GameReceiver(TennisGame4 game, ResultProvider nextResult) {
        this.game = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        return this.nextResult.getResult();
    }
}

class AdvantageServer implements ResultProvider {
    private final ResultProvider nextResult;

    public AdvantageServer(TennisGame4 game, ResultProvider nextResult) {
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        return this.nextResult.getResult();
    }
}

class AdvantageReceiver implements ResultProvider {
    private final ResultProvider nextResult;

    public AdvantageReceiver(TennisGame4 game, ResultProvider nextResult) {
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        return this.nextResult.getResult();
    }
}

class DefaultResult implements ResultProvider {

    private static final String[] scores = {"Love", "Fifteen", "Thirty", "Forty"};

    private final TennisGame4 game;

    public DefaultResult(TennisGame4 game) {
        this.game = game;
    }

    @Override
    public TennisResult getResult() {
        return new TennisResult(scores[game.serverScore()], scores[game.receiverScore()]);
    }
}
