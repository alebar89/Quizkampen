package Quizkampen;

import java.util.ArrayList;
import java.util.List;

public class Round {

    //TODO; ska föreställa en enskild runda,
    // behöver hämta in frågorna (Questions.java objects kanske)
    // håller koll på aktuell fråga som spelas.
    // delar ut nästa fråga samt kollar om den ska köra nästa rond när två frågor har delats ut

    private static final int MAX_QUESTIONS_PER_ROUND = 2;
    private static final int MAX_ROUNDS_PER_GAME = 2;

    public Round () {


    }



    public static int getMaxQuestionsPerRound(){
        return MAX_QUESTIONS_PER_ROUND;
    }
    public static int getMaxRoundsPerGame() {
        return MAX_ROUNDS_PER_GAME;
    }




}
