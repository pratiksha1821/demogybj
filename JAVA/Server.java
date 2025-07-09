// package chatting.application;
import javax.swing.*;

import java.awt.Color;
import java.util.*;
import java.awt.*;

public class Server extends JFrame{
    Server(){
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        add(p1);
        setLayout(null);
        setSize(450,700);
        getContentPane().setBackground(Color.black);
        setLocation(200,50);
        setVisible(true);



    }
    public static void main(String[] args){
        new Server();
    }
}
