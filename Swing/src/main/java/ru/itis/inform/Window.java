package ru.itis.inform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

public class Window {

    public static void main(String[] args) {

        final JFrame frame = new JFrame("Welcome!");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));

        JButton exit = new JButton("exit");

        exit.setMaximumSize(new Dimension(80,30));

        menuBar.add(exit);

        JButton about = new JButton("about");

        about.setMaximumSize(new Dimension(80,30));

        menuBar.add(about);

        JMenuBar eastPanel = new JMenuBar();

        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

        JButton showRed = new JButton("Show Red");

        showRed.setMaximumSize(new Dimension(150,30));

        JButton showTime = new JButton("Show Time");

        showTime.setMaximumSize(new Dimension(150,30));

        showTime.setSize(150, 50);

        JButton showWeather = new JButton("Show Weather");

        showWeather.setMaximumSize(new Dimension(150,30));

        eastPanel.add(showRed);

        eastPanel.add(Box.createVerticalStrut(20));

        eastPanel.add(showTime);

        eastPanel.add(Box.createVerticalStrut(20));

        eastPanel.add(showWeather);

        final JPanel center = new JPanel();

        center.setLayout(new BorderLayout());

        center.setBackground(Color.white);

        final JLabel centerlabel = new JLabel("Hello!");

        centerlabel.setFont(new Font("Comic sans ms", Font.ITALIC,18));

        center.add(centerlabel,BorderLayout.NORTH);

        frame.getContentPane().add(center,BorderLayout.CENTER);

        frame.getContentPane().add(menuBar, BorderLayout.NORTH);

        frame.getContentPane().add(eastPanel, BorderLayout.EAST);

        frame.setSize(600,400);

        frame.setVisible(true);

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);

                System.exit(0);

            }
        });

        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "You Are Welcome!");
            }
        });

        showRed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(center.getBackground().equals(Color.white)){   center.setBackground(Color.RED);}
                else{center.setBackground(Color.white);}
            }
        });

        showTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DateFormat format = DateFormat.getTimeInstance();
            if (centerlabel.getText().equals("Hello!")){
                centerlabel.setText(format.format(new Date()));
                }
            else{centerlabel.setText("Hello!");}
            }

            }
        );
        showWeather.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                String city = JOptionPane.showInputDialog ("Enter your City:");

                Weather w = new Weather(city);

                w.runWeatherSearch();

                String[] s = w.getWeather();

                Double temp = new Double(w.getTemp());

                int i = temp.intValue();

                i-=270;

                String t = String.valueOf(i);

                String result = s[0]+ " : "+t+" °C, " +s[1] ;


                JOptionPane.showMessageDialog(frame,result);

            }
        });

    }



}
