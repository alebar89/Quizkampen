package Quizkampen.Game;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Round {

    //TODO; ska föreställa en enskild runda,
    // behöver hämta in frågorna (Questions.java objects kanske)
    // håller koll på aktuell fråga som spelas.
    // delar ut nästa fråga samt kollar om den ska köra nästa rond när två frågor har delats ut

    private static int MAX_QUESTIONS_PER_ROUND;
    private static int MAX_ROUNDS_PER_GAME;

    static {
        loadProperties();
    }

    public Round () {
        //konstruktor logik om det skulle behövas
    }

    public static void loadProperties() {
        Properties prop = new Properties();
        try (FileInputStream is = new FileInputStream("src/game-config.properties")) {
            prop.load(is);
            MAX_QUESTIONS_PER_ROUND = Integer.parseInt(prop.getProperty("questions.per.round"));
            MAX_ROUNDS_PER_GAME = Integer.parseInt(prop.getProperty("rounds.per.game"));
        } catch (IOException e) {
            e.printStackTrace();

            // default värde om fel uppstår eller inget värde har angetts.
            MAX_QUESTIONS_PER_ROUND = 2;
            MAX_ROUNDS_PER_GAME = 2;

        }
    }



    public static int getMaxQuestionsPerRound(){
        return MAX_QUESTIONS_PER_ROUND;
    }
    public static int getMaxRoundsPerGame() {
        return MAX_ROUNDS_PER_GAME;
    }

}
