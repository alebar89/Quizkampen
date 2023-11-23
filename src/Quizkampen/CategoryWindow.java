package Quizkampen;

import javax.swing.*;
import java.awt.*;

public class CategoryWindow {
    public CategoryWindow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        JPanel centralPanel = new JPanel(new GridBagLayout());
        JLabel categoryHeading = new JLabel("Välj kategori/choose category");

        Font originalFont = categoryHeading.getFont();
        Font largerFont = new Font(originalFont.getName(), Font.PLAIN, originalFont.getSize() + 10);
        categoryHeading.setFont(largerFont);

        categoryHeading.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JButton firstOption = new JButton("Kategori/category 1");
        JButton secondOption = new JButton("Kategori/category 2");
        JButton thirdOption = new JButton("Kategori/category 3");

        Dimension smallerButtonSize = new Dimension(250, 50);

        firstOption.setPreferredSize(smallerButtonSize);
        firstOption.setMaximumSize(smallerButtonSize);
        secondOption.setPreferredSize(smallerButtonSize);
        secondOption.setMaximumSize(smallerButtonSize);
        thirdOption.setPreferredSize(smallerButtonSize);
        thirdOption.setMaximumSize(smallerButtonSize);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        centralPanel.add(firstOption, gbc);
        gbc.gridy++;
        centralPanel.add(secondOption, gbc);
        gbc.gridy++;
        centralPanel.add(thirdOption, gbc);

        frame.add(mainPanel);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centralPanel, BorderLayout.CENTER);
        topPanel.add(categoryHeading);
        categoryHeading.setHorizontalAlignment(SwingConstants.CENTER);
        categoryHeading.setVerticalAlignment(SwingConstants.CENTER);

        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        CategoryWindow cw = new CategoryWindow();
    }
}
