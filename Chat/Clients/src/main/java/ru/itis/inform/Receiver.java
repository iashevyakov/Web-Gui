package ru.itis.inform;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Receiver implements Runnable {
    private String str;
    private JTextArea textArea;
    private BufferedReader bf;
    public void run() {
        try{
            bf = new BufferedReader(new InputStreamReader(Client.getSocket().getInputStream()));
            textArea = ChatComponent.getTextArea();
            while (true){
                str ="\n"+ bf.readLine();
                textArea.append(str);
            }

        }
        catch (IOException e){
            System.out.print(e.getMessage());
        }
    }

}