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
import javax.swing.*;
/**
 * The Class TopInnerPanel.
 */
public class TopInnerPanel extends JLabel implements Styling{
	
	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 8276692589852376728L;

	/** The child 2. */
	private JPanel child1;
	
	private JPanel child2;
	
	/**
	 * Instantiates a new top inner panel.
	 */
	public TopInnerPanel(){
		style();
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
		}else{
			return err;
		}
			
	}

	/* (non-Javadoc)
	 * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
	 */
	public void style(){
	}	
}