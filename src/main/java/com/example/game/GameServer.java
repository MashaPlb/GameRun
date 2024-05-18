package com.example.game;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameServer {
    private static final Logger LOGGER = Logger.getLogger(HelloApplication.class.getName());
    private final ServerSocket serverSocket;

    public GameServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() {
        System.out.println("Сервер запущен и ожидает подключений...");
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новый игрок подключился.");
                new ClientHandler(clientSocket).start();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Ошибка при подключении игрока: ", e);
            }
        }
    }

    private static class ClientHandler extends Thread {
        private final Socket clientSocket;
        private ObjectOutputStream out;
        private ObjectInputStream in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());

                // Отправка приветственного сообщения новому клиенту
                out.writeObject("Добро пожаловать на сервер!");

                // Обработка сообщений от клиента
                Object inputObject;
                while ((inputObject = in.readObject()) != null) {
                    // Обработайте входящий объект, например, ход игрока
                    handleClientInput(inputObject);
                }
            } catch (IOException | ClassNotFoundException e) {
                LOGGER.log(Level.SEVERE, "Ошибка при обмене данными с клиентом: ", e);
            } finally {
                closeConnections();
            }
        }

        // Метод для обработки входящих объектов
        private void handleClientInput(Object inputObject) {
            // Реализуйте логику обработки входящих сообщений от клиента
            System.out.println("Получено сообщение от клиента: " + inputObject.toString());
            // ...
        }

        // Метод для корректного закрытия соединений
        private void closeConnections() {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Ошибка при закрытии соединения: ", e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 6666;
        GameServer server = new GameServer(port);
        server.start();
    }
}