package net.sf.memoranda.ui.DevelopHomePage;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class StatusBarPanel extends JLabel implements Styling, Observer{
	private TopInnerPanel parent;
	private JLabel buttonPanel,progressPanel;
	private TaskCard task;
	private Dimension dimension,progressDimension;
	private LayoutManager border,grid;
	private JLabel timeStatus,locStatus,dayStatus,spacer;
	private JProgressBar progressTime,progressLoc,progressDays;
	private JButton open;
	private final double WIDTH_RATIO = 1.0,HEIGHT_RATIO = .5;
	
	
	public StatusBarPanel(TopInnerPanel parent,TaskCard task){
		this.dimension = new Dimension(Styling.PROGRESS_PANEL_WIDTH,Styling.PROGRESS_PANEL_HEIGHT);
		
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
		
		progressTime.setUI(new ProgressCircleUI());
		progressLoc.setUI(new ProgressCircleUI());
		progressDays.setUI(new ProgressCircleUI());
	
		//progressTime.setUI(new ProgressCircleUI());

		open = new JButton();
		
		dayStatus = new JLabel("Progress in Days");
		locStatus = new JLabel("Progress in LOC");
		timeStatus = new JLabel("Progress in Hours");
		spacer = new JLabel(" ");
		progressTime = ProgressCircleUI.makeUI(Styling.PROGRESSBAR_HOURS_COLOR);
		progressLoc = ProgressCircleUI.makeUI(Styling.PROGRESSBAR_LOC_COLOR);
		progressDays = ProgressCircleUI.makeUI(Styling.PROGRESSBAR_DAYS_COLOR);

		buttonPanel = new JLabel();
		progressPanel = new JLabel();

	}

	public void editComponents(){
		this.setPreferredSize(this.dimension);
		this.setMinimumSize(this.dimension);

		//progressDimension = new Dimension(160,(int)((parent.getDimension().getHeight() / 6) - 5));
		progressTime.setValue(0);
		progressDays.setValue(0);
		progressLoc.setValue(0);
		
		progressTime.setStringPainted(true);
		progressDays.setStringPainted(true);
		progressLoc.setStringPainted(true);

		open.setText("Open Task File");

		this.setLayout(new BorderLayout());
		progressPanel.setLayout(new GridLayout(1,3,0,0));
		progressPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	}

	public void addActionListeners(){}

	public void addComponents(){
		//progressPanel.add(dayStatus);
		progressPanel.add(progressTime);

		//progressPanel.add(timeStatus);
		progressPanel.add(progressDays);

		//progressPanel.add(locStatus);
		progressPanel.add(progressLoc);

		buttonPanel.add(open);

		this.add(progressPanel,BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.SOUTH);
	}

	public void style(){
		//this.setBackground(Styling.BACKGROUND_COLOR);
		progressPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		this.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR));

		//progressDays.setBackground(Styling.TASK_PANEL_TEXT_COLOR);
		//progressDays.setForeground(Styling.TASK_PANEL_COLOR);
		progressDays.setFont(Styling.TERMINAL_FONT);

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
	}

	public void update(Observable taskCard,Object args){
		this.task = (TaskCard) taskCard;
		
		progressLoc.setMaximum(this.task.getEstimatedLOC());
		progressDays.setMaximum(this.task.getLength());
		progressTime.setMaximum((int)this.task.getEstimatedTime());
		
		progressTime.setValue((int)task.getActualTime());
		progressDays.setValue(task.getLength() - task.getDaysLeft());
		progressLoc.setValue(task.getActualLOC());
		
		progressTime.setStringPainted(true);
		progressDays.setStringPainted(true);
		progressLoc.setStringPainted(true);

		//this.setLayout(border);
		//progressPanel.setLayout(grid);

		this.revalidate();
	}
}