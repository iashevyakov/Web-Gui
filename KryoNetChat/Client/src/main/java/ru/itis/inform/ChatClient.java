package ru.itis.inform;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import ru.itis.inform.Network.*;

import java.io.IOException;
import java.util.Scanner;

public class ChatClient {

    private Client client;
    private String leave;
    private Scanner sc;

    public ChatClient() {
        client = new Client();
        client.start();
        sc = new Scanner(System.in);
        Network.register(client);

        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof ChatMessage) {
                    ChatMessage chatMessage = (ChatMessage) object;
                    System.out.println(chatMessage.text);
                    return;
                }
            }
        });


        new Thread("Connecting") {
            public void run() {
                try {
                    client.connect(5000, "localhost", Network.port);
                    leave = "";
                    ChatMessage chatMessage = new ChatMessage();
                    while (!leave.equals("leave")) {
                        leave = sc.nextLine();
                        chatMessage.text = leave;
                        client.sendTCP(chatMessage);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        }.start();
    }


    public static void main(String[] args) {
        new ChatClient();
    }
}