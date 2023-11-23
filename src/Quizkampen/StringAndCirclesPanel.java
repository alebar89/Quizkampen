package Quizkampen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class StringAndCirclesPanel extends JPanel {

    private final String playerOne = "Player 1";
    private final String playerTwo = "Player 2";
    private final Color[] pointCircles = {Color.GRAY, Color.GRAY, Color.GRAY};

    public StringAndCirclesPanel() {
        setPreferredSize(new Dimension(400, 150));

        JButton redButton = new JButton("Red");
        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColor(Color.RED);
            }
        });
        add(redButton);

        JButton greenButton = new JButton("Green");
        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColor(Color.GREEN);
            }
        });
        add(greenButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetColor();
            }
        });
        add(resetButton);
    }

    private void changeColor(Color newColor) {
        for (int i = 0; i < pointCircles.length; i++) {
            if (pointCircles[i] == Color.GRAY) {
                pointCircles[i] = newColor;
                repaint();
                break;
            }
        }
    }

    private void resetColor() {
        for (int i = 0; i < pointCircles.length; i++) {
            pointCircles[i] = Color.GRAY;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        FontMetrics leftFontMetrics = g2d.getFontMetrics();
        int leftStringWidth = leftFontMetrics.stringWidth(playerOne);
        g2d.drawString(playerOne, 5, leftFontMetrics.getHeight());

        FontMetrics rightFontMetrics = g2d.getFontMetrics();
        int rightStringWidth = rightFontMetrics.stringWidth(playerTwo);
        int panelWidth = getWidth();
        g2d.drawString(playerTwo, panelWidth - rightStringWidth - 5, rightFontMetrics.getHeight());

        int circleDiameter = 20;
        int circleSpacing = 10;
        int circlesY = 50;

        int leftStringX = circleSpacing;

        for (int i = 0; i < pointCircles.length; i++) {
            g2d.setColor(pointCircles[i]);
            g2d.fill(new Ellipse2D.Double(leftStringX + i * (circleDiameter + circleSpacing), circlesY, circleDiameter, circleDiameter));
        }

        int rightStringX = panelWidth - ((circleDiameter * 3) + (circleSpacing * 3));

        for (int i = 0; i < 3; i++) {
            g2d.setColor(Color.GRAY);
            g2d.fill(new Ellipse2D.Double(rightStringX + i * (circleDiameter + circleSpacing), circlesY, circleDiameter, circleDiameter));
        }
    }
}