package Quizkampen.GamePanels;

import Quizkampen.Game.GameStateManager;
import Quizkampen.Model.Questions;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private int currentQuestion = 0;
    private JPanel textArea;
    private JLabel Question;
    private JButton[][] buttons;
    private Questions questions;
    private StringAndCirclesPanel stringAndCirclesPanel;

    public GamePanel(GameStateManager gsm) {
        setLayout(new BorderLayout());
        stringAndCirclesPanel = new StringAndCirclesPanel();
        add(BorderLayout.NORTH, stringAndCirclesPanel);

        Question = new JLabel();
        textArea = new JPanel();
        textArea.add(Question);
        textArea.setPreferredSize(new Dimension(400, 200));
        textArea.setBorder(BorderFactory.createMatteBorder(15, 15, 15, 15, Color.LIGHT_GRAY));
        add(BorderLayout.CENTER, textArea);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setPreferredSize(new Dimension(200, 130));
        buttons = new JButton[2][2];
        Border border = BorderFactory.createMatteBorder(10, 15, 10, 15, Color.LIGHT_GRAY);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBorder(border);
                buttons[i][j].addActionListener(new OptionButtonListener());
                buttonPanel.add(buttons[i][j]);
            }
        }

        add(BorderLayout.SOUTH, buttonPanel);


        questions = new Questions();

        updateQuestionAndOptions();


    }

    private void updateQuestionAndOptions() {
        if (currentQuestion < questions.getQuestionAmount()) {
            Question.setText(questions.getQuestions(currentQuestion));

            for (int i = 0; i < 4; i++) {
                buttons[i / 2][i % 2].setText(questions.getAlternatives(currentQuestion)[i]);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Quizet är slut.");
        }
    }

    private class OptionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            String selectedAnswer = clickedButton.getText();

            if (currentQuestion < questions.getQuestionAmount()) {

                String correctAnswer = questions.getCorrectAnswer(currentQuestion);

                if (selectedAnswer.equalsIgnoreCase(correctAnswer)) {
                    JOptionPane.showMessageDialog(null, "Du svarade rätt");
                    stringAndCirclesPanel.changeColor(Color.GREEN); // Ändra färg till grön
                } else {
                    JOptionPane.showMessageDialog(null, "Du svarade fel");
                    stringAndCirclesPanel.changeColor(Color.RED); // Ändra färg till röd
                }

                if (currentQuestion < questions.getQuestionAmount() - 1) {
                    currentQuestion++;
                    updateQuestionAndOptions();
                } else {
                    JOptionPane.showMessageDialog(null, "Quizet är slut.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Felaktig data eller index");
            }
        }
    }

}