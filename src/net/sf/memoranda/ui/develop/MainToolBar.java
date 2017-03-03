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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.OverlayLayout;

import net.sf.memoranda.ui.NewTaskWindow;

// TODO: Auto-generated Javadoc
/**
 * The Class MainToolBar.
 */
public class MainToolBar extends JLabel implements Styling{
	
	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -4229258425606324012L;

	/** The home B. */
	private JButton tasksB;
	
	/** The calendar B. */
	private JButton calendarB;
	
	/** The home B. */
	private JButton homeB;
	
	/** The notifcation B. */
	private JButton notifcationB;
	
	/** The files B. */
	private JButton filesB;
	
	/** The notes B. */
	private JButton notesB;
	
	/** The task board. */
	@SuppressWarnings("unused")
	private LowerHomePanel taskBoard;
	
	/** The task frame. */
	@SuppressWarnings("unused")
	private NewTaskWindow taskFrame;
	
	/** The old height. */
	private int oldWidth = 0;
	
	/** The old height. */
	private int oldHeight = 0; 
	
	//---------------------Create Actions-----------------------------------
	/** The create new task action. */
	public Action createNewTaskAction = new AbstractAction("") {
		
	private static final long serialVersionUID = -6751747715539881623L;
	
	public void actionPerformed(ActionEvent event) {
		HomePanel.setActivePanel(HomePanel.TASK_PANEL);
	    }
	};
	
	/** The open calendar action. */
	public Action openCalendarAction = new AbstractAction("") {
		
		private static final long serialVersionUID = 6131253764559798328L;
	
		public void actionPerformed(ActionEvent event) {
			HomePanel.setActivePanel(HomePanel.CALENDAR_PANEL);
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
			HomePanel.setActivePanel(HomePanel.EVENT_PANEL);
	    }
	};
	
	/** The resources button action. */
	public Action resourcesButtonAction = new AbstractAction("") {
		
		private static final long serialVersionUID = 48588901517673371L;
	
		public void actionPerformed(ActionEvent event) {
			HomePanel.setActivePanel(HomePanel.RESOURCES_PANEL);
			System.out.println("FIELS");
	    }
	};
	
	/** The agenda button action. */
	public Action agendaButtonAction = new AbstractAction("") {
		
		private static final long serialVersionUID = 48588901517673371L;
	
		public void actionPerformed(ActionEvent event) {
			HomePanel.setActivePanel(HomePanel.AGENDA_PANEL);
	    }
	};
	
	/** The notes button action. */
	public Action notesButtonAction = new AbstractAction("") {
		
		private static final long serialVersionUID = 48588901517673371L;
	
		public void actionPerformed(ActionEvent event) {
			HomePanel.setActivePanel(HomePanel.NOTES_PANEL);
	    }
	};
	
	/** The files button action. */
	public Action filesButtonAction = new AbstractAction("") {
		
		private static final long serialVersionUID = 48588901517673371L;
	
		public void actionPerformed(ActionEvent event) {
			HomePanel.setActivePanel(HomePanel.RESOURCES_PANEL);
	    }
	};
	//--------------------End Create Actions--------------------------------
	/**
	 * Instantiates a new main tool bar.
	 */
	public MainToolBar(){
		createComponents();
		addActionListeners();
		addRollOverEffects();
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
		addRollOverEffects();
		addComponents();
		this.revalidate();
	}
	
	/**
     * Creates the components.
     */
	private void createComponents() {
		tasksB = new JButton();
		calendarB = new JButton();
		homeB = new JButton();
		notifcationB = new JButton();
		filesB = new JButton();
		notesB = new JButton();
	}

	/**
	 * Edits the components.
	 */
	private void editComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setIcon(LoadAssets.TERMINAL_IMAGE);
		this.setOpaque(false);
		
		editToolBarButton(tasksB, "Tasks");
		
		editToolBarButton(calendarB, "Open Calendar");
		
		editToolBarButton(homeB, "Go To Home");
		
		editToolBarButton(notifcationB, "Notifcations");
		
		editToolBarButton(filesB, "Resources");
		
		editToolBarButton(notesB, "Notes");
		
	}

	/**
	 * Adds the action listeners.
	 */
	private void addActionListeners() {
		this.tasksB.setAction(createNewTaskAction);
		this.calendarB.setAction(openCalendarAction);
		this.homeB.setAction(homeButtonAction);
		this.notifcationB.setAction(notifcationButtonAction);
		this.filesB.setAction(resourcesButtonAction);
		this.notesB.setAction(notesButtonAction);
	}
	
	/**
	 * Adds the roll-over effects
	 */
	private void addRollOverEffects() {
		this.tasksB.setRolloverEnabled(true);
		this.tasksB.setRolloverIcon(LoadAssets.TOOLBAR_TASK_HOVER_BUTTON_IMAGE);
		
		this.calendarB.setRolloverEnabled(true);
		this.calendarB.setRolloverIcon(LoadAssets.TOOLBAR_BELL_HOVER_BUTTON_IMAGE);
		
		this.homeB.setRolloverEnabled(true);
		this.homeB.setRolloverIcon(LoadAssets.TOOLBAR_HOME_HOVER_BUTTON_IMAGE);
		
		this.notifcationB.setRolloverEnabled(true);
		this.notifcationB.setRolloverIcon(LoadAssets.TOOLBAR_BELL_HOVER_BUTTON_IMAGE);
		
		this.filesB.setRolloverEnabled(true);
		this.filesB.setRolloverIcon(LoadAssets.TOOLBAR_FILE_HOVER_BUTTON_IMAGE);
		
		this.notesB.setRolloverEnabled(true);
		this.notesB.setRolloverIcon(LoadAssets.TOOLBAR_CALENDAR_HOVER_BUTTON_IMAGE);
	}

	/**
	 * Adds the components.
	 */
	private void addComponents() {
		this.add(addButtonSpacer());
		
		addButtonLabel(homeB, LoadAssets.TOOLBAR_HOME_BUTTON_IMAGE);
		this.add(homeB);
		this.add(addButtonSpacer());
		
		addButtonLabel(tasksB, LoadAssets.TOOLBAR_TASK_BUTTON_IMAGE);
		this.add(tasksB);
		this.add(addButtonSpacer());
		
		addButtonLabel(this.notifcationB, LoadAssets.TOOLBAR_BELL_BUTTON_IMAGE);
		this.add(this.notifcationB);
		this.add(addButtonSpacer());
		
		addButtonLabel(this.filesB, LoadAssets.TOOLBAR_FILE_BUTTON_IMAGE);
		this.add(this.filesB);
		this.add(addButtonSpacer());
		
		addButtonLabel(notesB, LoadAssets.TOOLBAR_NOTES_BUTTON_IMAGE);
		this.add(notesB);
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
		//this.tasksB.setBounds(0, 0, 2, 2);
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
		this.setMaximumSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH+10,
				Styling.MAIN_TOOLBAR_HEIGHT));
		this.setMinimumSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH-10,
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
