package Quizkampen.GamePanels;

import Quizkampen.Game.GameStateManager;
import Quizkampen.Model.Questions;

import javax.swing.*;
import java.awt.*;

public class CategoryWindowPanel extends JPanel {
    public CategoryWindowPanel(GameStateManager gsm) {
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        JPanel centralPanel = new JPanel(new GridBagLayout());
        JLabel categoryHeading = new JLabel("Välj kategori/choose category");

        Font originalFont = categoryHeading.getFont();
        Font largerFont = new Font(originalFont.getName(), Font.PLAIN, originalFont.getSize() + 10);
        categoryHeading.setFont(largerFont);
        categoryHeading.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JButton firstOption = new JButton("Sport");
        JButton secondOption = new JButton("Film");
        JButton thirdOption = new JButton("Geography");
        JButton fourthOption = new JButton("Animals");

        Dimension smallerButtonSize = new Dimension(250, 50);

        firstOption.setPreferredSize(smallerButtonSize);
        firstOption.setMaximumSize(smallerButtonSize);
        secondOption.setPreferredSize(smallerButtonSize);
        secondOption.setMaximumSize(smallerButtonSize);
        thirdOption.setPreferredSize(smallerButtonSize);
        thirdOption.setMaximumSize(smallerButtonSize);
        fourthOption.setPreferredSize(smallerButtonSize);
        fourthOption.setMaximumSize(smallerButtonSize);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        centralPanel.add(firstOption, gbc);
        gbc.gridy++;
        centralPanel.add(secondOption, gbc);
        gbc.gridy++;
        centralPanel.add(thirdOption, gbc);
        gbc.gridy++;
        centralPanel.add(fourthOption, gbc);

        add(topPanel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);
        topPanel.add(categoryHeading);
        categoryHeading.setHorizontalAlignment(SwingConstants.CENTER);
        categoryHeading.setVerticalAlignment(SwingConstants.CENTER);

        firstOption.addActionListener(e -> gsm.loadQuestionsForCategory(Questions.SPORT_CATEGORY));
        secondOption.addActionListener(e -> gsm.loadQuestionsForCategory(Questions.MOVIES_CATEGORY));
        thirdOption.addActionListener(e -> gsm.loadQuestionsForCategory(Questions.GEOGRAPHY_CATEGORY));
        fourthOption.addActionListener(e -> gsm.loadQuestionsForCategory(Questions.ANIMALS_CATEGORY));




    }
}

