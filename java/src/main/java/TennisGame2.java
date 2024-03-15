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
            P1res = POINTS_TO_RUNNING_SCORE.get(P1point);
            P2res = POINTS_TO_RUNNING_SCORE.get(P2point);
            score = P1res + "-" + P2res;
        }
        if (P2point > 0 && P1point==0)
        {
            if (P2point==1)
                P2res = POINTS_TO_RUNNING_SCORE.get(P2point);
            if (P2point==2)
                P2res = "Thirty";
            if (P2point==3)
                P2res = "Forty";
            
            P1res = "Love";
            score = P1res + "-" + P2res;
        }
        
        if (P1point>P2point && P1point < 4)
        {
            if (P1point==2)
                P1res="Thirty";
            if (P1point==3)
                P1res="Forty";
            if (P2point==1)
                P2res="Fifteen";
            if (P2point==2)
                P2res="Thirty";
            score = P1res + "-" + P2res;
        }
        if (P2point>P1point && P2point < 4)
        {
            if (P2point==2)
                P2res="Thirty";
            if (P2point==3)
                P2res="Forty";
            if (P1point==1)
                P1res="Fifteen";
            if (P1point==2)
                P1res="Thirty";
            score = P1res + "-" + P2res;
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

    private String P1res = "";
    private String P2res = "";
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