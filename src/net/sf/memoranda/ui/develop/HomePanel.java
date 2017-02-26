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

import net.sf.memoranda.ui.JNCalendarPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.*;

/**
 * The Class HomePanel.
 */
public class HomePanel extends JLabel implements Styling {
    
	public static final int HOME_PANEL = 0;
	public static final int CALENDAR_PANEL = 1;
	
	private static final JNCalendarPanel CALENDAR_JNPANEL = 
			new JNCalendarPanel();
	
	private static final JSplitPane OUTER_SPLITPANE = 
			new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	
	private static final JSplitPane RIGHT_SPLITPANE = 
			new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	
	private static Component activeComponent;
	/**
	 * Generated SerialVersion UID.
	 */
	private static final long serialVersionUID = 4595243106140665520L;

	/** The top P. */
	private TopHomePanel topP;
	
	/** The low P. */
	private LowerHomePanel lowP;
	
	private JLabel rightSidePanel;
    
    /** The tasks. */
    private Hashtable<String,TaskCard> tasks;
    
    /** The assets. */
    @SuppressWarnings("unused")
	private LoadAssets assets;
    
    /** The toolbar. */
    private MainToolBar toolbar;
    
    /**
     * Instantiates a new home panel.
     */
    public HomePanel(){
    	activeComponent = RIGHT_SPLITPANE;
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
    public HomePanel(TaskCard task) {
    	//this.setIcon(LoadAssets.HOMEPAGE_BACKGROUND);
    	activeComponent = RIGHT_SPLITPANE;
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
    public void createComponents() {
    	topP = new TopHomePanel(this.tasks.get("task 1"));
    	lowP = new LowerHomePanel(this.topP, this.tasks);
        this.toolbar = new MainToolBar(lowP);
        this.rightSidePanel = new JLabel();
    }
    
    /**
     * Edits the components.
     */
    public void editComponents(){
    	this.setLayout(new OverlayLayout(this));
    	this.rightSidePanel.setIcon(LoadAssets.TERMINAL_IMAGE);
    	
    	setRightSideSize(this.rightSidePanel, 0, 0);
    	
    	this.rightSidePanel.setOpaque(false);
    	this.rightSidePanel.setLayout(new OverlayLayout(this.rightSidePanel));
    	//this.rightSidePanel.setBorder(BorderFactory.createLineBorder(Color.green));
    	
    	RIGHT_SPLITPANE.setDividerLocation(Styling.TERMINAL_PANEL_HEIGHT+1);
    	RIGHT_SPLITPANE.setOneTouchExpandable(true);
    	RIGHT_SPLITPANE.setResizeWeight(1);
    	RIGHT_SPLITPANE.setOpaque(false);
    	//RIGHT_SPLITPANE.setBorder(BorderFactory.createLineBorder(Color.blue));
    	
    	OUTER_SPLITPANE.setDividerLocation(Styling.MAIN_TOOLBAR_WIDTH);
    	OUTER_SPLITPANE.setDividerSize(10);
    	OUTER_SPLITPANE.setForeground(Color.black);
    	OUTER_SPLITPANE.setBackground(Color.black);
    	OUTER_SPLITPANE.setOneTouchExpandable(true);
    	OUTER_SPLITPANE.setResizeWeight(1);
    	OUTER_SPLITPANE.setOpaque(false);
    	
    	BasicSplitPaneDivider divider = (BasicSplitPaneDivider) OUTER_SPLITPANE.getComponent(0);
    	divider.setBackground(Color.black);
    	divider.setBorder(null);

    	HomePanel.CALENDAR_JNPANEL.setVisible(true);
    }
    
    /**
     * Adds the action listeners.
     */
    public void addActionListeners() {

    }
    
    /**
     * Adds the components.
     */
    public void addComponents() {
    	RIGHT_SPLITPANE.setTopComponent(this.topP);
    	RIGHT_SPLITPANE.setBottomComponent(this.lowP);
    	
    	this.rightSidePanel.add(HomePanel.RIGHT_SPLITPANE);
    	//this.rightSidePanel.add(HomePanel.CALENDAR_JNPANEL);
    	
    	OUTER_SPLITPANE.setLeftComponent(this.toolbar);
    	OUTER_SPLITPANE.setRightComponent(this.rightSidePanel);
    	
    	this.add(OUTER_SPLITPANE);
    	this.revalidate();
    }

    /* (non-Javadoc)
     * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
     */
    public void style(){
    	this.setPreferredSize(new Dimension(Styling.SCREEN_WIDTH-2,Styling.SCREEN_HEIGHT-2));
    }
    
    private void setRightSideSize(Component comp, int widthMargin, int heightMargin){
    	comp.setMaximumSize(new Dimension(
    			Styling.SCREEN_WIDTH-widthMargin,
    			Styling.SCREEN_HEIGHT-heightMargin));
    	
    	comp.setMinimumSize(new Dimension(
    			Styling.SCREEN_WIDTH-Styling.MAIN_TOOLBAR_WIDTH,
    			Styling.SCREEN_HEIGHT));
    	
    	comp.setPreferredSize(new Dimension(
    			Styling.SCREEN_WIDTH-Styling.MAIN_TOOLBAR_WIDTH-widthMargin, 
    			Styling.SCREEN_HEIGHT-heightMargin));
    	
    	comp.setSize(new Dimension(
    			Styling.SCREEN_WIDTH-Styling.MAIN_TOOLBAR_WIDTH-widthMargin, 
    			Styling.SCREEN_HEIGHT-heightMargin));
    	
    }
    /**
     * Fill tasks.
     */
    private void fillTasks(){
        TaskCard tmpTask;
        for(int i = 0; i < 10; i++) {
            tmpTask = new TaskCard();
            tmpTask.setEstimatedLOC(500+i);
            tmpTask.setEstimatedTime((double)8.5+i);
            tmpTask.setActualLOC(256+i);
            tmpTask.setActualTime(4.5+i);
            tmpTask.setTaskName("task " + i);
            tmpTask.setStartDate(new Date());
            tmpTask.setEndDate(new Date());
            this.tasks.put(tmpTask.getTaskName(), tmpTask);
        }
        this.tasks.get("task 1").setActive(true);
    }
    
    public static void setActivePanel(int activePanel){
    	switch(activePanel){
    		case HomePanel.HOME_PANEL:
    			activeComponent.setVisible(false);
    			RIGHT_SPLITPANE.setVisible(true);
    			activeComponent = RIGHT_SPLITPANE;
    			break;
    		case HomePanel.CALENDAR_PANEL:
    			activeComponent.setVisible(false);
    			CALENDAR_JNPANEL.setVisible(true);
    			activeComponent = CALENDAR_JNPANEL;
    			break;
		default:
				activeComponent.setVisible(false);
				RIGHT_SPLITPANE.setVisible(true);
				activeComponent = RIGHT_SPLITPANE;
				break;	
    	}
    }
}