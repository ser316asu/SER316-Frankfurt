package net.sf.memoranda.ui.DevelopHomePage;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class StatusBarPanel extends JPanel implements Styling, Observer{
	private TopInnerPanel parent;
	private JPanel buttonPanel,progressPanel;
	private TaskCard task;
	private Dimension dimension,progressDimension;
	private LayoutManager border,grid;
	private JLabel timeStatus,locStatus,dayStatus,spacer;
	private JProgressBar progressTime,progressLoc,progressDays;
	private JButton open;
	private int timeMax,locMax,daysMax;
	private final double WIDTH_RATIO = 1.0,HEIGHT_RATIO = .5;
	
	
	public StatusBarPanel(TopInnerPanel parent,TaskCard task){
		this.parent = parent;
		
		buildComponents();

		editComponents();

		style();

		addActionListeners();

		addComponents();
	}

	public void buildComponents(){
		progressTime = new JProgressBar(0,100);
		progressDays = new JProgressBar(0,100);
		progressLoc = new JProgressBar(0,100);
		border = new BorderLayout();
		grid = new GridLayout(7,1,1,1);

		open = new JButton();
		
		dayStatus = new JLabel("Progress in Days");
		locStatus = new JLabel("Progress in LOC");
		timeStatus = new JLabel("Progress in Hours");
		spacer = new JLabel(" ");

		buttonPanel = new JPanel();
		progressPanel = new JPanel();

	}

	public void editComponents(){
		int width = (int) (parent.getDimension().getWidth() * WIDTH_RATIO);
		int height = (int) (parent.getDimension().getHeight() * HEIGHT_RATIO);

		dimension = new Dimension(width,height);

		progressDimension = new Dimension(160,(int)((parent.getDimension().getHeight() / 6) - 5));		

		this.setMinimumSize(dimension);

		progressDays.setMinimumSize(progressDimension);
		progressLoc.setMinimumSize(progressDimension);
		progressTime.setMinimumSize(progressDimension);

		progressDays.setMaximumSize(progressDimension);
		progressTime.setMaximumSize(progressDimension);
		progressLoc.setMaximumSize(progressDimension);

		progressTime.setValue(0);
		progressDays.setValue(0);
		progressLoc.setValue(0);
		
		progressTime.setStringPainted(true);
		progressDays.setStringPainted(true);
		progressLoc.setStringPainted(true);

		open.setText("Open Task File");

		this.setLayout(border);
		progressPanel.setLayout(grid);

		buttonPanel.setLayout(new GridLayout(1,0,1,1));
	}

	public void addActionListeners(){}

	public void addComponents(){
		progressPanel.add(dayStatus);
		progressPanel.add(progressDays);

		progressPanel.add(timeStatus);
		progressPanel.add(progressTime);

		progressPanel.add(locStatus);
		progressPanel.add(progressLoc);

		progressPanel.add(spacer);
		buttonPanel.add(open);

		this.add(progressPanel,BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.SOUTH);
	}

	public void style(){
		this.setBackground(Styling.BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR));

		progressDays.setBackground(Styling.TASK_PANEL_TEXT_COLOR);
		progressDays.setForeground(Styling.TASK_PANEL_COLOR);
		progressDays.setFont(Styling.FONT);

		progressLoc.setBackground(Styling.TASK_PANEL_TEXT_COLOR);
		progressLoc.setForeground(Styling.TASK_PANEL_COLOR);
		progressLoc.setFont(Styling.FONT);

		progressTime.setBackground(Styling.TASK_PANEL_TEXT_COLOR);
		progressTime.setForeground(Styling.TASK_PANEL_COLOR);
		progressTime.setFont(Styling.FONT);

		locStatus.setForeground(Styling.LABEL_PANEL_TEXT_COLOR);
		locStatus.setFont(Styling.FONT);

		dayStatus.setForeground(Styling.LABEL_PANEL_TEXT_COLOR);
		dayStatus.setFont(Styling.FONT);

		timeStatus.setForeground(Styling.LABEL_PANEL_TEXT_COLOR);
		timeStatus.setFont(Styling.FONT);

		open.setBackground(Styling.TASK_PANEL_COLOR);
		open.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
		open.setFont(Styling.FONT);
	}

	public void update(Observable taskCard,Object args){
		this.task = (TaskCard) taskCard;

		this.removeAll();

		progressTime = new JProgressBar(0,(int)task.getEstimatedTime());
		progressDays = new JProgressBar(0,task.getLength());
		progressLoc = new JProgressBar(0,task.getEstimatedLOC());

		border = new BorderLayout();
		grid = new GridLayout(7,1,1,1);

		open = new JButton();
		
		dayStatus = new JLabel("Progress in Days");
		locStatus = new JLabel("Progress in LOC");
		timeStatus = new JLabel("Progress in Hours");
		spacer = new JLabel(" ");

		buttonPanel = new JPanel();
		progressPanel = new JPanel();

		int width = (int) (parent.getDimension().getWidth() * WIDTH_RATIO);
		int height = (int) (parent.getDimension().getHeight() * HEIGHT_RATIO);

		dimension = new Dimension(width,height);

		progressDimension = new Dimension(160,(int)((parent.getDimension().getHeight() / 6) - 5));		

		this.setMinimumSize(dimension);

		progressDays.setMinimumSize(progressDimension);
		progressLoc.setMinimumSize(progressDimension);
		progressTime.setMinimumSize(progressDimension);

		progressDays.setMaximumSize(progressDimension);
		progressTime.setMaximumSize(progressDimension);
		progressLoc.setMaximumSize(progressDimension);

		progressTime.setValue((int)task.getActualTime());
		progressDays.setValue(task.getLength() - task.getDaysLeft());
		progressLoc.setValue(task.getActualLOC());
		
		progressTime.setStringPainted(true);
		progressDays.setStringPainted(true);
		progressLoc.setStringPainted(true);

		open.setText("Open Task File");

		this.setLayout(border);
		progressPanel.setLayout(grid);

		buttonPanel.setLayout(new GridLayout(1,0,1,1));

		style();

		addComponents();

		this.revalidate();
	}
}