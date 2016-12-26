package ru.itis.inform;

import java.io.*;
import java.net.Socket;


public class Client {
    private static Socket socket;
    private static final int PORT = 8080;
    private static final String HOST = "localhost";
    private static  String protocol = "GET / Ivan/1.0 &";

    public Client() throws IOException {
        socket = new Socket(HOST,PORT);
    }


    public static void main(String[] args) throws IOException {
        new Client();
        ChatComponent clientWindow = new ChatComponent();
        clientWindow.setVisible(true);
        clientWindow.setResizable(false);
        clientWindow.startChat();
    }

    public static Socket getSocket() {
        return socket;
    }

    public static String getProtocol() {
        return protocol;
    }


}

