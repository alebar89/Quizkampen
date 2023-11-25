package Quizkampen.GamePanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;

public class Lobby extends JFrame {


        JPanel panel = new JPanel();
        JButton skapaQuiz = new JButton("Skapa Quiz"); // välja kategori
        JButton anslut = new JButton("Gå med i spel"); // gå med ett spel

        Lobby(){
            setTitle("Quiz spel lobby");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(400,300);
            setVisible(true);

            skapaQuiz.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            anslut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            panel.setLayout(new BorderLayout());
            add(panel, CENTER);
            JPanel bp = new JPanel();
            bp.add(skapaQuiz);
            bp.add(anslut);
            add(bp, SOUTH);

        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                new Lobby();
            });
        }

    }

