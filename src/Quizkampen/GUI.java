package Quizkampen;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

public class GUI extends JPanel {

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());

        StringAndCirclesPanel northPanel = new StringAndCirclesPanel();

        mainPanel.add(northPanel);
        northPanel.add(new GUI());
        JTextArea textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(400, 200));
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setPreferredSize(new Dimension(200, 130));
        JButton[][] buttons = new JButton[2][2];
        Border border = BorderFactory.createMatteBorder(10, 15, 10, 15, Color.LIGHT_GRAY);
        Border border2 = BorderFactory.createMatteBorder(15, 15, 15, 15, Color.LIGHT_GRAY);

        frame.add(mainPanel);
        mainPanel.add(BorderLayout.NORTH, northPanel);
        mainPanel.add(BorderLayout.CENTER, textArea);
        mainPanel.add(BorderLayout.SOUTH, buttonPanel);

        textArea.setBorder(border2);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBorder(border);
                buttonPanel.add(buttons[i][j]);
            }
        }

        frame.pack();
        frame.setVisible(true);
    }
}