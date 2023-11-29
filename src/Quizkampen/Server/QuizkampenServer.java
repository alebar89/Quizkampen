package Quizkampen.Server;

import java.util.ArrayList;
import java.util.List;


public class QuizkampenServer {

    public PlayerThread currentPlayer;
    private QuestionDatabase database = Server.database;

    private PlayerThread getPlayerOne() {
        if (currentPlayer.getName().equalsIgnoreCase("Spelare 1")) {
            return currentPlayer;
        } else {
            return currentPlayer.getOpponentPlayer();
        }
    }

    private PlayerThread getPlayerTwo(){
        return getPlayerOne().getOpponentPlayer();
    }

    private List<String> resultList = new ArrayList<String>();

    public void addResult(String p) {
        resultList.add(p);
    }

    public List<String> getResults() {
        return resultList;
    }

    public QuestionDatabase getDatabase() {
        return database;
    }
}