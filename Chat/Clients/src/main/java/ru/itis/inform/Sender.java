package ru.itis.inform;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Sender implements Runnable{
    private static PrintWriter pw;
    private static String message;

    public Sender(){
        try {
            this.pw = new PrintWriter(new OutputStreamWriter(Client.getSocket().getOutputStream()));
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void run() {

    }

    public static void setMessage(String message) {
        Sender.message = message;
    }

    public  static PrintWriter getPw() {
        return pw;
    }
}