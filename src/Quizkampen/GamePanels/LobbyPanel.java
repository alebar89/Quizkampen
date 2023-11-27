package Quizkampen.GamePanels;

import Quizkampen.Game.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LobbyPanel extends JPanel {

    private static final int CIRCLE_DIAMETER = 20;
    private static final int CIRCLE_GAP = 10;
    private static final int ROWS = 6;
    private static final int CIRCLES_PER_ROW = 3;

    private JLabel northLabel;
    private JLabel centralLabel;
    private JLabel northWestLabel;
    private JLabel northEastLabel;
    private JPanel drawingPanel;

    public LobbyPanel(GameStateManager gsm) {
        setLayout(new BorderLayout());

        // Initialize labels and buttons
        northLabel = new JLabel("Turn: Player 1", SwingConstants.CENTER);
        centralLabel = new JLabel("Points: 0 - 0", SwingConstants.CENTER);
        northWestLabel = new JLabel("Player 1", SwingConstants.LEFT);
        northEastLabel = new JLabel("Player 2", SwingConstants.RIGHT);
        JButton anslut = new JButton("Gå med i spel");


        // Top corners panel
        JPanel topCorners = new JPanel(new BorderLayout());
        topCorners.add(northWestLabel, BorderLayout.WEST);
        topCorners.add(northEastLabel, BorderLayout.EAST);

        // Center panel for the turn and points labels
        JPanel centerLabelsPanel = new JPanel(new GridLayout(2, 1));
        centerLabelsPanel.add(northLabel);
        centerLabelsPanel.add(centralLabel);

        // Combine top corners and center labels panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(topCorners, BorderLayout.NORTH);
        topPanel.add(centerLabelsPanel, BorderLayout.CENTER);

        // Button panel at the bottom
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(anslut);

        // Action listener for the button
        anslut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gsm.setState(GameStateManager.CATEGORY_STATE);
            }
        });

        // Drawing panel for custom graphics
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawCirclesAndNumbers(g);
            }
        };
        drawingPanel.setPreferredSize(new Dimension(getWidth(), (CIRCLE_DIAMETER + CIRCLE_GAP) * ROWS + 20));

        // Add all panels to the main panel
        add(topPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void drawCirclesAndNumbers(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int startXLeft = 10;
        int startXRight = getWidth() - 30;
        int startY = 30;

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
            int textX = getWidth() / 2 - textWidth / 2; // Center text horizontally
            int textY = startY + i * (CIRCLE_DIAMETER + CIRCLE_GAP) + CIRCLE_DIAMETER / 2 + fm.getAscent() / 2; // Center text vertically
            g2d.setColor(Color.BLACK);
            g2d.drawString(Integer.toString(rowNumber), textX, textY);
        }
    }
}
