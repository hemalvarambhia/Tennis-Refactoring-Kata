
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
        if (player1Points <= 3 && player2Points <= 3 && (player1Points + player2Points < 6)) {
            if (scoresAreEqual())
                return String.format("%s-All", toRunningScore(player1Points));
        } else {
            if (scoresAreEqual())
                return "Deuce";
            String leadingPlayer = player1Points > player2Points ? player1Name : player2Name;
            return ((player1Points - player2Points)*(player1Points - player2Points) == 1) ? "Advantage " + leadingPlayer : "Win for " + leadingPlayer;
        }

        return String.format("%s-%s", toRunningScore(player1Points), toRunningScore(player2Points));
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
        String[] runningScores = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
        return runningScores[points];
    }

}
