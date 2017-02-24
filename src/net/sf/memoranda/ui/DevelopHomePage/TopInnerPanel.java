package net.sf.memoranda.ui.DevelopHomePage;
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class TopInnerPanel extends JLabel implements Styling{
	private JPanel child1,child2;
	private Dimension dimension;
	private LayoutManager layout;
	private static int panelCount = 0;
	
	public TopInnerPanel(){
		style();
		panelCount++;
	}
	
	public TopInnerPanel(int width, int height)
	{
		this.dimension = new Dimension(width, height);
		style();
		panelCount++;
	}
	
	public void giveChildren(JPanel child1,JPanel child2){
		this.child1 = child1;
		this.child2 = child2;
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

	public void style(){
		//this.setBackground(Styling.PROGRESS_PANEL_COLOR);
		//ths.setForeground(Styling.FOREGROUND_COLOR);}
		this.setMinimumSize(dimension);
		this.setPreferredSize(dimension);
		this.setMaximumSize(dimension);
	}	
}