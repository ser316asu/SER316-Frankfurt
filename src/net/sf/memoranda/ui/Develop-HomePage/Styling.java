import java.awt.*;
import javax.swing.*;
public interface Styling{
<<<<<<< HEAD
	static final Color LABEL_PANEL_TEXT_COLOR = new Color(125,125,125);
	static final Color TASK_PANEL_COLOR = new Color(125,125,125);
	static final Color BORDER_COLOR = new Color(125,125,125);

	static final Color TASK_PANEL_TEXT_COLOR = new Color(243,243,243);
	static final Color BACKGROUND_COLOR = new Color(243,243,243);//142,175,226);//0,34,64);//0,20,40);//(0,32,66);//(15,39,91);//Color(53,58,66);Color(113,114,106);
=======
	static final Color TASK_PANEL_TEXT_COLOR = new Color(255,255,255);
	static final Color LABEL_PANEL_TEXT_COLOR = new Color(255,255,255);
	static final Color BACKGROUND_COLOR = new Color(57,57,57);//142,175,226);//0,34,64);//0,20,40);//(0,32,66);//(15,39,91);//Color(53,58,66);Color(113,114,106);
	static final Color TASK_PANEL_COLOR = new Color(255,90,9);//64, 30, 0);//(248,255,63);
	static final Color BORDER_COLOR = new Color(241,241,241);
	static final Font FONT = new Font("Arial",Font.PLAIN,20);
>>>>>>> 7de5122e4982eabceb748dbaf557ab15c69f046c
	
	
	static final Font FONT = new Font("Arial",Font.BOLD,20);
	static final Font TIMER_FONT = new Font("Arial",Font.BOLD,60);
	public void style(); 
}