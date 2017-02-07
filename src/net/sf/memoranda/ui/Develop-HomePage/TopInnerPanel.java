import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
public class TopInnerPanel extends JPanel{
	private TaskCard task;
	private TopHomePanel parent;
	private JPanel child1,child2;
	private Dimension dimension;
	private LayoutManager layout;
	
	public TopInnerPanel(TopHomePanel parent,TaskCard task){
		this.parent = parent;
		this.task = task;
		this.setBorder(BorderFactory.createLineBorder(Color.red));
	}

	public void giveChildren(JPanel child1,JPanel child2){
		this.child1 = child1;
		this.child2 = child2;
	}

	public void setSize(double widthRatio,double heightRatio){
		Dimension parentDimension = parent.getDimension();
		int width = (int) ( (double) parentDimension.getWidth() * widthRatio );
		int height = (int) ( (double) parentDimension.getHeight() );
		dimension = new Dimension(width,height);
		this.setPreferredSize(dimension);
	}

	public int setPanelLayout(LayoutManager layout){
		int success = 0;
		int err = 1;
		if(layout != null){
			this.layout = layout;
			this.setLayout(layout);
			return success;
		}	
		return err;
	}

	public Dimension getDimension(){
		return dimension;
	}
	public int addChildren(){
		int err = 1;
		int success = 0;
		
		if(child1 != null && child2 != null){
			this.add(child1);
			this.add(child2);
			return success;
		}
		else
			return err;
	}


	
}