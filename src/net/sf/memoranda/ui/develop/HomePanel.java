/**************************************************************
 * Copyright (c) 2017 - 2017, Joshua Becker, Alec Shinn,
 * All rights reserved
 * SER316-Frankfurt is a project for ser216, 
 * using agile scrum.
 * Description: resources
 * 
 * Contact: jdbecke3@asu.edu, atshinn@asu.edu
 **************************************************************/
package net.sf.memoranda.ui.develop;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.*;
// TODO: Auto-generated Javadoc

/**
 * The Class HomePanel.
 */
public class HomePanel extends JLabel implements Styling
{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 4595243106140665520L;

	/** The top P. */
    private TopHomePanel top_P;
	
	/** The low P. */
	private LowerHomePanel low_P;
	
	/** The container for toolbar. */
	private JSplitPane container, containerForToolbar;
    
    /** The tasks. */
    private Hashtable<String,TaskCard> tasks;
    
    /** The assets. */
    private LoadAssets assets;
    
    /** The toolbar. */
    private MainToolBar toolbar;
    
    /**
     * Instantiates a new home panel.
     */
    public HomePanel()
    {
    	
        assets = new LoadAssets();
        this.tasks = new Hashtable<String, TaskCard>();
        fillTasks();
    	createComponents();
    	editComponents();
    	addActionListeners();
    	addComponents();
    }
    
    /**
     * Instantiates a new home panel.
     *
     * @param task the task
     */
    public HomePanel(TaskCard task)
    {
    	//this.setIcon(LoadAssets.HOMEPAGE_BACKGROUND);
        assets = new LoadAssets();
        this.tasks = new Hashtable<String, TaskCard>();
        fillTasks();
        createComponents();
        editComponents();
        addActionListeners();
        style();
        addComponents();
    }


    /**
     * Creates the components.
     */
    public void createComponents()
    {
    	top_P = new TopHomePanel(this.tasks.get("task 1"));
    	low_P = new LowerHomePanel(this.top_P, this.tasks);
    	container = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	containerForToolbar = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        this.toolbar = new MainToolBar(low_P);
    }
    
    /**
     * Edits the components.
     */
    public void editComponents()
    {
    	this.setLayout(new GridLayout(1,1,0,0));
    	container.setMaximumSize(new Dimension(Styling.SCREEN_WIDTH,Styling.SCREEN_HEIGHT));
    	container.setMinimumSize(new Dimension(Styling.SCREEN_WIDTH-Styling.MAIN_TOOLBAR_WIDTH-15,Styling.SCREEN_HEIGHT));
    	container.setDividerLocation(Styling.TERMINAL_PANEL_HEIGHT+1);
    	container.setOneTouchExpandable(true);
    	container.setResizeWeight(1);
    	container.setOpaque(false);
    
    	containerForToolbar.setMaximumSize(new Dimension(Styling.SCREEN_WIDTH,Styling.SCREEN_HEIGHT));
    	containerForToolbar.setMinimumSize(new Dimension(Styling.SCREEN_WIDTH-Styling.MAIN_TOOLBAR_WIDTH-15,Styling.SCREEN_HEIGHT));
    	containerForToolbar.setDividerLocation(Styling.MAIN_TOOLBAR_WIDTH);
    	containerForToolbar.setDividerSize(10);
    	containerForToolbar.setForeground(Color.black);
    	containerForToolbar.setBackground(Color.black);
    	containerForToolbar.setOneTouchExpandable(true);
    	containerForToolbar.setResizeWeight(1);
    	containerForToolbar.setOpaque(false);
    	
    	BasicSplitPaneDivider divider = (BasicSplitPaneDivider) containerForToolbar.getComponent(0);
    	divider.setBackground(Color.black);
    	divider.setBorder(null);

    	//this.setIcon(LoadAssets.HOMEPAGE_BACKGROUND);
    }
    
    /**
     * Adds the action listeners.
     */
    public void addActionListeners()
    {

    }
    
    /**
     * Adds the components.
     */
    public void addComponents()
    {
    	container.setTopComponent(this.top_P);
    	container.setBottomComponent(this.low_P);
    	//this.add(new MainToolBar());
    	//this.setLayer(c, layer);
    	//this.add(this.toolbar, new Integer(10));
    	//this.add(toolbar,JLayeredPane.DRAG_LAYER);
    	//this.add(container, JLayeredPane.DRAG_LAYER);
    	containerForToolbar.setLeftComponent(this.toolbar);
    	containerForToolbar.setRightComponent(this.container);
    	
    	this.add(containerForToolbar);
    	this.revalidate();
    }

    /* (non-Javadoc)
     * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
     */
    public void style()
    {
    	this.setPreferredSize(new Dimension(Styling.SCREEN_WIDTH-2,Styling.SCREEN_HEIGHT-2));
    }
    
    /**
     * Fill tasks.
     */
    public void fillTasks()
    {
        TaskCard tmpTask;
        for(int i = 0; i < 10; i++)
        {
            tmpTask = new TaskCard();
            tmpTask.setEstimatedLOC(500+i);
            tmpTask.setEstimatedTime((double)8.5+i);
            tmpTask.setActualLOC(256+i);
            tmpTask.setActualTime(4.5+i);
            tmpTask.setTaskName("task " + i);
            tmpTask.setStartDate(new Date("02/02/2017"));
            tmpTask.setEndDate(new Date("02/20/2017"));
            this.tasks.put(tmpTask.getTaskName(), tmpTask);
        }
        this.tasks.get("task 1").setActive(true);
    }
}