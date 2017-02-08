import java.awt.*;
import javax.swing.*;
public interface Styling{
	static final Color TASK_PANEL_TEXT_COLOR = new Color(68, 50, 46);
	static final Color LABEL_PANEL_TEXT_COLOR = new Color(68,50,46);
	static final Color BACKGROUND_COLOR = new Color(251,251,251);//142,175,226);//0,34,64);//0,20,40);//(0,32,66);//(15,39,91);//Color(53,58,66);Color(113,114,106);
	static final Color TASK_PANEL_COLOR = new Color(245,255,114);//64, 30, 0);//(248,255,63);
	static final Color BORDER_COLOR = new Color(241,241,241);
	static final Font FONT = new Font("Arial",Font.PLAIN,20);
	
	public void style(); 
}