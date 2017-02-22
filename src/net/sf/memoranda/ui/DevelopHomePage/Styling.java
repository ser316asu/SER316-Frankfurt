package net.sf.memoranda.ui.DevelopHomePage;
import java.awt.*;
import javax.swing.*;
public interface Styling{

	static final Color LABEL_PANEL_TEXT_COLOR = new Color(255,255,255);
	static final Color BORDER_COLOR = new Color(125,125,125);
	static final Color BACKGROUND_COLOR = new Color(243,243,243);//142,175,226);//0,34,64);//0,20,40);//(0,32,66);//(15,39,91);//Color(53,58,66);Color(113,114,106);

	static final Color TASK_PANEL_TEXT_COLOR = new Color(255,255,255);
    static final Color TASK_PANEL_COLOR = new Color(0,0,0);
    static final Color TASK_PANEL_COLOR_ONHOVER = new Color(125,125,125);
    static final Color TASK_PANEL_BOARDER_COLOR = new Color(125,125,125);
    
    static final Color TIMER_PANEL_COLOR = new Color(0,0,0,255);
    static final Color PROGRESS_PANEL_COLOR = new Color(0,0,0,255);
    
    static final Color PROGRESSBAR_HOURS_COLOR= new Color(50,50,50);
    static final Color PROGRESSBAR_LOC_COLOR= new Color(10,50,50);
    static final Color PROGRESSBAR_DAYS_COLOR= new Color(100,50,50);
    
	static final Font FONT = new Font("Staubach",Font.BOLD,20);
	static final Font TERMINAL_FONT = new Font("Staubach",Font.BOLD,20);
	static final Font TIMER_FONT = new Font("Staubach",Font.BOLD,60);
	static final Font TASK_PANEL_FONT = new Font("Rudiment",Font.BOLD,20);
	static final Font TOOLBAR_FONT = new Font("Rudiment",Font.BOLD,35);
	
	//sizing <a href="https://clipartfest.com/">clipartfest.com</a>
	static final int SCREEN_WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
	static final int SCREEN_HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
	
	static final int MAIN_TOOLBAR_HEIGHT = 300;
	static final int MAIN_TOOLBAR_WIDTH = 50;
	
	static final int TASK_BOARD_WIDTH = SCREEN_WIDTH-MAIN_TOOLBAR_WIDTH- 10;// - MAIN_TOOLBAR_WIDTH-2;
	static final int TASK_BOARD_HEIGHT = ((int) (SCREEN_HEIGHT * .6)) -50;
	
	static final int TASK_PANEL_WIDTH = TASK_BOARD_WIDTH/5 -110;
	static final int TASK_PANEL_HEIGHT = TASK_BOARD_HEIGHT/2 - 100;
	
	static final int TOP_PANEL_WIDTH = TASK_BOARD_WIDTH;// - MAIN_TOOLBAR_WIDTH-2;
    static final int TOP_PANEL_HEIGHT = (int) (SCREEN_HEIGHT * .4)-10;
    
	static final int TERMINAL_PANEL_WIDTH = (int) (TOP_PANEL_WIDTH*.6)-1;
	static final int TERMINAL_PANEL_HEIGHT = (int) (TOP_PANEL_HEIGHT);
	
	static final int TERMINAL_RIGHT_PANEL_WIDTH = TOP_PANEL_WIDTH;
	static final int TERMINAL_RIGHT_PANEL_HEIGHT  = (int) (TOP_PANEL_HEIGHT);
	
	static final int TIMER_PANEL_WIDTH = (int) (TOP_PANEL_WIDTH*.4);
	static final int TIMER_PANEL_HEIGHT  = (int) (TERMINAL_RIGHT_PANEL_HEIGHT*.4);
	
	static final int PROGRESS_PANEL_WIDTH = TIMER_PANEL_WIDTH;
	static final int PROGRESS_PANEL_HEIGHT  = (int) (TERMINAL_RIGHT_PANEL_HEIGHT*.6);
	
	static final int PROGRESS_PANEL_CIRCLES_WIDTH = PROGRESS_PANEL_WIDTH/3 -1;
	static final int PROGRESS_PANEL_CIRCLES_HEIGTH = PROGRESS_PANEL_HEIGHT/3 * 2 -1;
	
	static final int PROGRESS_PANEL_LABELS_WIDTH = PROGRESS_PANEL_WIDTH/3 -1;
	static final int PROGRESS_PANEL_LABELS_HEIGTH = PROGRESS_PANEL_HEIGHT/3 -1;
	
	static final int TOOLBAR_BUTTON_WIDTH = MAIN_TOOLBAR_WIDTH -2;
	static final int TOOLBAR_BUTTON_HEIGHT = MAIN_TOOLBAR_HEIGHT/6;
	
	public void style(); 
}