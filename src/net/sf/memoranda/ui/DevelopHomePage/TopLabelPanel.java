package net.sf.memoranda.ui.DevelopHomePage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TopLabelPanel extends JLabel implements Styling, Observer{
	private TaskCard task;
	private LayoutManager layout;
	private Dimension dimension;
    private JLabel[] labels;
    private int location;
    private final int LABEL_COUNT = 5;
    private final double WIDTH_RATIO = .5,HEIGHT_RATIO = 1.0;

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

    public void alignLabels(){
    	for(int i = 0; i < LABEL_COUNT*2; i++){
    		labels[i].setAlignmentX(Component.LEFT_ALIGNMENT);
    	}
    }
	
    public void setSize(){
    	int width = (int) (Styling.TERMINAL_PANEL_WIDTH * WIDTH_RATIO);
    	int height = (int) (Styling.TERMINAL_PANEL_HEIGHT * HEIGHT_RATIO);
    	dimension = new Dimension(width,height);
		this.setMinimumSize(dimension);
		this.setPreferredSize(dimension);
		this.setMaximumSize(dimension);
    }

    public void addLabels(){
    	for(int i = 0; i < LABEL_COUNT*2; i++){
            this.add(labels[i]);
    	}
    }

    public void style(){
        this.setBackground(Styling.BACKGROUND_COLOR);
        for(int i = 0; i < LABEL_COUNT*2; i++){
            labels[i].setFont(Styling.TERMINAL_FONT);
            labels[i].setForeground(Styling.LABEL_PANEL_TEXT_COLOR);
        }
    }

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