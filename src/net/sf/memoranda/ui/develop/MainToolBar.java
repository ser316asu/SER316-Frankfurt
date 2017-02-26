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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.OverlayLayout;

import net.sf.memoranda.ui.NewTaskWindow;

/**
 * The Class MainToolBar.
 */
public class MainToolBar extends JLabel implements Styling{
	
	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -4229258425606324012L;

	/** The home B. */
	private JButton createNewTaskB;
	private JButton calendarB;
	private JButton homeB;
	private JButton notifcationB;
	
	/** The task board. */
	@SuppressWarnings("unused")
	private LowerHomePanel taskBoard;
	
	/** The task frame. */
	@SuppressWarnings("unused")
	private NewTaskWindow taskFrame;
	
	/** The old height. */
	private int oldWidth = 0;
	private int oldHeight = 0; 
	
	//---------------------Create Actions-----------------------------------
	/** The create new task action. */
	public Action createNewTaskAction = new AbstractAction("") {
		
	private static final long serialVersionUID = -6751747715539881623L;
	
	public void actionPerformed(ActionEvent event) {
		taskFrame = new NewTaskWindow();
	    }
	};
	
	/** The open calendar action. */
	public Action openCalendarAction = new AbstractAction("") {
		
		private static final long serialVersionUID = 6131253764559798328L;
	
		public void actionPerformed(ActionEvent event) {
			HomePanel.setActivePanel(HomePanel.CALENDAR_PANEL);
			//TODO create an observer
			/*
			 * setValue: changes the state of the current taskCard and
			 * 			 notifies observers (Task Panels)
			 * setChangeVar: changes the state of the new TaskCard and 
			 * 				 returns itself in order to pass to the 
			 * 				 other observers
			 * getChangeVar: is used to make a change in order to modify 
			 * 				 the state of the observing panels
			 */
		
	    }
	};
	
	/** The home button action. */
	public Action homeButtonAction = new AbstractAction("") {
		
		private static final long serialVersionUID = 48588901517673371L;
	
		public void actionPerformed(ActionEvent event) {
			HomePanel.setActivePanel(HomePanel.HOME_PANEL);
	    }
	};
	
	/** The Notification button action. */
	public Action notifcationButtonAction = new AbstractAction("") {
		
		private static final long serialVersionUID = 48588901517673371L;
	
		public void actionPerformed(ActionEvent event) {
			
	    }
	};
	
	//--------------------End Create Actions--------------------------------
	/**
	 * Instantiates a new main tool bar.
	 */
	public MainToolBar(){
		createComponents();
		addActionListeners();
		editComponents();
		addComponents();
		this.revalidate();
	}
	
	/**
	 * Instantiates a new main tool bar.
	 *
	 * @param taskBoard the task board
	 */
	public MainToolBar(LowerHomePanel taskBoard){
		this.taskBoard = taskBoard;
		createComponents();
		editComponents();
		addActionListeners();
		addComponents();
		this.revalidate();
	}
	
	/**
     * Creates the components.
     */
	private void createComponents() {
		createNewTaskB = new JButton();
		calendarB = new JButton();
		homeB = new JButton();
		notifcationB = new JButton();
	}

	/**
	 * Edits the components.
	 */
	private void editComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setIcon(LoadAssets.TERMINAL_IMAGE);
		this.setOpaque(false);
		
		editToolBarButton(createNewTaskB, "Create New Task");
		
		editToolBarButton(calendarB, "Open Calendar");
		
		editToolBarButton(homeB, "Go To Home");
		
		editToolBarButton(notifcationB, "Notifcations");
		//this.setUI(new ButtonUI());
		
	}

	/**
	 * Adds the action listeners.
	 */
	private void addActionListeners() {
		this.createNewTaskB.setAction(createNewTaskAction);
		this.calendarB.setAction(openCalendarAction);
		this.homeB.setAction(homeButtonAction);
		this.notifcationB.setAction(notifcationButtonAction);
	}

	/**
	 * Adds the components.
	 */
	private void addComponents() {
		this.add(addButtonSpacer());
		
		addButtonLabel(homeB, LoadAssets.TOOLABR_HOME_BUTTON_IMAGE);
		this.add(homeB);
		this.add(addButtonSpacer());
		
		addButtonLabel(createNewTaskB, LoadAssets.TOOLBAR_TASK_BUTTON_IMAGE);
		this.add(createNewTaskB);
		this.add(addButtonSpacer());
		
		addButtonLabel(this.notifcationB, LoadAssets.TOOLABR_BELL_BUTTON_IMAGE);
		this.add(this.notifcationB);
		this.add(addButtonSpacer());
		
		addButtonLabel(calendarB, LoadAssets.TOOLBAR_CALENDAR_BUTTON_IMAGE);
		this.add(calendarB);
		this.add(addButtonSpacer());
		
		
	}
	
	/**
	 * Adds the button label.
	 *
	 * @param button the button
	 * @param ic the ic
	 */
	private void addButtonLabel(JButton button, ImageIcon ic){
		JLabel label = new JLabel();
		
		label.setFont(Styling.TOOLBAR_FONT);
		label.setOpaque(false);
		label.setForeground(Color.WHITE);
		label.setPreferredSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,
				Styling.TOOLBAR_BUTTON_HEIGHT+1));
		label.setMaximumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,
				Styling.TOOLBAR_BUTTON_HEIGHT+1));
		label.setMinimumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,
				Styling.TOOLBAR_BUTTON_HEIGHT+1));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setIcon(ic);
		button.add(label);
	}
	
	/**
	 * Edits the tool bar button.
	 *
	 * @param button the button
	 * @param toolTip the tool tip
	 */
	private void editToolBarButton(JButton button, String toolTip){
		button.setFont(Styling.TASK_PANEL_FONT);
		button.setBorderPainted(false);
		//this.createNewTaskB.setBounds(0, 0, 2, 2);
		button.setMargin(new Insets(0,0,0,0));
		button.setToolTipText(toolTip);
		button.setContentAreaFilled(false);
		button.setLayout(new OverlayLayout(button));
		
		button.setPreferredSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,
				Styling.TOOLBAR_BUTTON_HEIGHT));
		button.setMaximumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,
				Styling.TOOLBAR_BUTTON_HEIGHT));
		button.setMinimumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,
				Styling.TOOLBAR_BUTTON_HEIGHT));
	}
	
	/**
	 * Adds the button spacer.
	 *
	 * @return the j label
	 */
	private JLabel addButtonSpacer(){
		JLabel label = new JLabel("\n");
		label.setPreferredSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,
				Styling.TOOLBAR_BUTTON_HEIGHT));
		label.setMaximumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,
				Styling.TOOLBAR_BUTTON_HEIGHT));
		label.setMinimumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,
				Styling.TOOLBAR_BUTTON_HEIGHT));
		return label;
		
	}
	
	/* (non-Javadoc)
	 * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
	 */
	@Override
	public void style() {
		this.setPreferredSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,
				Styling.MAIN_TOOLBAR_HEIGHT));
		this.setMaximumSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,
				Styling.MAIN_TOOLBAR_HEIGHT));
		this.setMinimumSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,
				Styling.MAIN_TOOLBAR_HEIGHT));
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
    public void paint(Graphics graph){
    	if(this.oldWidth != this.getWidth() || this.oldHeight != this.getHeight()){
        	Image img = ((ImageIcon) this.getIcon()).getImage();
        	img = img.getScaledInstance(this.getWidth(), this.getHeight(),
        			java.awt.Image.SCALE_SMOOTH);
        	this.setIcon(new ImageIcon(img));
        	this.oldWidth = this.getWidth();
        	this.oldHeight = this.getHeight();
    	}
    	
    	super.paint(graph);
    }
	
	
}