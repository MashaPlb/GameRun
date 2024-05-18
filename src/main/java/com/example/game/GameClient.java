package com.example.game;

import java.io.*;
import java.net.*;

public class GameClient {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

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

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
