package Quizkampen.Game;

import Quizkampen.GamePanels.CategoryWindowPanel;
import Quizkampen.GamePanels.GamePanel;
import Quizkampen.GamePanels.LobbyPanel;
import Quizkampen.GamePanels.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class GameStateManager {

    private CardLayout cardLayout;  // använder cardlayout för att skifta mellan de olika rutorna.
    // det hela går ut på att alla paneler ligger på varandra men man väljer ut den panel som ska visas vid respektive stadie.
    private JPanel cardPanel;

    public static final int LOGIN_STATE = 0;
    public static final int LOBBY_STATE = 1;
    public static final int CATEGORY_STATE = 2;
    public static final int GAME_STATE = 3;
    public static final int RESULT_STATE = 4;

    public static int CURRENT_STATE = LOGIN_STATE;

    public GameStateManager(JFrame frame) {
        cardPanel = new JPanel(new CardLayout());
        cardLayout = (CardLayout)cardPanel.getLayout(); // kastar layouten till CardLayout så vi kan använda metoderna för det objektet
        frame.add(cardPanel);

        intitializeStates();

    }

    // initierar de olika korten(panelerna) som ska displayas för de olika stadierna i spelet.
    // lägg till era paneler här så kan vi fixa logiken till att hoppa till varje state.
    private void intitializeStates() {
        cardPanel.add(new LoginPanel(this), String.valueOf(LOGIN_STATE));
        cardPanel.add(new LobbyPanel(this), String.valueOf(LOBBY_STATE));
        cardPanel.add(new CategoryWindowPanel(this), String.valueOf(CATEGORY_STATE));
        cardPanel.add(new GamePanel(this), String.valueOf(GAME_STATE));


    }


    // metod för att byta stadieruta.
    public void setState(int state) {
        String stateString = String.valueOf(state);
        cardLayout.show(cardPanel, stateString);
        CURRENT_STATE = state;
    }



}