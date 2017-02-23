/**************************************************************
 * Copyright (c) 2017 - 2017, Alec Shinn, Joshua Becker, All rights reserved
 * SER316-Frankfurt
 * Description:
 */
package net.sf.memoranda.ui.DevelopHomePage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class TopLabelPanel.
 */
public class TopLabelPanel extends JLabel implements Styling, Observer{
	
	/** The task. */
	private TaskCard task;
	
	/** The layout. */
	private LayoutManager layout;
	
	/** The dimension. */
	private Dimension dimension;
    
    /** The labels. */
    private JLabel[] labels;
    
    /** The location. */
    private int location;
    
    /** The label count. */
    private final int LABEL_COUNT = 5;
    
    /** The height ratio. */
    private final double WIDTH_RATIO = .5,HEIGHT_RATIO = 1.0;

    /**
     * Instantiates a new top label panel.
     *
     * @param task the task
     * @param location the location
     */
    public TopLabelPanel(TaskCard task,int location){
    	this.task = task;
    	this.location = location;
    	this.labels = new JLabel[LABEL_COUNT*2];
    	layout = new GridLayout(LABEL_COUNT,2);
    	setSize();
    	createLabels();
    	this.setLayout(layout);
    	alignLabels();
    	addLabels();
        style();
    }
    
    /**
     * Instantiates a new top label panel.
     *
     * @param location the location
     */
    public TopLabelPanel(int location){
    	this.task = new TaskCard();
    	this.location = location;
    	this.labels = new JLabel[LABEL_COUNT*2];
    	layout = new GridLayout(LABEL_COUNT,2);
    	setSize();
    	createLabels();
    	this.setLayout(layout);
    	alignLabels();
    	addLabels();
        style();
    }

    /**
     * Creates the labels.
     */
    public void createLabels(){
    	if(location == TopHomePanel.LEFT_LABEL_PANEL){
    		labels[0] = new JLabel(" Task Name ");
            labels[1] = new JLabel("");
        	labels[2] = new JLabel(" Estimated LOC: ");
            labels[3] = new JLabel("");
        	labels[4] = new JLabel(" Actual LOC: ");
            labels[5] = new JLabel("");
        	labels[6] = new JLabel(" Estimated Time(hrs): ");
            labels[7] = new JLabel("");
        	labels[8] = new JLabel(" Actual Time(hrs): ");
        	labels[9] = new JLabel("");
      	}
    	else if(location == TopHomePanel.RIGHT_LABEL_PANEL){
    		labels[0] = new JLabel(" Schedule Status: ");
            labels[1] = new JLabel(" ");
        	labels[2] = new JLabel(" Actual LOC/h: ");
            labels[3] = new JLabel("");
        	labels[4] = new JLabel(" Estimated LOC/h: ");
            labels[5] = new JLabel("");
        	labels[6] = new JLabel(" Start Date: ");
            labels[7] = new JLabel("");
        	labels[8] = new JLabel(" End Date: ");
        	labels[9] = new JLabel("");
        }
    }

    /**
     * Align labels.
     */
    public void alignLabels(){
    	for(int i = 0; i < LABEL_COUNT*2; i++){
    		labels[i].setAlignmentX(Component.LEFT_ALIGNMENT);
    	}
    }
	
    /**
     * Sets the size.
     */
    public void setSize(){
    	int width = (int) (Styling.TERMINAL_PANEL_WIDTH * WIDTH_RATIO);
    	int height = (int) (Styling.TERMINAL_PANEL_HEIGHT * HEIGHT_RATIO);
    	dimension = new Dimension(width,height);
		this.setMinimumSize(dimension);
		this.setPreferredSize(dimension);
		this.setMaximumSize(dimension);
    }

    /**
     * Adds the labels.
     */
    public void addLabels(){
    	for(int i = 0; i < LABEL_COUNT*2; i++){
            this.add(labels[i]);
    	}
    }

    /* (non-Javadoc)
     * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
     */
    public void style(){
        this.setBackground(Styling.BACKGROUND_COLOR);
        for(int i = 0; i < LABEL_COUNT*2; i++){
            labels[i].setFont(Styling.TERMINAL_FONT);
            labels[i].setForeground(Styling.LABEL_PANEL_TEXT_COLOR);
        }
    }

    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    public void update(Observable taskCard,Object ars){
    	this.task.setActive(false);
    	this.task = (TaskCard) taskCard;
    	this.task.setActive(true);
    	//this.removeAll();
        if(location == TopHomePanel.LEFT_LABEL_PANEL){
            labels[1].setText(task.getTaskName()+ "");// = new JLabel(task.getTaskName()+ "");
            labels[3].setText(task.getEstimatedLOC()+ "");
            labels[5].setText(task.getActualLOC()+ "");
            labels[7].setText(task.getEstimatedTime()+ "");
            labels[9].setText(task.getActualTime()+ "");
        }
        else if(location == TopHomePanel.RIGHT_LABEL_PANEL){
            labels[1].setText(task.scheduleStatusToString());
            labels[3].setText(task.getLocPerHour() + "");
            labels[5].setText(task.getEstimatedLOCPH()+ "");
            labels[7].setText(task.formatDate(task.getStartDate())+ "");
            labels[9].setText(task.formatDate(task.getEndDate())+ "");
        }
        //this.addLabels();
        //style();
        this.revalidate();
    }
}