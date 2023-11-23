package Quizkampen;

import java.util.Arrays;

public class quizkampenProtocol {

    private Questions questions = new Questions();

    private Round rounds = new Round();

    private static final int WAITING = 0;
    private static final int ANSWER = 1;
    private int state = WAITING;
    private int currentQuestionIndex = 0;

    private int questionCounter = 0;

    private int roundCounter = 0;
    private int gameCounter = 0;

    private Player player1 = new Player("Player 1", Round.getMaxRoundsPerGame());
    private Player player2 = new Player("Player 2", Round.getMaxRoundsPerGame());


    public String processInput(String theInput) {
        String theOutput = "";


        if (roundCounter > Round.getMaxRoundsPerGame()) {
            return "Spelet är slut";

        }
        Player currentPlayer = getCurrentPlayer();


        if (state == WAITING) {
            if (questionCounter == 0) {
                theOutput = "Rond " + (roundCounter + 1) + ": ";
            }
            theOutput += "Fråga " + (questionCounter + 1) + ": " + questions.getQuestions(currentQuestionIndex) +
                    Arrays.toString(questions.getAlternatives(currentQuestionIndex));
            state = ANSWER;

        } else if (state == ANSWER) {
            if (theInput.equalsIgnoreCase(questions.getCorrectAnswer(currentQuestionIndex))) {
                theOutput = "Du svarade rätt";
                int points = 1;
                currentPlayer.updateRoundPoints(1, points);

            } else {
                theOutput = "Du svarade fel, rätt svar är " + questions.getCorrectAnswer(currentQuestionIndex);
            }
            currentQuestionIndex++;
            questionCounter++;


            if (questionCounter >= Round.getMaxQuestionsPerRound()) {
                questionCounter = 0;
                roundCounter++;
                if (roundCounter >= Round.getMaxRoundsPerGame()) {
                    gameCounter++;
                    theOutput += "\nGame " + gameCounter + " färdigspelat";
                } else {
                    // Skriver ut poängen per runda för varje spelare
                    theOutput += "\nPoäng efter rond " + roundCounter + ":";
                    theOutput += "\nPoäng för " + player1.getName() + ": " + player1.getPointsForRound(roundCounter);
                    theOutput += "\nPoäng för " + player2.getName() + ": " + player2.getPointsForRound(roundCounter);

                    // Uppdaterar totala poängen för varje spelar efter rundan
                    player1.updateTotalPoints(player1.getPointsForRound(roundCounter));
                    player2.updateTotalPoints(player2.getPointsForRound(roundCounter));

                    // Skriver ut totala poängen efter varje runda
                    theOutput += "\nTotal poäng för " + player1.getName() + " efter rond " + roundCounter + ": " + player1.getTotalPoints();
                    theOutput += "\nTotal poäng för " + player2.getName() + " efter rond " + roundCounter + ": " + player2.getTotalPoints();
                }

            }
            if (currentQuestionIndex < questions.getQuestionAmount()) {
                state = WAITING;
            } else {
                theOutput += "\nSpelet är slut.";
            }
        } else {
            theOutput = "Ogiltigt tillstånd";
        }

        return theOutput;
    }

    private Player getCurrentPlayer() {
        return (roundCounter % 2 == 0) ? player1 : player2;
    }
}






