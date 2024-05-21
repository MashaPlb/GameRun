package com.example.game;

import java.io.IOException;

public class GameClientRunner {
    public static void main(String[] args) {
        GameClient client = new GameClient();
        try {
            // Замените "localhost" на IP-адрес сервера, если он запущен на другом компьютере
            client.startConnection("localhost", 6666);
            // Теперь клиент подключен и может отправлять сообщения
            client.sendMessage("Привет, я второй игрок!");
            // Получение сообщений от сервера (если они есть)
            Object message = client.receiveMessage();
            System.out.println(message);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.stopConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}