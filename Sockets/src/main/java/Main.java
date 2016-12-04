import java.io.*;
import java.net.Socket;


public class Main {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("www.google.com",80);

        PrintWriter pw =  new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        pw.println("GET /doodles HTTP/1.1");

        pw.println("HOST: www.google.com");

        pw.println("User-Agent: Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko");

        pw.println();

        pw.println();

        pw.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String a = reader.readLine();

        while(a!=null) {

            System.out.println(a);

            a = reader.readLine();

        }

        reader.close();

    }

}
