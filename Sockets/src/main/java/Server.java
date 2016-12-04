
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

    private static final String MESSAGE = "Hello world!";

    public Server() {
        try {
            List<String> list = new LinkedList<String>();
            // Create server listening PORT
            serverSocket = new ServerSocket(Server.PORT);
            // Get new connection
            while ((socket = serverSocket.accept()) != null) {
                // REad request
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while (true) {
                    String s = br.readLine();
                    if (s == null || s.trim().length() == 0) {
                        break;
                    }

                    System.out.println(s);
                    list.add(s);
                }
                System.out.println("---");
                String s = list.get(list.size()-1);
                String s1 = list.get(list.size()-2);
                System.out.println(s+s1);
                PrintWriter pr = new PrintWriter(socket.getOutputStream());
                pr.println("HTTP/1.1 200 OK");
                pr.println("Content-type:text/plain;Charset:UTF-8");
                pr.println("Content-length:" + MESSAGE.length());
                pr.println();
                pr.println(MESSAGE);
                pr.flush();
                socket.close();
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}