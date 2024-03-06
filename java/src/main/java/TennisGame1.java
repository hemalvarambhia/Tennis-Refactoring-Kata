import java.util.Map;
import java.util.HashMap;
public class TennisGame1 implements TennisGame {

    private final String player1Name;
    private int player1Points = 0;
    private final String player2Name;
    private int player2Points = 0;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName))
            player1Points += 1;
        else
            player2Points += 1;
    }

    public String getScore() {
        String score = "";
        if(isDeuce()) return "Deuce";
        if (equalScores())
        {
            return String.format("%s-All", runningScore(player1Points));
        }
        else if (player1Points >=4 || player2Points >=4)
        {
            if (pointsDifference() ==1) return String.format("Advantage %s", player1Name);
            else if (pointsDifference() ==-1) return String.format("Advantage %s", player2Name);
            else if (pointsDifference() >=2) return String.format("Win for %s", player1Name);
            else return String.format("Win for %s", player2Name);
        }
        else
        {
            return String.format("%s-%s", runningScore(player1Points), runningScore(player2Points));
        }
    }

    private boolean isDeuce() {
        return player1Points >= 3 && equalScores();
    }

    private boolean equalScores() {
        return player1Points == player2Points;
    }

    private int pointsDifference() {
        return player1Points - player2Points;
    }

    private String runningScore(int points) {
        Map<Integer, String> pointsToScore = new HashMap<Integer, String>() {{
            put(0, "Love");
            put(1, "Fifteen");
            put(2, "Thirty");
            put(3, "Forty");
        }};

        return pointsToScore.get(points);
    }
}
