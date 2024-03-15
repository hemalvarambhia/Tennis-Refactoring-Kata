import java.util.Map;

public class TennisGame2 implements TennisGame
{
    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore(){
        if (scoresAreEqual() && P1point>=3)
            return "Deuce";

        String score = "";
        if (scoresAreEqual())
        {
            score = POINTS_TO_RUNNING_SCORE.get(P1point);
            return score + "-All";
        }
        
        if (P1point > 0 && P2point==0)
        {
            playerOneRunningScore = POINTS_TO_RUNNING_SCORE.get(P1point);
            playerTwoRunningScore = POINTS_TO_RUNNING_SCORE.get(P2point);
            score = playerOneRunningScore + "-" + playerTwoRunningScore;
        }
        if (P2point > 0 && P1point==0)
        {
            if (P2point==1)
                playerTwoRunningScore = POINTS_TO_RUNNING_SCORE.get(P2point);
            if (P2point==2)
                playerTwoRunningScore = POINTS_TO_RUNNING_SCORE.get(P2point);
            if (P2point==3)
                playerTwoRunningScore = "Forty";
            
            playerOneRunningScore = "Love";
            score = playerOneRunningScore + "-" + playerTwoRunningScore;
        }
        
        if (P1point>P2point && P1point < 4)
        {
            if (P1point==2)
                playerOneRunningScore =POINTS_TO_RUNNING_SCORE.get(P1point);
            if (P1point==3)
                playerOneRunningScore ="Forty";
            if (P2point==1)
                playerTwoRunningScore ="Fifteen";
            if (P2point==2)
                playerTwoRunningScore =POINTS_TO_RUNNING_SCORE.get(P2point);;
            score = playerOneRunningScore + "-" + playerTwoRunningScore;
        }
        if (P2point>P1point && P2point < 4)
        {
            if (P2point==2)
                playerTwoRunningScore =POINTS_TO_RUNNING_SCORE.get(P2point);
            if (P2point==3)
                playerTwoRunningScore ="Forty";
            if (P1point==1)
                playerOneRunningScore ="Fifteen";
            if (P1point==2)
                playerOneRunningScore =POINTS_TO_RUNNING_SCORE.get(P1point);
            score = playerOneRunningScore + "-" + playerTwoRunningScore;
        }
        
        if (P1point > P2point && P2point >= 3)
        {
            score = "Advantage player1";
        }
        
        if (P2point > P1point && P1point >= 3)
        {
            score = "Advantage player2";
        }
        
        if (P1point>=4 && P2point>=0 && (P1point-P2point)>=2)
        {
            score = "Win for player1";
        }
        if (P2point>=4 && P1point>=0 && (P2point-P1point)>=2)
        {
            score = "Win for player2";
        }
        return score;
    }

    private boolean scoresAreEqual() {
        return P1point == P2point;
    }

    public void wonPoint(String player) {
        if (player == "player1")
            P1point++;
        else
            P2point++;
    }

    private int P1point = 0;
    private int P2point = 0;

    private String playerOneRunningScore = "";
    private String playerTwoRunningScore = "";
    private String player1Name;
    private String player2Name;

    private static final Map<Integer, String> POINTS_TO_RUNNING_SCORE =
        Map.of(
            0, "Love",
                1, "Fifteen",
                2, "Thirty",
                3, "Forty"
        );
}