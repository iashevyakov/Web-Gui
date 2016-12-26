package ru.itis.inform;
import ru.itis.inform.ClientConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;


public class Server {

    private static ServerSocket serverSocket;

    private static Socket socket;

    private static final int PORT = 8080;

    private static List<ClientConnection> list;

    private int numberOfClients;


    public Server() {
        try {
            list = new LinkedList<ClientConnection>();

            serverSocket = new ServerSocket(Server.PORT);

            while ((socket = serverSocket.accept()) != null) {

                ClientConnection conn = new ClientConnection(socket);

                list.add(conn);

                numberOfClients = list.size();

                System.out.println(numberOfClients);

                new Thread(conn).start();

            }

        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
    public static List<ClientConnection> getList(){
        return list;
    }

    public static void main(String[] args) {
        new Server();
    }
}