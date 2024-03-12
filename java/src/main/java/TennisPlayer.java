public class TennisPlayer {
    private final String name;
    private Integer points = 0;
    public TennisPlayer(String name) {
        this.name = name;
    }

    public void wonPoint() {
        points += 1;
    }

    public Integer getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public int pointsDifference(TennisPlayer opponent) {
        return getPoints() - opponent.getPoints();
    }
}
