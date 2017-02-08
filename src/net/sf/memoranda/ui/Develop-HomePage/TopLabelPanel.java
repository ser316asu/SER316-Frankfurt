import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TopLabelPanel extends JPanel implements Styling{
	private TaskCard task;
	private TopInnerPanel parent;
	private LayoutManager layout;
	private Dimension dimension;
    private JLabel[] labels;
    private int location;
    private final int LABEL_COUNT = 5;
    private final double WIDTH_RATIO = .5,HEIGHT_RATIO = 1.0;

    public TopLabelPanel(TopInnerPanel parent,TaskCard task,int location){
    	this.parent = parent;
    	this.task = task;
    	this.location = location;
    	this.labels = new JLabel[LABEL_COUNT];
    	layout = new GridLayout(LABEL_COUNT,0);
    	setSize();
    	createLabels();
    	this.setLayout(layout);
    	alignLabels();
    	addLabels();
        style();
    }

    public void createLabels(){
    	if(location == TopHomePanel.LEFT_LABEL_PANEL){
    		labels[0] = new JLabel("Task Name: " + task.getTaskName());
        	labels[1] = new JLabel("Estimated LOC: " + task.getEstimatedLOC());
        	labels[2] = new JLabel("Actual LOC: " + task.getActualLOC());
        	labels[3] = new JLabel("Estimated Time(hrs): " + task.getEstimatedTime());
        	labels[4] = new JLabel("Actual Time(hrs): " + task.getActualTime());
      	}
    	else if(location == TopHomePanel.RIGHT_LABEL_PANEL){
    	    labels[0] = new JLabel("Schedule Status: " + task.scheduleStatusToString());
    	    labels[1] = new JLabel("Actual LOC/h: " + task.getLocPerHour());
        	labels[2] = new JLabel("Estimated LOC/h: " + task.getEstimatedLOCPH());
        	labels[3] = new JLabel("Start Date: " + task.formatDate(task.getStartDate()));
        	labels[4] = new JLabel("End Date: " + task.formatDate(task.getEndDate()));
        }
    }

    public void alignLabels(){
    	for(int i = 0; i < LABEL_COUNT; i++){
    		labels[i].setVerticalAlignment(JLabel.CENTER);
    		labels[i].setHorizontalAlignment(JLabel.CENTER);
    	}
    }
	
    public void setSize(){
    	Dimension parentDimension = parent.getDimension();
    	int width = (int) (parentDimension.getWidth() * WIDTH_RATIO);
    	int height = (int) (parentDimension.getHeight() * HEIGHT_RATIO);
    	dimension = new Dimension(width,height);
    	this.setMinimumSize(dimension);
    }

    public void addLabels(){
    	for(int i = 0; i < LABEL_COUNT; i++){
            this.add(labels[i]);
    	}
    }

    public void style(){
        this.setBackground(Styling.BACKGROUND_COLOR);
        //this.setForeground(Styling.FOREGROUND_COLOR);
        for(int i = 0; i < LABEL_COUNT; i++){
            labels[i].setFont(Styling.FONT);
            labels[i].setForeground(Styling.FOREGROUND_COLOR);
        }
    }
	
}