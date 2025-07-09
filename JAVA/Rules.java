import javax.swing.*;
import java.awt.*;

public class Rules extends JFrame{
    Rules(){
        getContentPane().setBackground(Color.WHITE);

        setLayout(null);


        JLabel heading = new JLabel("MIND GAME");
        heading.setBounds(50,20,700,30);
        heading.setFont(new Font("Arial",Font.BOLD,40));
        heading.setForeground(Color.BLUE);
        add(heading);

        setSize(800,650);
        setLocation(300,100);
        setVisible(true);

    }
    public static void main(String[] args){
        new Rules();
    }
}
