public class Player {
    private final String name;
    private Integer points = 0;
    public Player(String name) {
        this.name = name;
    }

    public void wonPoint() {
        points += 1;
    }

    public String getName() {
        return name;
    }
}
