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
import java.awt.*; 

// TODO: Auto-generated Javadoc
/**
 * The Class TopHomePanel.
 */
public class TopHomePanel extends JSplitPane implements Styling{
	
    /** The Constant LEFT_LABEL_PANEL. */
    public final static int LEFT_LABEL_PANEL = 0;
    
    /** The Constant RIGHT_LABEL_PANEL. */
    public final static int RIGHT_LABEL_PANEL = 1;
    /**
	 * Generated serial version UID.
	 */
    private static final long serialVersionUID = -3435226563742560282L;
    
    /** The right child. */
    private TopInnerPanel leftChild;
    
    /** The right child. */
    private TopInnerPanel rightChild;
    
    /** The right labels. */
    private TopLabelPanel leftLabels;
    
    /** The right labels. */
    private TopLabelPanel rightLabels;
    
    /** The container for right. */
    private JSplitPane containerForRight;
    
    /** The status bar top. */
    private StatusBarPanel statusBarTop;
    
    /** The timer panel. */
    private TimerPanel timerPanel;
    
    /** The task. */
    private TaskCard task;
    
    /** The old height. */
    private int oldWidth;
    
    /** The old height. */
    private int oldHeight;
    


    /**
     * Instantiates a new top home panel.
     *
     * @param task the task
     */
    public TopHomePanel(TaskCard task){
        this.task = task;
        createChildPanels();
        style();
        addPanels();  


    }

    /**
     * Creates the child panels.
     */
    public void createChildPanels(){
        leftLabels = new TopLabelPanel(TopHomePanel.LEFT_LABEL_PANEL);
        rightLabels = new TopLabelPanel(TopHomePanel.RIGHT_LABEL_PANEL);

        statusBarTop = new StatusBarPanel(rightChild,task);
        timerPanel = new TimerPanel(rightChild,task);
    	containerForRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	
        leftChild = new TopInnerPanel();
        rightChild = new TopInnerPanel();

        task.addObserver(leftLabels);
        task.addObserver(rightLabels);
        task.addObserver(statusBarTop);
    }

    /**
     * Adds the panels.
     */
    public void addPanels(){
        leftChild.add(leftLabels, BorderLayout.EAST);
        leftChild.add(rightLabels,BorderLayout.WEST);

        containerForRight.setTopComponent(statusBarTop);
        containerForRight.setBottomComponent(timerPanel);
        
        rightChild.add(containerForRight, BorderLayout.EAST);

        this.setLeftComponent(leftChild);
        this.setRightComponent(rightChild);
    }

    /* (non-Javadoc)
     * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
     */
    public void style(){
        this.setBackground(Styling.BACKGROUND_COLOR);
        this.setOpaque(false);
        this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        this.setDividerLocation(Styling.TERMINAL_PANEL_WIDTH);
        this.setDividerSize(10);
        this.setOneTouchExpandable(true);
        //this.setResizeWeight(1);
        
    	containerForRight.setOpaque(false);
    	containerForRight.setPreferredSize(new Dimension(
    			Styling.PROGRESS_PANEL_WIDTH,
    			Styling.TOP_PANEL_HEIGHT-1));
    	containerForRight.setMaximumSize(new Dimension(
    			Styling.PROGRESS_PANEL_WIDTH,
    			Styling.TOP_PANEL_HEIGHT-1));
    	
    	containerForRight.setDividerLocation(Styling.PROGRESS_PANEL_HEIGHT);
    	containerForRight.setDividerSize(10);
    	containerForRight.setOneTouchExpandable(true);
    	//containerForRight.setResizeWeight(1);
    	containerForRight.setOpaque(false);
    	
    	
        leftChild.setLayout(new BoxLayout(leftChild, BoxLayout.X_AXIS));
        rightChild.setLayout(new BorderLayout(0,0));
        rightChild.setBackground(Styling.TIMER_PANEL_COLOR);
        }
    
    /**
     * Adds the observers.
     *
     * @param tc the tc
     */
    public void addObservers(TaskCard tc){
    	tc.addObserver(leftLabels);
    	tc.addObserver(rightLabels);
    	tc.addObserver(statusBarTop);
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics graph){
    	/*if(this.oldWidth != this.getWidth() || this.oldHeight != this.getHeight()){
        	Image img = ((ImageIcon) this.getIcon()).getImage();
        	img = img.getScaledInstance(this.getWidth(), this.getHeight(),
        			java.awt.Image.SCALE_SMOOTH);
        	this.setIcon(new ImageIcon(img));
        	this.oldWidth = this.getWidth();
        	this.oldHeight = this.getHeight();
    	}*/
    	
    	super.paint(graph);
    }
}
