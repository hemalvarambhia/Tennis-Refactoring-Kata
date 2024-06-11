import java.util.Map;

public class TennisPlayer {
    public TennisPlayer(String name) {
        this.name = name;
    }

    /**
     * Awards the player a point.
     */
    public void wonPoint() {
        points += 1;
    }

    /**
     * Provides clients with the number of points a player has won.
     * @return points won by the player.
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * Provides clients with the name of the player.
     * @return player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Identifies whether the current player has been the opponent (true for win, false otherwise).
     * For a player to win, they have to have more than three points and a two point lead over the
     * opponent.
     * @param opponent
     * @return true or false.
     */
    public boolean hasBeaten(TennisPlayer opponent) {
        return (getPoints() >=4) && pointsDifference(opponent) >=2;
    }

    /**
     * Identifies whether the player has advantage over their opponent.
     * For a player to have advantage, they have more than three points and a one point
     * lead over their opponent.
     * @param opponent
     * @return
     */
    public boolean hasAdvantageOver(TennisPlayer opponent) {
        return getPoints() >= 4 && pointsDifference(opponent) == 1;
    }

    public String toString() { return name; }

    public int pointsDifference(TennisPlayer opponent) {
        return getPoints() - opponent.getPoints();
    }

    public String runningScore() {
        return POINTS_TO_RUNNING_SCORE.get(points);
    }

    private final String name;

    private Integer points = 0;

    private static final Map<Integer, String> POINTS_TO_RUNNING_SCORE = Map.of(
            0, "Love",
            1, "Fifteen",
            2, "Thirty",
            3, "Forty"
    );
}
