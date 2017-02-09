package net.sf.memoranda.ui.DevelopHomePage;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.net.URL;

class LoadAssets 
{
    public static ImageIcon TASK_PANEL_IMAGE = loadImage("Assets/ui/frame.png", Styling.TASK_PANEL_WIDTH,Styling.TASK_PANEL_HEIGHT);
    public static ImageIcon TASK_BOARD_IMAGE = loadImage("Assets/ui/BoardBackground.jpg", Styling.TASK_BOARD_WIDTH,Styling.TASK_BOARD_HEIGHT);
    public static ImageIcon HOMEPAGE_BACKGROUND = loadImage("Assets/ui/HomePageBackground.jpg", Styling.SCREEN_WIDTH,Styling.SCREEN_HEIGHT);
    public static Icon TERMINAL_IMAGE = loadImage("Assets/ui/terminalBackground.png",Styling.TERMINAL_PANEL_WIDTH, Styling.TERMINAL_PANEL_HEIGHT);
    public LoadAssets()
    {
        loadFont("Assets/ui/Rudiment.ttf");
        loadFont("Assets/ui/Staubach.ttf");
    }
    private static ImageIcon loadImage(String name, int w, int h)
	{
		Image img;
		try 
		{
			img = ImageIO.read(new File(name));
			img = img.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(img);
		} catch (IOException ex) 
		{
			System.out.println("FIle Not Found\nFile Path: " + name);
            System.exit(0);
		}
		return null;
	}
    private void loadFont(String name)
    {
    	Font font = null;
    	try
    	{
    		File fontFile = new File(name);
    		font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
    		
    	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	    ge.registerFont(font);
    	}catch(IOException e)
    	{
			System.out.println("FIle Not Found\nFile Path: " + name);
            System.exit(0);
    	}catch(FontFormatException e)
    	{
			System.out.println("Font Format Exeption\nFile Path: " + name);
            System.exit(0);
    	}
    }
}