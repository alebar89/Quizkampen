package Quizkampen;

public class Player {
    private String name;
    private int totalPoints;
    private int[] roundPoints; // För att lagra poängen per rond

    public Player(String name, int maxRounds) {
        this.name = name;
        this.totalPoints = 0;
        this.roundPoints = new int[maxRounds];
    }

    public String getName() {
        return name;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    // Uppdaterar totala poängen för spelaren
    public void updateTotalPoints(int points) {
        totalPoints += points;
    }

    // Update points for the current round
    public void updateRoundPoints(int round, int points) {
        roundPoints[round] = points;
    }

    // Få poäng för specifik runda
    public int getPointsForRound(int round) {
        return roundPoints[round];
    }
}
