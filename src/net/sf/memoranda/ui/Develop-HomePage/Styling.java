import java.awt.*;
import javax.swing.*;
public interface Styling{
	static final Color FOREGROUND_COLOR = Color.white;
	static final Color BACKGROUND_COLOR = new Color(53,58,66);//Color(113,114,106);
	static final Color TASK_PANEL_COLOR = new Color(248,255,63);
	static final Font FONT = new Font("Symbol",Font.BOLD,14);
	
	public void style(); 
}