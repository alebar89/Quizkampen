package Quizkampen.Game;


// TODO ska hantera gamestates, rounds och poäng,

import javax.swing.*;

public class Game {

    private JFrame frame;
    private GameStateManager gameStateManager;

    public Game() {

        initializeWindow();
        initializeGameStateManager();

    }

    private void initializeWindow() {
        frame = new JFrame("QuizKampen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
    }

    private void initializeGameStateManager(){
        gameStateManager = new GameStateManager(frame);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Game();
    }

}