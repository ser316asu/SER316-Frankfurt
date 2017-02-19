package net.sf.memoranda.ui.DevelopHomePage;
/**


**/
import javax.swing.*;
import java.awt.*;

public class Develop extends JFrame
{
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
    public Develop()
    {
    	this.setJMenuBar(new MainMenuBar(this));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();// geting size of screen
		Develop.SCREEN_WIDTH = gd.getDisplayMode().getWidth();
		Develop.SCREEN_HEIGHT = gd.getDisplayMode().getHeight();
        
		HomePanel hp = new HomePanel();
		hp.setPreferredSize(new Dimension(Develop.SCREEN_WIDTH,Develop.SCREEN_HEIGHT));
		this.add(hp);
		

        //frame.add(new JLabel("THis is a test"));

        //Add stuff to the frame
        this.pack();
        this.setVisible(true);
    }
    public Develop(TaskCard task){
    	//this.setJMenuBar(new MainMenuBar(this));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();// geting size of screen
        SCREEN_WIDTH = gd.getDisplayMode().getWidth();
        SCREEN_HEIGHT = gd.getDisplayMode().getHeight();
        System.out.println(Develop.SCREEN_WIDTH + " HELLO " +Develop.SCREEN_HEIGHT);
        HomePanel hp = new HomePanel(task);
        hp.setPreferredSize(new Dimension(Develop.SCREEN_WIDTH,Develop.SCREEN_HEIGHT));
        this.add(hp);
        

        //frame.add(new JLabel("THis is a test"));

        //Add stuff to the frame
        this.pack();
        this.setVisible(true);
    }
}