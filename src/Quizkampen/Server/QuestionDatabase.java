package Quizkampen.Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Quizkampen.Config.Question;

public class QuestionDatabase {

    private final ArrayList<Question> DBquestions = new ArrayList<>();

    /**
     * By using a BufferedReader we read each line, create a questions and add them into a arraylist
     */


    public QuestionDatabase() {

        String gameQuestionsData;
        String gameQuestionsCategory;

        String questionList = "source/QuestionsList.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(questionList))){

            while((gameQuestionsCategory = reader.readLine()) != null){
                gameQuestionsData = reader.readLine();
                List<String>answers = new ArrayList<>();
                String correctAnswer = reader.readLine();
                String answerTwo = reader.readLine();
                String answerThree = reader.readLine();
                String answerFour = reader.readLine();
                answers.add(correctAnswer);
                answers.add(answerTwo);
                answers.add(answerThree);
                answers.add(answerFour);
                Collections.shuffle(answers);


                DBquestions.add(new Question(gameQuestionsCategory, gameQuestionsData ,correctAnswer, answers));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Question> getDBquestions() {
        return DBquestions;
    }
}