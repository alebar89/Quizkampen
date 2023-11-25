package Quizkampen.GamePanels;

import javax.swing.*;
import java.awt.*;

public class ScoreBoardPanel extends JPanel {

    private static final int CIRCLE_DIAMETER = 20;
    private static final int CIRCLE_GAP = 10;
    private static final int ROWS = 6;
    private static final int CIRCLES_PER_ROW = 3;

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String playerOne = "Player 1";
            String playerTwo = "Player 2";
            int playerOnePoints = 3;
            int playerTwoPoints = 5;
            String playerOnePointsToString = String.valueOf(playerOnePoints);
            String playerTwoPointsToString = String.valueOf(playerTwoPoints);

            JFrame frame = new JFrame("Circle Drawing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            JPanel topPanel = new JPanel(new BorderLayout());
            JPanel southPanel = new JPanel(new GridLayout(0, 2));

            JLabel northLabel = new JLabel("Turn: " + playerOne);
            northLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel centralLabel = new JLabel("Points: " + playerOnePointsToString + " - " + playerTwoPointsToString);
            centralLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel southWestLabel = new JLabel(playerOne);
            JLabel southEastLabel = new JLabel(playerTwo);

            topPanel.add(northLabel, BorderLayout.NORTH);
            topPanel.add(centralLabel, BorderLayout.CENTER);
            topPanel.add(southPanel, BorderLayout.SOUTH);
            southPanel.add(southWestLabel);
            southWestLabel.setHorizontalAlignment(SwingConstants.LEFT);
            southPanel.add(southEastLabel);
            southEastLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            ScoreBoardPanel circleDrawing = new ScoreBoardPanel();

            frame.setLayout(new BorderLayout());
            frame.add(topPanel, BorderLayout.NORTH);
            frame.add(circleDrawing, BorderLayout.CENTER);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        });
    }
}
