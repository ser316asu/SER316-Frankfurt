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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import net.sf.memoranda.Task;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusBarPanel.
 */
public class StatusBarPanel extends JLabel implements Styling, Observer{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 48641522633589340L;
	
	/** The progress labels panel. */
	private JLabel progressPanel, circlesPanel, progressLabelsPanel;
	
	/** The task. */
	private Task task;
	
	/** The progress dimension. */
	@SuppressWarnings("unused")
	private Dimension dimension,progressDimension;
	
	/** The spacer. */
	private JLabel timeStatus,locStatus,dayStatus;
	
	/** The progress days. */
	private JProgressBar progressTime,progressLoc,progressOverall;
	
	/** The open. */
	private JButton open;
	
	/**
	 * Instantiates a new status bar panel.
	 *
	 * @param parent the parent
	 * @param task the task
	 */
	public StatusBarPanel(TopInnerPanel parent){
		this.dimension = new Dimension(Styling.PROGRESS_PANEL_WIDTH,Styling.PROGRESS_PANEL_HEIGHT);
		this.task = null;
		
		buildComponents();

		editComponents();

		style();

		addActionListeners();

		addComponents();
	}
	/**
	 * Instantiates a new status bar panel.
	 *
	 * @param parent the parent
	 * @param task the task
	 */
	public StatusBarPanel(TopInnerPanel parent,Task task){
		this.dimension = new Dimension(Styling.PROGRESS_PANEL_WIDTH,Styling.PROGRESS_PANEL_HEIGHT);
		
		buildComponents();

		editComponents();

		style();

		addActionListeners();

		addComponents();
	}

	/**
	 * Builds the components.
	 */
	public void buildComponents(){
		progressTime = new JProgressBar(0,100);
		progressOverall = new JProgressBar(0,100);
		progressLoc = new JProgressBar(0,100);
		
		progressTime.setUI(new ProgressCircleUI());
		progressLoc.setUI(new ProgressCircleUI());
		progressOverall.setUI(new ProgressCircleUI());
	
		//progressTime.setUI(new ProgressCircleUI());
		circlesPanel = new JLabel();
		this.progressLabelsPanel = new JLabel();

		open = new JButton();
		
		dayStatus = new JLabel("Total Progress");
		locStatus = new JLabel("Progress in LOC");
		timeStatus = new JLabel("Progress in Hours");
		
		progressTime = ProgressCircleUI.makeUI(Styling.PROGRESSBAR_HOURS_COLOR);
		progressLoc = ProgressCircleUI.makeUI(Styling.PROGRESSBAR_LOC_COLOR);
		progressOverall = ProgressCircleUI.makeUI(Styling.PROGRESSBAR_DAYS_COLOR);

		progressPanel = new JLabel();

	}

	/**
	 * Edits the components.
	 */
	public void editComponents(){
		this.setPreferredSize(this.dimension);
		this.setMinimumSize(this.dimension);
		this.setMaximumSize(this.dimension);

		//progressDimension = new Dimension(160,(int)((parent.getDimension().getHeight() / 6) - 5));
		progressTime.setValue(0);
		progressOverall.setValue(0);
		progressLoc.setValue(0);
		
		progressTime.setStringPainted(true);
		progressOverall.setStringPainted(true);
		progressLoc.setStringPainted(true);

		open.setText("Open Task File");


		this.circlesPanel.setLayout(new GridLayout(1,3,0,0));
		this.progressLabelsPanel.setLayout(new BoxLayout(this.progressLabelsPanel,BoxLayout.X_AXIS));
		this.setLayout(new GridLayout(1,1,0,0));
		progressPanel.setLayout(new BoxLayout(progressPanel,BoxLayout.Y_AXIS));
		
		this.dayStatus.setHorizontalAlignment(CENTER);
		this.locStatus.setHorizontalAlignment(CENTER);
		this.timeStatus.setHorizontalAlignment(CENTER);
		
		//this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}

	/**
	 * Adds the action listeners.
	 */
	public void addActionListeners(){}

	/**
	 * Adds the components.
	 */
	public void addComponents(){
		//progressPanel.add(dayStac);tus);
		this.circlesPanel.add(progressTime);

		//progressPanel.add(timeStatus);
		this.circlesPanel.add(progressOverall);

		//progressPanel.add(locStatus);
		this.circlesPanel.add(progressLoc);
		
		JLabel label = new JLabel("Hello");
		label.setLayout(new FlowLayout());
		this.progressLabelsPanel.add(this.timeStatus);
		this.progressLabelsPanel.add(this.locStatus);
		this.progressLabelsPanel.add(this.dayStatus);
		
		this.progressPanel.add(this.circlesPanel);
		this.progressPanel.add(this.progressLabelsPanel);
		//this.progressPanel.add(new JLabel("Some Text"));
		

		//buttonPanel.add(open);

		this.add(progressPanel);
		//this.add(, constraints);
		//this.add(buttonPanel,BorderLayout.SOUTH);
	}

	/* (non-Javadoc)
	 * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
	 */
	public void style(){
		//this.setBackground(Styling.BACKGROUND_COLOR);
		//progressPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		
		//this.circlesPanel.setBorder(BorderFactory.createLineBorder(Color.green));
		//this.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR));

		//progressOverall.setBackground(Styling.TASK_PANEL_TEXT_COLOR);
		//progressOverall.setForeground(Styling.TASK_PANEL_COLOR);
		progressOverall.setFont(Styling.TERMINAL_FONT);

		//progressLoc.setBackground(Styling.TASK_PANEL_TEXT_COLOR);
		//progressLoc.setForeground(Styling.TASK_PANEL_COLOR);
		progressLoc.setFont(Styling.TERMINAL_FONT);

		//progressTime.setBackground(Styling.TASK_PANEL_TEXT_COLOR);
		//progressTime.setForeground(Styling.TASK_PANEL_COLOR);
		progressTime.setFont(Styling.TERMINAL_FONT);

		locStatus.setForeground(Styling.LABEL_PANEL_TEXT_COLOR);
		locStatus.setFont(Styling.TERMINAL_FONT);

		dayStatus.setForeground(Styling.LABEL_PANEL_TEXT_COLOR);
		dayStatus.setFont(Styling.TERMINAL_FONT);

		timeStatus.setForeground(Styling.LABEL_PANEL_TEXT_COLOR);
		timeStatus.setFont(Styling.TERMINAL_FONT);

		open.setBackground(Styling.TASK_PANEL_COLOR);
		open.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
		open.setFont(Styling.FONT);
		
		//this.circlesPanel.setMaximumSize(new Dimension(Styling.PROGRESS_PANEL_CIRCLES_WIDTH,Styling.PROGRESS_PANEL_CIRCLES_HEIGTH));
		//this.progressLabelsPanel.setMaximumSize(new Dimension(Styling.PROGRESS_PANEL_LABELS_WIDTH,Styling.PROGRESS_PANEL_LABELS_HEIGTH));
		//this.circlesPanel.setPreferredSize(new Dimension(Styling.PROGRESS_PANEL_CIRCLES_WIDTH,Styling.PROGRESS_PANEL_CIRCLES_HEIGTH));
		this.progressLabelsPanel.setPreferredSize(new Dimension(Styling.PROGRESS_PANEL_LABELS_WIDTH,Styling.PROGRESS_PANEL_LABELS_HEIGTH));
		this.progressLabelsPanel.setMaximumSize(new Dimension(Styling.PROGRESS_PANEL_WIDTH,Styling.PROGRESS_PANEL_LABELS_HEIGTH));
		this.circlesPanel.setPreferredSize(new Dimension(Styling.PROGRESS_PANEL_WIDTH,Styling.PROGRESS_PANEL_CIRCLES_HEIGTH));
		this.circlesPanel.setMaximumSize(new Dimension(Styling.PROGRESS_PANEL_WIDTH,Styling.PROGRESS_PANEL_CIRCLES_HEIGTH));
		System.out.println(Styling.PROGRESS_PANEL_WIDTH + " circles " + Styling.PROGRESS_PANEL_CIRCLES_HEIGTH);
		Dimension d = new Dimension(Styling.PROGRESS_PANEL_LABELS_WIDTH,Styling.PROGRESS_PANEL_LABELS_HEIGTH);
		
		this.timeStatus.setPreferredSize(d);
		this.dayStatus.setPreferredSize(d);
		this.locStatus.setPreferredSize(d);
		this.timeStatus.setSize(d);
		this.dayStatus.setSize(d);
		this.locStatus.setSize(d);
		this.timeStatus.setMinimumSize(d);
		this.dayStatus.setMinimumSize(d);
		this.locStatus.setMinimumSize(d);
		this.timeStatus.setMaximumSize(d);
		this.dayStatus.setMaximumSize(d);
		this.locStatus.setMaximumSize(d);
		
		//this.setBorder(BorderFactory.createLineBorder(Color.white));
		//this.progressLabelsPanel.setBorder(BorderFactory.createLineBorder(Color.white));
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable taskCard,Object args){
		this.task = (Task) taskCard;
		
		progressLoc.setMaximum(this.task.getEstLOC());
		progressOverall.setMaximum(100);
		progressTime.setMaximum((int)task.getHoursEst());
		
		progressTime.setValue(task.getActualTime());
		progressOverall.setValue(task.getProgress());
		progressLoc.setValue(task.getActualLOC());
		
		progressTime.setStringPainted(true);
		progressOverall.setStringPainted(true);
		progressLoc.setStringPainted(true);

		//this.setLayout(border);
		//progressPanel.setLayout(grid);

		this.revalidate();
	}
	
	public void updateTask(){
		this.editComponents();
	}
	
	public Task getCurrentTask(){
		return this.task;
	}
}