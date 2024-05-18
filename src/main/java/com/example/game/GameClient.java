package com.example.game;

import javafx.application.Platform;

import java.io.*;
import java.net.*;

public class GameClient {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private static ObjectInputStream in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    public void sendMessage(Object message) throws IOException {
        out.writeObject(message);
    }

    public Object receiveMessage() throws IOException, ClassNotFoundException {
        return in.readObject();
    }
    public static void waitForStartSignal() throws IOException, ClassNotFoundException {
        Object message;
        while ((message = in.readObject()) != null) {
            if ("START_GAME".equals(message.toString())) {
                // Запуск игры
                startGame();
                break;
            }
        }
    }

    private static void startGame() {
        // Реализуйте логику перехода на игровую сцену
        Platform.runLater(() -> {
            try {
                HelloApplication.changeScene("game-scene.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
