public class TennisGame5 implements TennisGame {
    private final TennisPlayer player1;
    private final TennisPlayer player2;

    public TennisGame5(String player1Name, String player2Name) {
        player1 = new TennisPlayer(player1Name);
        player2 = new TennisPlayer(player2Name);
    }

    @Override
    public void wonPoint(String playerName) {
        if (playerName.equals(player1.getName())) {
            player1.wonPoint();
        } else if (playerName.equals(player2.getName())) {
            player2.wonPoint();
        } else
            throw new IllegalArgumentException("Invalid player name.");
    }

    @Override
    public String getScore() {
        if(player1.getPoints() > 16 || player2.getPoints() > 16) throw new IllegalArgumentException("Invalid score.");
        if(isDeuce()) return "Deuce";
        if(player1.hasAdvantageOver(player2)) return advantage(player1);
        if(player1.hasBeaten(player2)) return won(player1);
        if(player2.hasAdvantageOver(player1)) return advantage(player2);
        if(player2.hasBeaten(player1)) return won(player2);

        if(scoresAreTied()) return String.format("%s-All", player1.runningScore());
        return String.format("%s-%s", player1.runningScore(), player2.runningScore());
    }

    private boolean scoresAreTied() {
        return player1.getPoints() < 3 && pointsDifference() == 0;
    }

    private boolean isDeuce() {
        return player1.getPoints() >= 3 && pointsDifference() == 0;
    }

    private int pointsDifference() {
        return player1.pointsDifference(player2);
    }

    private static String advantage(TennisPlayer player) {
        return  String.format("Advantage %s", player);
    }

    private static String won(TennisPlayer player) {
        return String.format("Win for %s", player);
    }
}
