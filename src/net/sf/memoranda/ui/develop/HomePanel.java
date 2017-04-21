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

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.Task;
import net.sf.memoranda.TaskImpl;
import net.sf.memoranda.ui.DailyItemsPanel;
import net.sf.memoranda.ui.JNCalendarPanel;
import net.sf.memoranda.ui.ResourcesPanel;
import net.sf.memoranda.util.Context;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.*;

/**
 * The Class HomePanel.
 */
public class HomePanel extends JLabel implements Styling {
    
	/** The Constant HOME_PANEL. */
	public static final int HOME_PANEL = 0;
	
	/** The Constant CALENDAR_PANEL. */
	public static final int CALENDAR_PANEL = 1;
	
	/** The Constant TASK_PANEL. */
	public static final int TASK_PANEL = 2;
	
	/** The Constant ALARM. */
	public static final int ALARM = 3;
	
	/** The Constant RESOURCES_PANEL. */
	public static final int RESOURCES_PANEL = 4;
	
	/** The Constant AGENDA_PANEL. */
	public static final int AGENDA_PANEL = 5;
	
	/** The Constant NOTES_PANEL. */
	public static final int NOTES_PANEL = 6;
	
	/** The Constant EVENT_PANEL. */
	public static final int EVENT_PANEL = 7;
	
	public static JButton currentB;
	
	/** The Constant CALENDAR_JNPANEL. */
	private static final JNCalendarPanel CALENDAR_JNPANEL = 
			JNCalendarPanel.getInstance();
	
	/** The Constant ITEMS_PANEL. */
	private static final DailyItemsPanel ITEMS_PANEL =
			new DailyItemsPanel();
	
	/** The Constant FILES_PANEL. */
	private static final ResourcesPanel FILES_PANEL =
			new ResourcesPanel();
	
	/** The Constant OUTER_SPLITPANE. */
	private static final JSplitPane OUTER_SPLITPANE = 
			new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	
	/** The Constant HOME_SPLITPANE. */
	private static final JSplitPane HOME_SPLITPANE = 
			new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	
	/** The Constant CONTENT_CARDLAYOUT. */
	private static final CardLayout  CONTENT_CARDLAYOUT = 
			new CardLayout();
	
	/** The Constant CONTENT_PANEL. */
	private static final JPanel CONTENT_PANEL =
			new JPanel();
	/**
	 * Generated SerialVersion UID.
	 */
	private static final long serialVersionUID = 4595243106140665520L;

	/** The top P. */
	private static final TopHomePanel TOP_PANEL = new TopHomePanel();
	
	/** The low P. */
	private static final LowerHomePanel LOWER_PANEL = new LowerHomePanel(TOP_PANEL);
    
    /** The assets. */
    @SuppressWarnings("unused")
	private LoadAssets assets;
    
    /** The toolbar. */
    private MainToolBar toolbar;
    
    /**
     * Instantiates a new home panel.
     *
     * @param task the task
     */
    public HomePanel() {
    	assets = new LoadAssets();
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
        this.toolbar = new MainToolBar(LOWER_PANEL);
    }
    
    /**
     * Edits the components.
     */
    public void editComponents(){
    	this.setLayout(new OverlayLayout(this));
    	this.setIcon(LoadAssets.TERMINAL_IMAGE);
    	
    	setRightSideSize(HomePanel.CONTENT_PANEL, 0, 0);
    	
    	HomePanel.CONTENT_PANEL.setOpaque(false);
    	HomePanel.CONTENT_PANEL.setLayout(CONTENT_CARDLAYOUT);
    	//this.CONTENT_PANEL.setBorder(BorderFactory.createLineBorder(Color.green));
    	
    	HomePanel.HOME_SPLITPANE.setDividerLocation(Styling.TERMINAL_PANEL_HEIGHT+1);
    	HomePanel.HOME_SPLITPANE.setOneTouchExpandable(true);
    	//HomePanel.HOME_SPLITPANE.setResizeWeight(1);
    	HomePanel.HOME_SPLITPANE.setOpaque(false);
    	//HOME_SPLITPANE.setBorder(BorderFactory.createLineBorder(Color.blue));
    	
    	HomePanel.OUTER_SPLITPANE.setDividerLocation(Styling.MAIN_TOOLBAR_WIDTH);
    	HomePanel.OUTER_SPLITPANE.setDividerSize(10);
    	HomePanel.OUTER_SPLITPANE.setForeground(Color.black);
    	HomePanel.OUTER_SPLITPANE.setBackground(Color.black);
    	HomePanel.OUTER_SPLITPANE.setOneTouchExpandable(true);
    	//HomePanel.OUTER_SPLITPANE.setResizeWeight(1);
    	HomePanel.OUTER_SPLITPANE.setOpaque(false);
    	
    	BasicSplitPaneDivider divider = (BasicSplitPaneDivider) OUTER_SPLITPANE.getComponent(0);
    	divider.setBackground(Color.black);
    	divider.setBorder(null);
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
    	HOME_SPLITPANE.setTopComponent(TOP_PANEL);
    	HOME_SPLITPANE.setBottomComponent(LOWER_PANEL);
    	
    	CONTENT_PANEL.add(HomePanel.HOME_SPLITPANE, "HOME");
    	CONTENT_PANEL.add(HomePanel.CALENDAR_JNPANEL, "CALENDAR");
    	CONTENT_PANEL.add(HomePanel.ITEMS_PANEL, "ITEMS");
    	CONTENT_PANEL.add(HomePanel.FILES_PANEL, "FILES");
    	
    	//HomePanel.CONTENT_CARDLAYOUT.addLayoutComponent(HomePanel.HOME_SPLITPANE, "HOME");
    	//HomePanel.CONTENT_CARDLAYOUT.addLayoutComponent(HomePanel.CALENDAR_JNPANEL, "CALENDAR");
    	
    	OUTER_SPLITPANE.setLeftComponent(this.toolbar);
    	OUTER_SPLITPANE.setRightComponent(HomePanel.CONTENT_PANEL);
    	
    	this.add(OUTER_SPLITPANE);
    	
    	setActivePanel(HomePanel.HOME_PANEL);
    	
    	this.revalidate();
    }

    /* (non-Javadoc)
     * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
     */
    public void style(){
    	this.setPreferredSize(new Dimension(Styling.SCREEN_WIDTH-2,Styling.SCREEN_HEIGHT-2));
    }
    
    /**
     * Sets the right side size.
     *
     * @param comp the comp
     * @param widthMargin the width margin
     * @param heightMargin the height margin
     */
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
     * Sets the active panel.
     *
     * @param activePanel the new active panel
     */
    public static void setActivePanel(int activePanel){
    	switch(activePanel){
    		case HomePanel.HOME_PANEL:
    			TOP_PANEL.movedTo();
    			CONTENT_CARDLAYOUT.show(HomePanel.CONTENT_PANEL, "HOME");
    			Context.put("CURRENT_PANEL", "HOME");
    			break;
    		case HomePanel.CALENDAR_PANEL:
    			CONTENT_CARDLAYOUT.show(HomePanel.CONTENT_PANEL, "CALENDAR");
    			Context.put("CURRENT_PANEL", "CALENDAR" );
    			break;
    		case HomePanel.TASK_PANEL:
    			CONTENT_CARDLAYOUT.show(HomePanel.CONTENT_PANEL, "ITEMS");
    			ITEMS_PANEL.selectPanel("TASKS");
    			//activeComponent = TASK_
    			Context.put("CURRENT_PANEL", "TASKS");
    			break;
    		case HomePanel.AGENDA_PANEL:
    			HomePanel.CONTENT_CARDLAYOUT.show(HomePanel.CONTENT_PANEL, "ITEMS");
    			ITEMS_PANEL.selectPanel("AGENDA");
    			Context.put("CURRENT_PANEL", "AGENDA");
    			break;
    		case HomePanel.NOTES_PANEL:
    			HomePanel.CONTENT_CARDLAYOUT.show(HomePanel.CONTENT_PANEL, "ITEMS");
    			ITEMS_PANEL.selectPanel("NOTES");
    			Context.put("CURRENT_PANEL", "NOTES");
    			break;
    		case HomePanel.EVENT_PANEL:
    			HomePanel.CONTENT_CARDLAYOUT.show(HomePanel.CONTENT_PANEL, "ITEMS");
    			ITEMS_PANEL.selectPanel("EVENTS");
    			Context.put("CURRENT_PANEL", "EVENTS");
    			break;
    		case HomePanel.RESOURCES_PANEL:
    			HomePanel.CONTENT_CARDLAYOUT.show(HomePanel.CONTENT_PANEL, "FILES");
    			Context.put("CURRENT_PANEL", "FILES");
    			break;
		default:
				CONTENT_CARDLAYOUT.show(HomePanel.CONTENT_PANEL, "HOME");
				
				Context.put("CURRENT_PANEL", "HOME");
				break;	
    	}
    }
}