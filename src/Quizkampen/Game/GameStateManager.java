package Quizkampen.Game;

import Quizkampen.GamePanels.*;
import Quizkampen.Model.Questions;

import javax.swing.*;
import java.awt.*;

public class GameStateManager {

    private CardLayout cardLayout;  // använder cardlayout för att skifta mellan de olika rutorna.
    // det hela går ut på att alla paneler ligger på varandra men man väljer ut den panel som ska visas vid respektive stadie.
    private JPanel cardPanel;



    public static final int LOGIN_STATE = 0;
    public static final int LOBBY_STATE = 1;
    public static final int CATEGORY_STATE = 2;
    public static final int GAME_STATE = 3;
    public static final int RESULT_STATE = 4;
    public static int CURRENT_STATE = LOGIN_STATE;

    private String[] currentQuestions;

    public String[] getCurrentQuestions() {
        return currentQuestions;
    }

    public void setCurrentQuestions(String[] currentQuestions) {
        this.currentQuestions = currentQuestions;
    }

    public String[] getCurrentAnswers() {
        return currentAnswers;
    }

    public void setCurrentAnswers(String[] currentAnswers) {
        this.currentAnswers = currentAnswers;
    }

    public String[][] getCurrentAlternatives() {
        return currentAlternatives;
    }

    public void setCurrentAlternatives(String[][] currentAlternatives) {
        this.currentAlternatives = currentAlternatives;
    }

    private String[] currentAnswers;
    private String[][] currentAlternatives;

    public GameStateManager(JFrame frame) {
        cardPanel = new JPanel(new CardLayout());
        cardLayout = (CardLayout)cardPanel.getLayout(); // kastar layouten till CardLayout så vi kan använda metoderna för det objektet
        frame.add(cardPanel);

        intitializeStates();
    }

    // initierar de olika korten(panelerna) som ska displayas för de olika stadierna i spelet.
    // lägg till era paneler här så kan vi fixa logiken till att hoppa till varje state.
    // obs nedan så initieras panelerna direkt när spelet körs och kan därmed inte använda någon
    // funktion osm väntar på svar
    private void intitializeStates() {
        cardPanel.add(new LoginPanel(this), String.valueOf(LOGIN_STATE));
        cardPanel.add(new CategoryWindowPanel(this), String.valueOf(CATEGORY_STATE));
        cardPanel.add(new LobbyPanel(this), String.valueOf(LOBBY_STATE));
    }


    public void initializeGamePanel() {
        GamePanel gamePanel = new GamePanel(this);
        cardPanel.add(gamePanel, String.valueOf(GAME_STATE));
        gamePanel.updateQuestionAndOptions();
    }

    public void loadQuestionsForCategory(String category) {
        switch (category) {
            case Questions.SPORT_CATEGORY:
                currentQuestions = Questions.getQuestionSport();
                currentAnswers = Questions.getAnswersSport();
                currentAlternatives = Questions.getAlternativesSport();
                break;
            case Questions.MOVIES_CATEGORY:
                currentQuestions = Questions.getQuestionMovies();
                currentAnswers = Questions.getAnswersMovies();
                currentAlternatives = Questions.getAlternativesMovies();
                break;
            case Questions.GEOGRAPHY_CATEGORY:
                currentQuestions = Questions.getQuestionGeography();
                currentAnswers = Questions.getAnswersGeography();
                currentAlternatives = Questions.getAlternativesGeography();
                break;
            case Questions.ANIMALS_CATEGORY:
                currentQuestions = Questions.getQuestionAnimals();
                currentAnswers = Questions.getAnswersAnimals();
                currentAlternatives = Questions.getAlternativesAnimals();
                break;
            default:
                throw new IllegalStateException("Okänd Kategori: " + category);
        }
        initializeGamePanel();
        setState(GAME_STATE);
    }


    // metod för att byta stadieruta.
    public void setState(int state) {
        String stateString = String.valueOf(state);
        cardLayout.show(cardPanel, stateString);
        CURRENT_STATE = state;
    }



}