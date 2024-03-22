
public class TennisGame3 implements TennisGame {
    
    private int player2Points;
    private int player1Points;
    private String p1N;
    private String p2N;

    public TennisGame3(String p1N, String p2N) {
        this.p1N = p1N;
        this.p2N = p2N;
    }

    public String getScore() {
        String s;
        if (player1Points < 4 && player2Points < 4 && !(player1Points + player2Points == 6)) {
            String[] p = new String[]{"Love", "Fifteen", "Thirty", "Forty"}; 
            s = p[player1Points];
            return (player1Points == player2Points) ? s + "-All" : s + "-" + p[player2Points];
        } else {
            if (player1Points == player2Points)
                return "Deuce";
            s = player1Points > player2Points ? p1N : p2N;
            return ((player1Points - player2Points)*(player1Points - player2Points) == 1) ? "Advantage " + s : "Win for " + s;
        }
    }
    
    public void wonPoint(String playerName) {
        if (playerName == "player1")
            this.player1Points += 1;
        else
            this.player2Points += 1;
        
    }

}
