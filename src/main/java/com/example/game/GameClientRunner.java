package com.example.game;

import java.io.IOException;

public class GameClientRunner {
    public static void main(String[] args) {
        GameClient client = new GameClient();
        try {
            client.startConnection("localhost", 6666);
            client.sendMessage("Привет, я второй игрок!");
            Object message = client.receiveMessage();
            System.out.println(message);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}