package Quizkampen.GamePanels;

import Quizkampen.Game.GameStateManager;

import javax.swing.*;
public class LoginPanel extends JPanel {

    public LoginPanel(GameStateManager gsm) {

        setLayout(null);

        JLabel label = new JLabel("USERNAME:");
        label.setBounds(50, 165, 100,20);
        add(label);

        JTextField userText = new JTextField(20);
        userText.setBounds(120,165,150, 25);
        add(userText);

        JButton buttonLogin = new JButton("Login");
        buttonLogin.setBounds(50, 215, 275,25);
        add(buttonLogin);

        buttonLogin.addActionListener(e -> gsm.setState(GameStateManager.LOBBY_STATE));
        buttonLogin.addActionListener(e -> gsm.sendPlayerName(userText.getText()));

    }
}