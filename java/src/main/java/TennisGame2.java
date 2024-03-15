import java.util.Map;

public class TennisGame2 implements TennisGame
{
    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore(){
        if (scoresAreEqual() && player1Point >=3)
            return "Deuce";

        String score = "";
        if (scoresAreEqual())
        {
            score = POINTS_TO_RUNNING_SCORE.get(player1Point);
            return score + "-All";
        }
        
        if (player1Point > 0 && player2Point ==0)
        {
            player1RunningScore = POINTS_TO_RUNNING_SCORE.get(player1Point);
            player2RunningScore = POINTS_TO_RUNNING_SCORE.get(player2Point);
            score = player1RunningScore + "-" + player2RunningScore;
        }
        if (player2Point > 0 && player1Point ==0)
        {
            player2RunningScore = POINTS_TO_RUNNING_SCORE.get(player2Point);
            player1RunningScore = POINTS_TO_RUNNING_SCORE.get(player1Point);
            score = player1RunningScore + "-" + player2RunningScore;
        }
        
        if (player1Point > player2Point && player1Point < 4)
        {
            if (player1Point ==2)
                player1RunningScore =POINTS_TO_RUNNING_SCORE.get(player1Point);
            if (player1Point ==3)
                player1RunningScore = POINTS_TO_RUNNING_SCORE.get(player1Point);
            if (player2Point ==1)
                player2RunningScore ="Fifteen";
            if (player2Point ==2)
                player2RunningScore =POINTS_TO_RUNNING_SCORE.get(player2Point);;
            score = player1RunningScore + "-" + player2RunningScore;
        }
        if (player2Point > player1Point && player2Point < 4)
        {
            if (player2Point ==2)
                player2RunningScore =POINTS_TO_RUNNING_SCORE.get(player2Point);
            if (player2Point ==3)
                player2RunningScore =POINTS_TO_RUNNING_SCORE.get(player2Point);
            if (player1Point ==1)
                player1RunningScore ="Fifteen";
            if (player1Point ==2)
                player1RunningScore =POINTS_TO_RUNNING_SCORE.get(player1Point);
            score = player1RunningScore + "-" + player2RunningScore;
        }
        
        if (player1Point > player2Point && player2Point >= 3)
        {
            score = "Advantage player1";
        }
        
        if (player2Point > player1Point && player1Point >= 3)
        {
            score = "Advantage player2";
        }
        
        if (player1Point >=4 && player2Point >=0 && (player1Point - player2Point)>=2)
        {
            score = "Win for player1";
        }
        if (player2Point >=4 && player1Point >=0 && (player2Point - player1Point)>=2)
        {
            score = "Win for player2";
        }
        return score;
    }

    private boolean scoresAreEqual() {
        return player1Point == player2Point;
    }

    public void wonPoint(String player) {
        if (player == "player1")
            player1Point++;
        else
            player2Point++;
    }

    private int player1Point = 0;
    private int player2Point = 0;

    private String player1RunningScore = "";
    private String player2RunningScore = "";
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