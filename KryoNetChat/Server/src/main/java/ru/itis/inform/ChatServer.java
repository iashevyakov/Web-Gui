package ru.itis.inform;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import com.esotericsoftware.minlog.Log;
import ru.itis.inform.Network.ChatMessage;

public class ChatServer {

    private Server server;

    private String answer;

    public ChatServer () throws IOException {
        server = new Server() {
            protected Connection newConnection () {
                return new ChatConnection();
            }
        };

        Network.register(server);

        server.addListener(new Listener() {
            public void received (Connection c, Object object) {
                ChatConnection connection = (ChatConnection)c;
                if (object instanceof ChatMessage) {
                    ChatMessage chatMessage = (ChatMessage)object;
                    if (connection.name==null){
                        connection.name = chatMessage.text;
                        chatMessage.text = "Welcome,"+ connection.name;
                        server.sendToTCP(connection.getID(), chatMessage);
                        return;
                    }
                    else  {
                        if(chatMessage.text==null||chatMessage.text.length()==0)return;
                        answer=chatMessage.text;
                        chatMessage.text=connection.name+"@ "+answer;
                        server.sendToAllTCP(chatMessage);
                        return;
                    }
                }
            }

            public void disconnected (Connection c) {
                ChatConnection connection = (ChatConnection)c;
                if (connection.name != null) {
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.text = connection.name + "left.";
                    server.sendToAllTCP(chatMessage);
                }
            }
        });
        server.bind(Network.port);
        server.start();
    }

    static class ChatConnection extends Connection {
        public String name;
    }

    public static void main (String[] args) throws IOException {
        Log.set(Log.LEVEL_DEBUG);
        new ChatServer();
    }
}