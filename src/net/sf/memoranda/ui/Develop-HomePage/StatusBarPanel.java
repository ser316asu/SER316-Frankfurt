import javax.swing.*;
import java.awt.*;

public class StatusBarPanel extends JPanel implements Styling{
	private TopInnerPanel parent;
	private TaskCard task;
	private Dimension dimension;
	private LayoutManager layout;
	private JProgressBar progress;
	private final double WIDTH_RATIO = 1.0,HEIGHT_RATIO = .5;
	
	public StatusBarPanel(TopInnerPanel parent,TaskCard task){
		this.parent = parent;
		
		this.task = task;

		layout = new BorderLayout();

		this.setLayout(layout);
		
		setSize();

		style();
	}

	public void setSize(){
		Dimension parentDimension = parent.getDimension();
		int width =(int) (parentDimension.getWidth() * WIDTH_RATIO);
		int height = (int) (parentDimension.getHeight() * HEIGHT_RATIO);
		height += 5;

		dimension = new Dimension(width,height);
		//this.setMaximumSize(dimension);
		this.setMinimumSize(dimension); 
	}

	public void style(){
		this.setBackground(Styling.BACKGROUND_COLOR);
		//this.setForeground(Styling.FOREGROUND_COLOR);
		this.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR));
	}
}