package Quizkampen.Server;

import java.net.ServerSocket;
import java.net.SocketException;


public class Server {
    /**
     * Runs the application. Pairs up clients that connect.
     */
    public static QuestionDatabase database = new QuestionDatabase();
    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(44444)) {
            System.out.println("Server is Running");
            while (!listener.isClosed()) {
                QuizkampenServer game = new QuizkampenServer();
                PlayerThread playerOne
                        = new PlayerThread(listener.accept(), "Spelare 1", game);
                System.out.println("Player ONE connected");
                PlayerThread playerTWO
                        = new PlayerThread(listener.accept(), "Spelare 2", game);
                System.out.println("Player TWO connected");
                playerOne.setOpponentPlayer(playerTWO);
                playerTWO.setOpponentPlayer(playerOne);
                game.currentPlayer = playerOne;
                playerOne.start();
                playerTWO.start();
            }
        }catch (SocketException e){
            System.out.println("Player exited the game");
        }
    }
}