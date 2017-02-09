import java.awt.*;
import javax.swing.*;
public interface Styling{

	static final Color LABEL_PANEL_TEXT_COLOR = new Color(125,125,125);
	static final Color BORDER_COLOR = new Color(125,125,125);
	static final Color BACKGROUND_COLOR = new Color(243,243,243);//142,175,226);//0,34,64);//0,20,40);//(0,32,66);//(15,39,91);//Color(53,58,66);Color(113,114,106);

	static final Color TASK_PANEL_TEXT_COLOR = new Color(255,255,255);
    static final Color TASK_PANEL_COLOR = new Color(125,125,125);
    static final Color TASK_PANEL_COLOR_ONHOVER = new Color(130,130,130);
	
	static final Font FONT = new Font("Arial",Font.BOLD,20);
	static final Font TIMER_FONT = new Font("Arial",Font.BOLD,60);
	
	public void style(); 
}