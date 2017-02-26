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
import java.awt.*;
// TODO: Auto-generated Javadoc

/**
 * The Interface Styling.
 */
public interface Styling{

	/** The Constant LABEL_PANEL_TEXT_COLOR. */
	Color LABEL_PANEL_TEXT_COLOR = new Color(255,255,255);
	
	/** The Constant BORDER_COLOR. */
	Color BORDER_COLOR = new Color(125,125,125);
	
	/** The Constant BACKGROUND_COLOR. */
	Color BACKGROUND_COLOR = new Color(243,243,243);

	/** The Constant TASK_PANEL_TEXT_COLOR. */
	Color TASK_PANEL_TEXT_COLOR = new Color(255,255,255);
    
    /** The Constant TASK_PANEL_COLOR. */
    Color TASK_PANEL_COLOR = new Color(0,0,0);
    
    /** The Constant TASK_PANEL_COLOR_ONHOVER. */
    Color TASK_PANEL_COLOR_ONHOVER = new Color(125,125,125);
    
    /** The Constant TASK_PANEL_BOARDER_COLOR. */
    Color TASK_PANEL_BOARDER_COLOR = new Color(125,125,125);
    
    /** The Constant TIMER_PANEL_COLOR. */
    Color TIMER_PANEL_COLOR = new Color(0,0,0,255);
    
    /** The Constant PROGRESS_PANEL_COLOR. */
    Color PROGRESS_PANEL_COLOR = new Color(0,0,0,255);
    
    /** The Constant PROGRESSBAR_HOURS_COLOR. */
    Color PROGRESSBAR_HOURS_COLOR= new Color(50,50,50);
    
    /** The Constant PROGRESSBAR_LOC_COLOR. */
    Color PROGRESSBAR_LOC_COLOR= new Color(10,50,50);
    
    /** The Constant PROGRESSBAR_DAYS_COLOR. */
    Color PROGRESSBAR_DAYS_COLOR= new Color(100,50,50);
    
	/** The Constant FONT. */
	Font FONT = new Font("Staubach",Font.BOLD,20);
	
	/** The Constant TERMINAL_FONT. */
	Font TERMINAL_FONT = new Font("Staubach",Font.BOLD,20);
	
	/** The Constant TIMER_FONT. */
	Font TIMER_FONT = new Font("Staubach",Font.BOLD,60);
	
	/** The Constant TASK_PANEL_FONT. */
	Font TASK_PANEL_FONT = new Font("Rudiment",Font.BOLD,20);
	
	/** The Constant TOOLBAR_FONT. */
	Font TOOLBAR_FONT = new Font("Rudiment",Font.BOLD,35);
	
	/** The Constant SCREEN_WIDTH. */
	//sizing <a href="https://clipartfest.com/">clipartfest.com</a>
	int SCREEN_WIDTH = 
			GraphicsEnvironment.getLocalGraphicsEnvironment().
			getDefaultScreenDevice().getDisplayMode().getWidth();
	
	/** The Constant SCREEN_HEIGHT. */
	int SCREEN_HEIGHT = 
			GraphicsEnvironment.getLocalGraphicsEnvironment().
			getDefaultScreenDevice().getDisplayMode().getHeight();
	
	/** The Constant MAIN_TOOLBAR_HEIGHT. */
	int MAIN_TOOLBAR_HEIGHT = 300;
	
	/** The Constant MAIN_TOOLBAR_WIDTH. */
	int MAIN_TOOLBAR_WIDTH = 50;
	
	/** The Constant TASK_BOARD_WIDTH. */
	int TASK_BOARD_WIDTH = SCREEN_WIDTH-MAIN_TOOLBAR_WIDTH- 10;// - MAIN_TOOLBAR_WIDTH-2;
	
	/** The Constant TASK_BOARD_HEIGHT. */
	int TASK_BOARD_HEIGHT = ((int) (SCREEN_HEIGHT * .6)) -50;
	
	/** The Constant TASK_PANEL_WIDTH. */
	int TASK_PANEL_WIDTH = TASK_BOARD_WIDTH/5 -110;
	
	/** The Constant TASK_PANEL_HEIGHT. */
	int TASK_PANEL_HEIGHT = TASK_BOARD_HEIGHT/2 - 100;
	
	/** The Constant TOP_PANEL_WIDTH. */
	int TOP_PANEL_WIDTH = TASK_BOARD_WIDTH;// - MAIN_TOOLBAR_WIDTH-2;
    
    /** The Constant TOP_PANEL_HEIGHT. */
    int TOP_PANEL_HEIGHT = (int) (SCREEN_HEIGHT * .4)-10;
    
	/** The Constant TERMINAL_PANEL_WIDTH. */
	int TERMINAL_PANEL_WIDTH = (int) (TOP_PANEL_WIDTH*.6)-10;
	
	/** The Constant TERMINAL_PANEL_HEIGHT. */
	int TERMINAL_PANEL_HEIGHT = (int) (TOP_PANEL_HEIGHT);
	
	/** The Constant TERMINAL_RIGHT_PANEL_WIDTH. */
	int TERMINAL_RIGHT_PANEL_WIDTH = TOP_PANEL_WIDTH -10;
	
	/** The Constant TERMINAL_RIGHT_PANEL_HEIGHT. */
	int TERMINAL_RIGHT_PANEL_HEIGHT  = (int) (TOP_PANEL_HEIGHT);
	
	/** The Constant TIMER_PANEL_WIDTH. */
	int TIMER_PANEL_WIDTH = (int) (TOP_PANEL_WIDTH*.4);
	
	/** The Constant TIMER_PANEL_HEIGHT. */
	int TIMER_PANEL_HEIGHT  = (int) (TERMINAL_RIGHT_PANEL_HEIGHT*.4);
	
	/** The Constant PROGRESS_PANEL_WIDTH. */
	int PROGRESS_PANEL_WIDTH = TIMER_PANEL_WIDTH;
	
	/** The Constant PROGRESS_PANEL_HEIGHT. */
	int PROGRESS_PANEL_HEIGHT  = (int) (TERMINAL_RIGHT_PANEL_HEIGHT*.6);
	
	/** The Constant PROGRESS_PANEL_CIRCLES_WIDTH. */
	int PROGRESS_PANEL_CIRCLES_WIDTH = PROGRESS_PANEL_WIDTH/3 -1;
	
	/** The Constant PROGRESS_PANEL_CIRCLES_HEIGTH. */
	int PROGRESS_PANEL_CIRCLES_HEIGTH = PROGRESS_PANEL_HEIGHT/3 * 2 -1;
	
	/** The Constant PROGRESS_PANEL_LABELS_WIDTH. */
	int PROGRESS_PANEL_LABELS_WIDTH = PROGRESS_PANEL_WIDTH/3 -1;
	
	/** The Constant PROGRESS_PANEL_LABELS_HEIGTH. */
	int PROGRESS_PANEL_LABELS_HEIGTH = PROGRESS_PANEL_HEIGHT/3 -1;
	
	/** The Constant TOOLBAR_BUTTON_WIDTH. */
	int TOOLBAR_BUTTON_WIDTH = MAIN_TOOLBAR_WIDTH -2;
	
	/** The Constant TOOLBAR_BUTTON_HEIGHT. */
	int TOOLBAR_BUTTON_HEIGHT = MAIN_TOOLBAR_HEIGHT/6;
	
	/**
	 * Style.
	 */
	void style(); 
}