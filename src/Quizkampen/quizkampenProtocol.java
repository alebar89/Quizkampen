package Quizkampen;

import java.util.Arrays;

public class quizkampenProtocol {

    private static final int WAITING = 0;
    private static final int ANSWER = 1;

    private int state = WAITING;

    private int currentQuestion = 0;
    private int currentAlternatives = 0;
    private int currentAnswers = 0;

    private String[] Question = {"Vilka vann herrarnas fotbolls-VM 2022?", "Vilket lag vann SM-guld i ishockey för herrar 2007?"};
    private String[] Answers = {"Argentina", "Modo"};
    private String[][] Alternatives = {
            {"Spanien", "Argentina", "Tyskland", "Italien"},
            {"Djurgården", "Malmö", "Luleå", "Modo"}
    };

    public String processInput(String theInput) {
        String theOutput;

        if (state == WAITING) {
            theOutput = Question[currentQuestion] + Arrays.toString(Alternatives[currentAlternatives]);
            state = ANSWER;
        } else if (state == ANSWER) {
            if (theInput.equalsIgnoreCase(Answers[currentAnswers])) {
                theOutput = "Du svarade rätt";

                // Gå till nästa fråga om det finns fler frågor
                if (currentQuestion < Question.length - 1) {
                    currentQuestion++;
                    currentAlternatives++;
                    currentAnswers++;
                    state = WAITING;
                } else {
                    theOutput += "\nQuizet är slut.";
                    state = WAITING;
                }
            } else {
                theOutput = "Du svarade fel";
                state = WAITING;
            }
        } else {
            theOutput = "Ogiltigt tillstånd";
        }

        return theOutput;
    }
}