package Quizkampen.GamePanels;

import Quizkampen.Game.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class ScoreBoardPanel extends JPanel {

    private static final int CIRCLE_DIAMETER = 20;
    private static final int CIRCLE_GAP = 10;
    private static final int ROWS = 6;
    private static final int CIRCLES_PER_ROW = 3;

    private JLabel northLabel;
    private JLabel centralLabel;
    private JLabel southWestLabel;
    private JLabel southEastLabel;


    public ScoreBoardPanel(GameStateManager gsm) {

        northLabel = new JLabel("Turn: Player 1");
        centralLabel = new JLabel("Points: 0 - 0");
        southWestLabel = new JLabel("Player 1");
        southEastLabel = new JLabel("Player 2");

        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel southPanel = new JPanel(new GridLayout(0, 2));

        // Configure labels
        northLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centralLabel.setHorizontalAlignment(SwingConstants.CENTER);
        southWestLabel.setHorizontalAlignment(SwingConstants.LEFT);
        southEastLabel.setHorizontalAlignment(SwingConstants.RIGHT);


        topPanel.add(northLabel, BorderLayout.NORTH);
        topPanel.add(centralLabel, BorderLayout.CENTER);
        southPanel.add(southWestLabel);
        southPanel.add(southEastLabel);


        add(topPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int startXLeft = 10;
        int startXRight = getWidth() - 30;

        int startY = 10;

        for (int i = 0; i < ROWS; i++) {
            // Draw circles on the left side
            for (int j = 0; j < CIRCLES_PER_ROW; j++) {
                int x = startXLeft + j * (CIRCLE_DIAMETER + CIRCLE_GAP);
                int y = startY + i * (CIRCLE_DIAMETER + CIRCLE_GAP);

                g2d.setColor(Color.GRAY);
                g2d.fillOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
            }

            // Draw circles on the right side
            for (int j = 0; j < CIRCLES_PER_ROW; j++) {
                int x = startXRight - j * (CIRCLE_DIAMETER + CIRCLE_GAP);
                int y = startY + i * (CIRCLE_DIAMETER + CIRCLE_GAP);

                g2d.setColor(Color.GRAY);
                g2d.fillOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
            }

            // Draw the row number in the middle of the row
            int rowNumber = i + 1;
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(Integer.toString(rowNumber));
            int textX = getWidth() / 2 - textWidth / 2; // Center the text horizontally
            int textY = startY + i * (CIRCLE_DIAMETER + CIRCLE_GAP) + CIRCLE_DIAMETER / 2 + fm.getAscent() / 2; // Center the text vertically

            g2d.setColor(Color.BLACK);
            g2d.drawString(Integer.toString(rowNumber), textX, textY);
        }
    }

}
