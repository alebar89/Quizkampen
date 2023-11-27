package Quizkampen.Server;

import Quizkampen.Game.Game;
import Quizkampen.Server.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class QuizkampenServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server Körs. Väntar på spelare...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Spelare Kopplad " + clientSocket);

                // Create a new thread to handle the client
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}