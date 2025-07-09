import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener{
    Login(){
        JButton rules,back;

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("lo.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(0,0,600,500);
        add(image);
        JLabel heading = new JLabel("MIND GAME");
        heading.setBounds(750,60,800,45);
        heading.setFont(new Font("Arial",Font.BOLD,40));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel name = new JLabel("Enter your name");
        name.setBounds(810,150,300,20);
        name.setFont(new Font("Arial",Font.BOLD,18));
        name.setForeground(Color.black);
        add(name);

        JTextField tfname = new JTextField();
        tfname.setBounds(735,200,300,25);
        tfname.setFont(new Font("Times New Roman",Font.BOLD,20));
        add(tfname);

        rules= new JButton("Rules");
        rules.setBounds(735,270,120,25);
        rules.setBackground(Color.gray);
        rules.addActionListener(this);
        add(rules);


        back= new JButton("Back");
        back.setBounds(915,270,120,25);
        back.setBackground(Color.gray);
        back.addActionListener(this);
        add(back);



    






        setSize(1200,500);
        setLocation(200,100);
        setVisible(true);
        

    }
    public void actionPerformed(ActionEvent ae){
       
        if (ae.getSource() == rules){
            setVisible(false);
            new Rules();

        }else if(ae.getSource()== back){
            setVisible(false);

        }

    }
    public static void main(String[] args){
        new Login();
    }
}
