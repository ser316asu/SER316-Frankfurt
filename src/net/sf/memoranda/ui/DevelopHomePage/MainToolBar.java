/**************************************************************
 * Copyright (c) 2017 - 2017, Joshua Becker, All rights reserved
 * SER316-Frankfurt
 */
package net.sf.memoranda.ui.DevelopHomePage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import javax.swing.OverlayLayout;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.LabelUI;

import net.sf.memoranda.ui.JNCalendarPanel;
import net.sf.memoranda.ui.NewTaskWindow;

// TODO: Auto-generated Javadoc
/**
 * The Class MainToolBar. is a tool bar
 * Designed for the left of the frame
 * will contain navigation buttons
 */
public class MainToolBar extends JLabel implements Styling, Observer{
	
	/** The calendar B. and create new task B*/
	private JButton createNewTask_B, calendar_B, home_B;
	
	/** The task board. */
	private LowerHomePanel taskBoard;
	
	/** The task frame. */
	private NewTaskWindow taskFrame;
	
	/** The old height.  and the old width*/
	private int oldWidth = 0, oldHeight = 0; 
	
	/**
	 * Instantiates a new main tool bar.
	 */
	public MainToolBar()
	{
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
	public MainToolBar(LowerHomePanel taskBoard)
	{
		this.taskBoard = taskBoard;
		createComponents();
		editComponents();
		addActionListeners();
		addComponents();
		this.revalidate();
	}
	
	//---------------------Create Actions-----------------------------------
    /**  Action when called will open the 	new task window for creation of tasks. */
    public Action createNewTaskAction = new AbstractAction("") {
    	
	private static final long serialVersionUID = -6751747715539881623L;
    
	public void actionPerformed(ActionEvent e) {
		taskFrame = new NewTaskWindow();
        }
    };
	
    /**  when the action is called it 	will open the calendar view. */
    public Action openCalendarAction = new AbstractAction("") {
    	
		private static final long serialVersionUID = 6131253764559798328L;

		public void actionPerformed(ActionEvent e) {
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
    
    /**  Action when called will open the 	new task window for creation of tasks. */
    public Action homeButtonAction = new AbstractAction("") {
    	
		private static final long serialVersionUID = 48588901517673371L;

		public void actionPerformed(ActionEvent e) {
			//TODO create an observer
        }
    };
    
    //--------------------End Create Actions--------------------------------
	
	/**
	 * Creates the components.
	 */
	private void createComponents() {
		createNewTask_B = new JButton();
		calendar_B = new JButton();
		home_B = new JButton();
		
	}

	/**
	 * Edits the components.
	 */
	private void editComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setIcon(LoadAssets.TERMINAL_IMAGE);
		this.setOpaque(false);
		
		editToolBarButton(createNewTask_B, "Create New Task");
		
		editToolBarButton(calendar_B, "Open Calendar");
		
		editToolBarButton(home_B, "Go To Home");
		//this.setUI(new ButtonUI());
		
	}

	/**
	 * Adds the action listeners.
	 */
	private void addActionListeners() {
		this.createNewTask_B.setAction(createNewTaskAction);
		this.calendar_B.setAction(openCalendarAction);
		this.home_B.setAction(homeButtonAction);
	}

	/**
	 * Adds the components.
	 */
	private void addComponents() {
		this.add(addButtonSpacer());
		
		addButtonLabel(home_B, LoadAssets.TOOLABR_HOME_BUTTON_IMAGE);
		this.add(home_B);
		this.add(addButtonSpacer());
		
		addButtonLabel(createNewTask_B, LoadAssets.TOOLBAR_TASK_BUTTON_IMAGE);
		this.add(createNewTask_B);
		this.add(addButtonSpacer());
		
		addButtonLabel(calendar_B, LoadAssets.TOOLBAR_CALENDAR_BUTTON_IMAGE);
		this.add(calendar_B);
		this.add(addButtonSpacer());
	}
	
	/**
	 * Adds the button label.
	 *
	 * @param button the button
	 * @param ic the ic
	 */
	private void addButtonLabel(JButton button, ImageIcon ic)
	{
		JLabel label = new JLabel();
		
		label.setFont(Styling.TOOLBAR_FONT);
		label.setOpaque(false);
		label.setForeground(Color.WHITE);
		label.setPreferredSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT+1));
		label.setMaximumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT+1));
		label.setMinimumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT+1));
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
	private void editToolBarButton(JButton button, String toolTip)
	{
		button.setFont(Styling.TASK_PANEL_FONT);
		button.setBorderPainted(false);
		//this.createNewTask_B.setBounds(0, 0, 2, 2);
		button.setMargin(new Insets(0,0,0,0));
		button.setToolTipText(toolTip);
		button.setContentAreaFilled(false);
		button.setLayout(new OverlayLayout(button));
		
		button.setPreferredSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT));
		button.setMaximumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT));
		button.setMinimumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT));
	}
	
	/**
	 * Adds the button spacer.
	 *
	 * @return the j label
	 */
	private JLabel addButtonSpacer()
	{
		JLabel label = new JLabel("\n");
		label.setPreferredSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT));
		label.setMaximumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT));
		label.setMinimumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT));
		return label;
		
	}
	
	/**
	 * updates the style of the components.
	 */
	@Override
	public void style() {
		this.setPreferredSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,Styling.MAIN_TOOLBAR_HEIGHT));
		this.setMaximumSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,Styling.MAIN_TOOLBAR_HEIGHT));
		this.setMinimumSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,Styling.MAIN_TOOLBAR_HEIGHT));
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
    public void paint(Graphics g)
    {
    	if(this.oldWidth != this.getWidth() || this.oldHeight != this.getHeight())
    	{
        	Image img = ((ImageIcon) this.getIcon()).getImage();
        	img = img.getScaledInstance(this.getWidth(), this.getHeight(),  java.awt.Image.SCALE_SMOOTH);
        	this.setIcon(new ImageIcon(img));
        	this.oldWidth = this.getWidth();
        	this.oldHeight = this.getHeight();
    	}
    	
    	super.paint(g);
    }

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
