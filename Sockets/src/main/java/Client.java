import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public Client() throws IOException {

        int port = 8080;
        String host = "localhost";
        Socket s = new Socket(host, port);
        OutputStream out = s.getOutputStream();
        PrintWriter pw = new PrintWriter(out);
        pw.println("GET / Ivan/1");
        pw.println("HOST: localhost");
        pw.println("5");
        pw.println("8");
        pw.flush();

    }
    public static void main(String [] args) throws IOException {
        new Client();
    }
}
