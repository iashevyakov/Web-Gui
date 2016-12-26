package ru.itis.inform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;



public class ChatComponent extends JFrame {
    private JPanel mainPanel = new JPanel();
    private JPanel downPanel = new JPanel();
    private JTextField textField = new JTextField();
    private static JTextArea textArea = new JTextArea();
    private JButton jButton = new JButton("Send");
    private JScrollPane jScrollPane = new JScrollPane(textArea);

    public ChatComponent(){
        super("Welcome!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(490,485);

        jScrollPane.setPreferredSize(new Dimension(465, 400));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textField.setPreferredSize(new Dimension(400,22));
        textArea.setEditable(false);


        mainPanel.add(jScrollPane);
        mainPanel.add(textField);
        mainPanel.add(jButton);
        add(mainPanel, BorderLayout.CENTER);
        add(downPanel, BorderLayout.NORTH);


        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PrintWriter pw = Sender.getPw();
                String text = textField.getText();
                Sender.setMessage(text);
                textField.setText("");
                pw.print(Client.getProtocol());
                pw.println(text);
                pw.flush();
            }
        });
    }

    public static JTextArea getTextArea() {
        return textArea;
    }

    public void startChat(){
        try {
            Thread thread1 = new Thread(new Receiver());
            Thread thread2 = new Thread(new Sender());
            thread1.start();
            thread2.start();
        }
        catch (Exception e){
            textArea.setText("SERVER DOESN'T WORK!");
        }
    }
}
