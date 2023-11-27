/*package Quizkampen.GamePanels;

import Quizkampen.Game.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.SOUTH;

public class LobbyPanel extends JPanel {

    public LobbyPanel(GameStateManager gsm) {

        JButton skapaQuiz = new JButton("Skapa Quiz"); // välja kategori
        JButton anslut = new JButton("Gå med i spel"); // gå med ett spel

        skapaQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gsm.setState(GameStateManager.CATEGORY_STATE);

            }
        });

        anslut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        this.setLayout(new BorderLayout());
        JPanel bp = new JPanel();
        bp.add(skapaQuiz);
        bp.add(anslut);
        add(bp, SOUTH);

    }
}
*/


