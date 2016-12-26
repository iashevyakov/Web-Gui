package ru.itis.inform;
import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.regex.Matcher;

public class ClientConnection implements Runnable {

    private Socket socket;

    private String stringInputMessage;

    private PrintWriter pw;

    private BufferedReader br;

    private Matcher m1;

    private Matcher m2;

    private String name;


    public void run() {
        String st="first";
        while (true) {
            try {
                st = br.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            stringInputMessage = st;
            try {
                sending();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void sending() throws IOException {
        m1 = Patterns.REGEXP_MESSAGE.matcher(stringInputMessage);
        if (!m1.matches()) {
            pw.println("Incorrect REQUEST!");
            pw.flush();
        } else {
            if (name!=null) {
                m2 = Patterns.REGEXP_COUNT.matcher(stringInputMessage);
                if (m2.matches()) {
                    countSending();

                } else {
                    messageSending();
                }
            }
            else{
                pw.println("Enter your name!");
                pw.flush();
                name=br.readLine();
                name = name.substring(16);
                pw.println("Welcome,"+name+"!");
            }
        }
    }
    private void countSending(){
        for (ClientConnection cc : Server.getList()) {
            try {
                pw = new PrintWriter(cc.getSocket().getOutputStream());
                pw.print(String.valueOf(new Date().toString()+"| "+name+"@ "));
                pw.println(Calculator.count(m2.group(1), m2.group(3), m2.group(2)));
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void messageSending() {
        for (ClientConnection cc : Server.getList()) {

            try {
                pw = new PrintWriter(cc.getSocket().getOutputStream());
                pw.print(String.valueOf(new Date().toString()+"| "+name+"@ "));
                pw.println(m1.group(1));
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public ClientConnection(Socket s) throws IOException {

        this.socket = s;

        pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));

        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    private Socket getSocket() throws IOException {
        return socket;
    }

}
