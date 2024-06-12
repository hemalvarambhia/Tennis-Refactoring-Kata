import java.util.Map;

public class TennisGame6 implements TennisGame {
    private final String player1Name;
    private final String player2Name;
    private int player1Score;
    private int player2Score;

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
        Map<Integer, String> runningScoreMap = Map.of(
                0, "Love",
                1, "Fifteen",
                2, "Thirty",
                3, "Forty"
        );

        if (pointsDifference() == 0)
        {
            // tie score

            return switch (player1Score) {
                case 0, 1, 2 -> {
                    String runningScore = runningScoreMap.get(player1Score);
                    yield String.format("%s-All", runningScore);
                }
                default -> "Deuce";
            };
        }
        else if (player1Score > 3 || player2Score > 3)
        {
            if (pointsDifference() == 1) {
                return String.format("Advantage %s", player1Name);
            } else if (pointsDifference() == -1) {
                return String.format("Advantage %s", player2Name);
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

    private int pointsDifference() {
        return player1Score - player2Score;
    }
}
