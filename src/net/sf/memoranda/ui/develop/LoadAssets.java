/**************************************************************
 * Copyright (c) 2017 - 2017, Joshua Becker, Alec Shinn,
 * All rights reserved
 * SER316-Frankfurt is a project for ser216, 
 * using agile scrum.
 * Description:
 * 
 * Contact: jdbecke3@asu.edu, atshinn@asu.edu
 **************************************************************/
package net.sf.memoranda.ui.develop;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * The Class LoadAssets.
 */
public class LoadAssets {
    
    /** The task panel image. */
    public static ImageIcon TASK_PANEL_IMAGE = loadImage("Assets/ui/frameNotSelected.png",
    		Styling.TASK_PANEL_WIDTH,Styling.TASK_PANEL_HEIGHT);
    
    /** The task panel selceted image. */
    public static ImageIcon TASK_PANEL_SELCETED_IMAGE = loadImage("Assets/ui/frameSelected.png",
    		Styling.TASK_PANEL_WIDTH,Styling.TASK_PANEL_HEIGHT);
    
    /** The task board image. */
    public static ImageIcon TASK_BOARD_IMAGE = loadImage("Assets/ui/BoardBackground.jpg",
    		Styling.TASK_BOARD_WIDTH,Styling.TASK_BOARD_HEIGHT);
    
    /** The homepage background. */
    public static ImageIcon HOMEPAGE_BACKGROUND = loadImage("Assets/ui/HomePageBackground.jpg",
    		Styling.SCREEN_WIDTH,Styling.SCREEN_HEIGHT);
    
    /** The terminal image. */
    public static Icon TERMINAL_IMAGE = loadImage("Assets/ui/terminalBackground.png",
    		Styling.SCREEN_WIDTH, Styling.SCREEN_HEIGHT);
    
    /** The toolbar calendar button image. */
    public static ImageIcon TOOLBAR_CALENDAR_BUTTON_IMAGE = loadImage("Assets/ui/calendarIconWhite.png",
    		Styling.TOOLBAR_BUTTON_WIDTH-10,Styling.TOOLBAR_BUTTON_HEIGHT-10);
    
    /** The toolbar calendar button hover image */
    public static ImageIcon TOOLBAR_CALENDAR_HOVER_BUTTON_IMAGE = loadImage("Assets/ui/calendarIconWhite_hover.png",
    		Styling.TOOLBAR_BUTTON_WIDTH-10,Styling.TOOLBAR_BUTTON_HEIGHT-10);
    
    /** The toolbar task button image. */
    public static ImageIcon TOOLBAR_TASK_BUTTON_IMAGE = loadImage("Assets/ui/NewTaskButton.png",
    		Styling.TOOLBAR_BUTTON_WIDTH-10,Styling.TOOLBAR_BUTTON_HEIGHT-10);
    
    /** The toolbar task button hover image */
    public static ImageIcon TOOLBAR_TASK_HOVER_BUTTON_IMAGE = loadImage("Assets/ui/NewTaskButton_hover.png",
    		Styling.TOOLBAR_BUTTON_WIDTH-10,Styling.TOOLBAR_BUTTON_HEIGHT-10);
    
    /** The toolbar home button image. */
    public static ImageIcon TOOLBAR_HOME_BUTTON_IMAGE = loadImage("Assets/ui/HomeButton.png",
    		Styling.TOOLBAR_BUTTON_WIDTH-10,Styling.TOOLBAR_BUTTON_HEIGHT-10);
    
    /** The toolbar home button hover image. */
    public static ImageIcon TOOLBAR_HOME_HOVER_BUTTON_IMAGE = loadImage("Assets/ui/HomeButton_hover.png",
    		Styling.TOOLBAR_BUTTON_WIDTH-10,Styling.TOOLBAR_BUTTON_HEIGHT-10);
    
    /** The toolbar notification button image. */
    public static ImageIcon TOOLBAR_BELL_BUTTON_IMAGE = loadImage("Assets/ui/bellnotifcation.png",
    		Styling.TOOLBAR_BUTTON_WIDTH-10,Styling.TOOLBAR_BUTTON_HEIGHT-10);
    
    /** The toolbar notification button image. */
    public static ImageIcon TOOLBAR_BELL_HOVER_BUTTON_IMAGE = loadImage("Assets/ui/bellnotifcation_hover.png",
    		Styling.TOOLBAR_BUTTON_WIDTH-10,Styling.TOOLBAR_BUTTON_HEIGHT-10);
    
    /** The toolbar file button image. */
    public static ImageIcon TOOLBAR_FILE_BUTTON_IMAGE = loadImage("Assets/ui/fileIcon.png",
    		Styling.TOOLBAR_BUTTON_WIDTH-10,Styling.TOOLBAR_BUTTON_HEIGHT-10);
    
    /** The toolbar file button hover image. */
    public static ImageIcon TOOLBAR_FILE_HOVER_BUTTON_IMAGE = loadImage("Assets/ui/fileIcon_hover.png",
    		Styling.TOOLBAR_BUTTON_WIDTH-10,Styling.TOOLBAR_BUTTON_HEIGHT-10);
    
    /** The toolbar notes button image. */
    public static ImageIcon TOOLBAR_NOTES_BUTTON_IMAGE = loadImage("Assets/ui/notesIcon.png",
    		Styling.TOOLBAR_BUTTON_WIDTH-10,Styling.TOOLBAR_BUTTON_HEIGHT-10);
    
    /** The toolbar notes button image. */
    public static ImageIcon TOOLBAR_NOTES_HOVER_BUTTON_IMAGE = loadImage("Assets/ui/notesIcon_hover.png",
    		Styling.TOOLBAR_BUTTON_WIDTH-10,Styling.TOOLBAR_BUTTON_HEIGHT-10);
    
    /**
     * Instantiates a new load assets.
     */
    public LoadAssets(){
        loadFont("Assets/ui/Rudiment.ttf");
        loadFont("Assets/ui/Staubach.ttf");
    }
    
    /**
     * Load image.
     *
     * @param name the name
     * @param w the w
     * @param h the h
     * @return the image icon
     */
    private static ImageIcon loadImage(String name, int w, int h){
		Image img;
		try {
			img = ImageIO.read(new File(name));
			img = img.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(img);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error file not found\n" + name, 
					"IOException", JOptionPane.ERROR_MESSAGE);
			System.out.println("FIle Not Found\nFile Path: " + name);
		}
		return null;
	}
    
    /**
     * Load font.
     *
     * @param name the name
     */
    private void loadFont(String name){
    	Font font = null;
    	try{
    		File fontFile = new File(name);
    		font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
    		
    	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	    ge.registerFont(font);
    	}catch(IOException e){
    		JOptionPane.showMessageDialog(null, "Error file not found\n" + name,
    				"IOException", JOptionPane.ERROR_MESSAGE);
			System.out.println("FIle Not Found\nFile Path: " + name);
            System.exit(0);
    	}catch(FontFormatException e){
    		JOptionPane.showMessageDialog(null, "Font Formate Error\n" + name,
    				"FontFormatException", JOptionPane.ERROR_MESSAGE);
			System.out.println("Font Format Exeption\nFile Path: " + name);
    	}
    }
}