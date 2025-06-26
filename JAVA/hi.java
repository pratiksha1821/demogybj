import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Project1
{
    public static void main(String[] args)
    {
	JFrame fr = new JFrame("Puzzle Game");

		//Making sure that the Frame appears at the Center of Screen

	fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	/*
		DO_NOTHING_ON_CLOSE
		HIDE_ON_CLOSE
		DISPOSE_ON_CLOSE
		EXIT_ON_CLOSE
	*/	

	fr.getContentPane().setBackground(Color.MAGENTA);	


//Adding Components

	JPanel p = new JPanel();
	GridLayout gl = new GridLayout(3, 3, 10, 10);	//for 3 rows and 3 columns
	p.setLayout(gl);	//Apply Layout on Panel

	MyListener ml = new MyListener();   //Creating object of Listener class

	for(int i=0; i<9; i++)
	{	
		JButton b1 = new JButton(""+i);
		p.add(b1);
		b1.addActionListener(ml);	//Register the Button with Listener object
	}

	fr.add(p);		//Panel should be added in the CENTER of Frame

	fr.setSize(600, 600);	//set the width and height in pixels
	fr.setLocationRelativeTo(null);
	fr.setVisible(true);		//To make the frame visible
    }
}

class MyListener implements ActionListener
{
     private static int count = 0;
     public void actionPerformed(ActionEvent e)
     {
	JButton b = (JButton) e.getSource();		//identifying the Source Object of the event

	if(count %2 == 0)
	{	b.setLabel("X");	b.setBackground(Color.YELLOW); }

	else 
	{	b.setLabel("O");	b.setBackground(Color.CYAN); }

	count++;
	b.setFont(new Font("Serif", 3, 80));
	//b.setEnabled(false);				//Disbale the Button
	b.setForeground(Color.RED);
	
	//System.out.println("Button " + e.getActionCommand() + " is Pressed");
     }
}