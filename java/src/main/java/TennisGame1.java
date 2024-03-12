import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TennisGame1 implements TennisGame {

    private final TennisPlayer player1;
    private final TennisPlayer player2;

    private static final Map<Integer, String> POINTS_TO_RUNNING_SCORE = Map.of(
            0, "Love",
            1, "Fifteen",
            2, "Thirty",
            3, "Forty"
    );

    public TennisGame1(String player1Name, String player2Name) {
        this.player1 = new TennisPlayer(player1Name);
        this.player2 = new TennisPlayer(player2Name);
    }

    public void wonPoint(String playerName) {
        List<String> players = Arrays.asList(player1.getName(), player2.getName());
        if(!players.contains(playerName)){
          throw new RuntimeException(String.format("%s is not playing the game", playerName));
        }
        if (player1.getName().equals(playerName)) {
            player1.wonPoint();
        } else {
            player2.wonPoint();
        }
    }

    public String getScore() {
        if(isDeuce()) return "Deuce";
        if (isAdvantagePlayer1()) return String.format("Advantage %s", player1.getName());
        if (isAdvantagePlayer2()) return String.format("Advantage %s", player2.getName());

        if (player1Won()) {
            return String.format("Win for %s", player1.getName());
        } else if(player2Won()) {
            return String.format("Win for %s", player2.getName());
        }

        if (equalScores()) return String.format("%s-All", runningScore(player1.getPoints()));

        return String.format("%s-%s", runningScore(player1.getPoints()), runningScore(player2.getPoints()));
    }

    private boolean isDeuce() {
        return player1.getPoints() >= 3 && equalScores();
    }

    private boolean equalScores() {
        return player1.getPoints().equals(player2.getPoints());
    }

    private boolean isAdvantagePlayer1() {
        return (player1.getPoints() >= 4 || player2.getPoints() >= 4) && pointsDifference() == 1;
    }

    private boolean isAdvantagePlayer2() {
        return (player1.getPoints() >=4 || player2.getPoints()>=4) && pointsDifference() == -1;
    }

    private boolean player1Won() {
        return (player1.getPoints() >=4 || player2.getPoints() >=4) && pointsDifference() >=2;
    }

    private boolean player2Won() {
        return (player1.getPoints() >=4 || player2.getPoints() >=4) && pointsDifference() <= -2;
    }

    private int pointsDifference() {
        return player1.getPoints() - player2.getPoints();
    }

    private String runningScore(int points) {
        return POINTS_TO_RUNNING_SCORE.get(points);
    }
}
