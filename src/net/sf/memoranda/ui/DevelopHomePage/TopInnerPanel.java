/**************************************************************
 * Copyright (c) 2017 - 2017, Alec Shinn, Joshua Becker, All rights reserved
 * SER316-Frankfurt
 * Description:
 */
package net.sf.memoranda.ui.DevelopHomePage;
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

// TODO: Auto-generated Javadoc
/**
 * The Class TopInnerPanel.
 */
public class TopInnerPanel extends JLabel implements Styling{
	
	/** The child 2. */
	private JPanel child1,child2;
	
	/** The dimension. */
	private Dimension dimension;
	
	/** The layout. */
	private LayoutManager layout;
	
	/** The panel count. */
	private static int panelCount = 0;
	
	/**
	 * Instantiates a new top inner panel.
	 */
	public TopInnerPanel(){
		style();
		panelCount++;
	}
	
	/**
	 * Instantiates a new top inner panel.
	 *
	 * @param width the width
	 * @param height the height
	 */
	public TopInnerPanel(int width, int height)
	{
		this.dimension = new Dimension(width, height);
		style();
		panelCount++;
	}
	
	/**
	 * Give children.
	 *
	 * @param child1 the child 1
	 * @param child2 the child 2
	 */
	public void giveChildren(JPanel child1,JPanel child2){
		this.child1 = child1;
		this.child2 = child2;
	}

	/**
	 * Sets the panel layout.
	 *
	 * @param layout the layout
	 * @return the int
	 */
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

	/**
	 * Gets the dimension.
	 *
	 * @return the dimension
	 */
	public Dimension getDimension(){
		return dimension;
	}

	/**
	 * Adds the children.
	 *
	 * @return the int
	 */
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

	/* (non-Javadoc)
	 * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
	 */
	public void style(){
		//this.setBackground(Styling.PROGRESS_PANEL_COLOR);
		//ths.setForeground(Styling.FOREGROUND_COLOR);}
		this.setMinimumSize(dimension);
		this.setPreferredSize(dimension);
		this.setMaximumSize(dimension);
	}	
}