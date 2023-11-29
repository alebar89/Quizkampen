package Quizkampen.Server;

import java.io.*;
import java.net.Socket;


public class PlayerThread extends Thread {
    String playerID;
    PlayerThread opponentPlayer;
    Socket socket;
    ObjectInputStream input;
    ObjectOutputStream output;
    QuizkampenServer game;

    public PlayerThread(Socket socket, String playerID, QuizkampenServer game) {
        this.socket = socket;
        this.playerID = playerID;
        this.game = game;
        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject("WELCOME " + playerID);
            output.writeObject(game.getDatabase().getDBquestions());
            output.writeObject("MESSAGE Waiting for opponent to connect");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The run method of this thread. Receives scores from clients and adds them to the resultList, which is then sent back to clients.
     */
    public void run() {

        try {
            // Tell the first player that it is her turn.
            if (playerID.equals("Spelare 1")) {
                System.out.println("playerOneTurn");
                output.writeObject("YOUR_TURN");
            }

            if (playerID.equals("Spelare 2")) {
                System.out.println("playerTwoTurn");
                output.writeObject("YOUR_TURN");
            }

            while (true) {
                sleep(200);
                String resp = (String) input.readObject();
                if (input == null) {
                    return;
                }
                if (resp.startsWith("ROUND_OVER")) {
                    String res = resp.substring(10);
                    System.out.println(res);
                    game.addResult(res.trim());
                    output.writeObject("RESULT " + game.getResults());
                } else if (resp.startsWith("ENDROUND")) {
                    output.writeObject("RESULT " + game.getResults());
                }
                else if (resp.startsWith("WAITING")){
                    output.writeObject("MESSAGE Wait for your turn");
                }
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
    public void setOpponentPlayer(PlayerThread opponentPlayer) {
        this.opponentPlayer = opponentPlayer;
    }

    public PlayerThread getOpponentPlayer() {
        return opponentPlayer;
    }
}