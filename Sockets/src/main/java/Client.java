import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Scanner sc;

    private String n1;

    private String n2;

    private String op;

    private final int PORT = 8080;

    private String host;

    private Socket s;

    private OutputStream out;

    private PrintWriter pw;

    public Client() throws IOException {

        sc = new Scanner(System.in);

        n1 = sc.nextLine();

        op = sc.nextLine();

        n2 = sc.nextLine();

        host = "localhost";

        s = new Socket(host, PORT);

        out = s.getOutputStream();

        pw = new PrintWriter(out);


    }
    public void run(){

        pw.println("GET / Ivan/1.0");

        pw.println(" HOST: localhost &");

        pw.println(n1);

        pw.println(op);

        pw.println(n2);

        pw.flush();

    }

    public static void main(String[] args) throws IOException {
        new Client();
    }
}
