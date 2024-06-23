import java.util.Map;

public class TennisGame6 implements TennisGame {
    private final String player1Name;
    private final String player2Name;
    private int player1Score;
    private int player2Score;

    private static final Map<Integer, String> runningScoreMap = Map.of(
            0, "Love",
            1, "Fifteen",
            2, "Thirty",
            3, "Forty"
            );

    public TennisGame6(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    @Override
    public void wonPoint(String playerName) {
        if (playerName.equals("player1"))
            player1Score++;
        else
            player2Score++;

    }

    public String getScore()
    {
        if(scoresAreTied()) {
            String runningScore = runningScoreMap.get(player1Score);
            return String.format("%s-All", runningScore);
        }

        if (isDeuce()) {
           return "Deuce";
        }
        if (player1Score > 3 || player2Score > 3)
        {
            if (pointsDifference() == 1) {
                return advantageTo(player1Name);
            } else if (pointsDifference() == -1) {
                return advantageTo(player2Name);
            } else if (pointsDifference() >= 2) {
                return String.format("Win for %s", player1Name);
            } else {
                return String.format("Win for %s", player2Name);
            }
        }
        else
        {
            String score1 = runningScoreMap.get(player1Score);
            String player2RunningScore = runningScoreMap.get(player2Score);

            return score1 + "-" + player2RunningScore;
        }
    }

    private boolean isDeuce() {
        return player1Score >= 3 && pointsDifference() == 0;
    }

    private boolean scoresAreTied() {
        return player1Score < 3 && pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1Score - player2Score;
    }

    private static String advantageTo(String player) {
        return String.format("Advantage %s", player);
    }
}
