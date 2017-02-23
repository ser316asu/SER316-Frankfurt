/**************************************************************
 * Copyright (c) 2017 - 2017, Joshua Becker, Alec Shinn,
 * All rights reserved
 * SER316-Frankfurt is a project for ser216, 
 * using agile scrum.
 * Description:
 * 
 * Contact: jdbecke3@asu.edu, atshinn@asu.edu
 **************************************************************/
package net.sf.memoranda.ui.DevelopHomePage;
import java.awt.*;
import javax.swing.*;
// TODO: Auto-generated Javadoc

/**
 * The Interface Styling.
 */
public interface Styling{

	/** The Constant LABEL_PANEL_TEXT_COLOR. */
	static final Color LABEL_PANEL_TEXT_COLOR = new Color(255,255,255);
	
	/** The Constant BORDER_COLOR. */
	static final Color BORDER_COLOR = new Color(125,125,125);
	
	/** The Constant BACKGROUND_COLOR. */
	static final Color BACKGROUND_COLOR = new Color(243,243,243);//142,175,226);//0,34,64);//0,20,40);//(0,32,66);//(15,39,91);//Color(53,58,66);Color(113,114,106);

	/** The Constant TASK_PANEL_TEXT_COLOR. */
	static final Color TASK_PANEL_TEXT_COLOR = new Color(255,255,255);
    
    /** The Constant TASK_PANEL_COLOR. */
    static final Color TASK_PANEL_COLOR = new Color(0,0,0);
    
    /** The Constant TASK_PANEL_COLOR_ONHOVER. */
    static final Color TASK_PANEL_COLOR_ONHOVER = new Color(125,125,125);
    
    /** The Constant TASK_PANEL_BOARDER_COLOR. */
    static final Color TASK_PANEL_BOARDER_COLOR = new Color(125,125,125);
    
    /** The Constant TIMER_PANEL_COLOR. */
    static final Color TIMER_PANEL_COLOR = new Color(0,0,0,255);
    
    /** The Constant PROGRESS_PANEL_COLOR. */
    static final Color PROGRESS_PANEL_COLOR = new Color(0,0,0,255);
    
    /** The Constant PROGRESSBAR_HOURS_COLOR. */
    static final Color PROGRESSBAR_HOURS_COLOR= new Color(50,50,50);
    
    /** The Constant PROGRESSBAR_LOC_COLOR. */
    static final Color PROGRESSBAR_LOC_COLOR= new Color(10,50,50);
    
    /** The Constant PROGRESSBAR_DAYS_COLOR. */
    static final Color PROGRESSBAR_DAYS_COLOR= new Color(100,50,50);
    
	/** The Constant FONT. */
	static final Font FONT = new Font("Staubach",Font.BOLD,20);
	
	/** The Constant TERMINAL_FONT. */
	static final Font TERMINAL_FONT = new Font("Staubach",Font.BOLD,20);
	
	/** The Constant TIMER_FONT. */
	static final Font TIMER_FONT = new Font("Staubach",Font.BOLD,60);
	
	/** The Constant TASK_PANEL_FONT. */
	static final Font TASK_PANEL_FONT = new Font("Rudiment",Font.BOLD,20);
	
	/** The Constant TOOLBAR_FONT. */
	static final Font TOOLBAR_FONT = new Font("Rudiment",Font.BOLD,35);
	
	/** The Constant SCREEN_WIDTH. */
	//sizing <a href="https://clipartfest.com/">clipartfest.com</a>
	static final int SCREEN_WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
	
	/** The Constant SCREEN_HEIGHT. */
	static final int SCREEN_HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
	
	/** The Constant MAIN_TOOLBAR_HEIGHT. */
	static final int MAIN_TOOLBAR_HEIGHT = 300;
	
	/** The Constant MAIN_TOOLBAR_WIDTH. */
	static final int MAIN_TOOLBAR_WIDTH = 50;
	
	/** The Constant TASK_BOARD_WIDTH. */
	static final int TASK_BOARD_WIDTH = SCREEN_WIDTH-MAIN_TOOLBAR_WIDTH- 10;// - MAIN_TOOLBAR_WIDTH-2;
	
	/** The Constant TASK_BOARD_HEIGHT. */
	static final int TASK_BOARD_HEIGHT = ((int) (SCREEN_HEIGHT * .6)) -50;
	
	/** The Constant TASK_PANEL_WIDTH. */
	static final int TASK_PANEL_WIDTH = TASK_BOARD_WIDTH/5 -110;
	
	/** The Constant TASK_PANEL_HEIGHT. */
	static final int TASK_PANEL_HEIGHT = TASK_BOARD_HEIGHT/2 - 100;
	
	/** The Constant TOP_PANEL_WIDTH. */
	static final int TOP_PANEL_WIDTH = TASK_BOARD_WIDTH;// - MAIN_TOOLBAR_WIDTH-2;
    
    /** The Constant TOP_PANEL_HEIGHT. */
    static final int TOP_PANEL_HEIGHT = (int) (SCREEN_HEIGHT * .4)-10;
    
	/** The Constant TERMINAL_PANEL_WIDTH. */
	static final int TERMINAL_PANEL_WIDTH = (int) (TOP_PANEL_WIDTH*.6)-1;
	
	/** The Constant TERMINAL_PANEL_HEIGHT. */
	static final int TERMINAL_PANEL_HEIGHT = (int) (TOP_PANEL_HEIGHT);
	
	/** The Constant TERMINAL_RIGHT_PANEL_WIDTH. */
	static final int TERMINAL_RIGHT_PANEL_WIDTH = TOP_PANEL_WIDTH;
	
	/** The Constant TERMINAL_RIGHT_PANEL_HEIGHT. */
	static final int TERMINAL_RIGHT_PANEL_HEIGHT  = (int) (TOP_PANEL_HEIGHT);
	
	/** The Constant TIMER_PANEL_WIDTH. */
	static final int TIMER_PANEL_WIDTH = (int) (TOP_PANEL_WIDTH*.4);
	
	/** The Constant TIMER_PANEL_HEIGHT. */
	static final int TIMER_PANEL_HEIGHT  = (int) (TERMINAL_RIGHT_PANEL_HEIGHT*.4);
	
	/** The Constant PROGRESS_PANEL_WIDTH. */
	static final int PROGRESS_PANEL_WIDTH = TIMER_PANEL_WIDTH;
	
	/** The Constant PROGRESS_PANEL_HEIGHT. */
	static final int PROGRESS_PANEL_HEIGHT  = (int) (TERMINAL_RIGHT_PANEL_HEIGHT*.6);
	
	/** The Constant PROGRESS_PANEL_CIRCLES_WIDTH. */
	static final int PROGRESS_PANEL_CIRCLES_WIDTH = PROGRESS_PANEL_WIDTH/3 -1;
	
	/** The Constant PROGRESS_PANEL_CIRCLES_HEIGTH. */
	static final int PROGRESS_PANEL_CIRCLES_HEIGTH = PROGRESS_PANEL_HEIGHT/3 * 2 -1;
	
	/** The Constant PROGRESS_PANEL_LABELS_WIDTH. */
	static final int PROGRESS_PANEL_LABELS_WIDTH = PROGRESS_PANEL_WIDTH/3 -1;
	
	/** The Constant PROGRESS_PANEL_LABELS_HEIGTH. */
	static final int PROGRESS_PANEL_LABELS_HEIGTH = PROGRESS_PANEL_HEIGHT/3 -1;
	
	/** The Constant TOOLBAR_BUTTON_WIDTH. */
	static final int TOOLBAR_BUTTON_WIDTH = MAIN_TOOLBAR_WIDTH -2;
	
	/** The Constant TOOLBAR_BUTTON_HEIGHT. */
	static final int TOOLBAR_BUTTON_HEIGHT = MAIN_TOOLBAR_HEIGHT/6;
	
	/**
	 * Style.
	 */
	public void style(); 
}