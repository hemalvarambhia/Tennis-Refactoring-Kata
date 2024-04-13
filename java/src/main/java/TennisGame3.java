import java.util.Map;

public class TennisGame3 implements TennisGame {
    
    private int player2Points;
    private int player1Points;
    private final String player1Name;
    private final String player2Name;

    public TennisGame3(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        if(player1Points >= 3 && scoresAreEqual()) {
            return "Deuce";
        }

        if (scoresAreEqual())
            return String.format("%s-All", toRunningScore(player1Points));

        int pointsDifference = Math.abs(pointsDifference());
        if (player1Points > 3 || player2Points > 3) {
            String leadingPlayer = getLeadingPlayer();
            if ((player1Points > 3 || player2Points > 3) && pointsDifference == 1) {
                return advantage(leadingPlayer);
            }

            if(pointsDifference >= 2) {
                return win(leadingPlayer);
            }
        }

        return String.format("%s-%s", toRunningScore(player1Points), toRunningScore(player2Points));
    }

    private static String win(String leadingPlayer) {
        return String.format("Win for %s", leadingPlayer);
    }

    private static String advantage(String leadingPlayer) {
        return String.format("Advantage %s", leadingPlayer);
    }

    private int pointsDifference() {
        return player1Points - player2Points;
    }

    private String getLeadingPlayer() {
        return player1Points > player2Points ? player1Name : player2Name;
    }

    private boolean scoresAreEqual() {
        return player1Points == player2Points;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            this.player1Points += 1;
        else
            this.player2Points += 1;
        
    }

    private String toRunningScore(Integer points) {
        return POINTS_TO_RUNNING_SCORE.get(points);
    }

    private static final Map<Integer, String> POINTS_TO_RUNNING_SCORE = Map.of(
            0, "Love",
            1, "Fifteen",
            2, "Thirty",
            3, "Forty"
    );

}
