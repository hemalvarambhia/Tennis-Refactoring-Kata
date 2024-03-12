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

    public boolean hasBeaten(TennisPlayer opponent) {
        return (getPoints() >=4) && pointsDifference(opponent) >=2;
    }

    public boolean hasAdvantageOver(TennisPlayer opponent) {
        return getPoints() >= 4 && pointsDifference(opponent) == 1;
    }

    public int pointsDifference(TennisPlayer opponent) {
        return getPoints() - opponent.getPoints();
    }
}
