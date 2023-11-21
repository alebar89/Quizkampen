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


    public String processInput(String theInput) {
        String theOutput = "";

        if (roundCounter > Round.getMaxRoundsPerGame()) {
            return "Spelet är slut";

        }

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
}







