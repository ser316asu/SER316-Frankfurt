package net.sf.memoranda.ui.DevelopHomePage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TimerPanel extends JPanel implements Styling{
	private Dimension dimension,buttonDimension;
	private TopInnerPanel parent;
	private JButton pause,start,stop;
	private JLabel time;
	private LayoutManager layout;
	private TaskCard task;
	private JPanel buttonPanel;
	private ButtonListener timerListener;
	
	public TimerPanel(TopInnerPanel parent,TaskCard task){
		this.task = task;
		this.parent = parent;
		createComponents();
		editComponents();
		addActionListener();
		style();
		addComponents();
	}

	public void createComponents(){
		layout = new BorderLayout();

		pause = new JButton();
		start = new JButton();
		stop = new JButton();

		time = new JLabel();
		
		int width = (int) parent.getDimension().getWidth();
		int height = (int) parent.getDimension().getHeight() + 5;

		dimension = new Dimension(width,height);

		buttonDimension = new Dimension(75,50);

		buttonPanel = new JPanel(new GridLayout(0,3,2,2));

		timerListener = new ButtonListener();		
	}

	public void editComponents(){
		this.setPreferredSize(dimension);
		this.setMaximumSize(dimension);
		this.setMinimumSize(dimension);

		this.setLayout(layout);

		pause.setText("Pause");
		start.setText("Start");
		stop.setText("Stop");
		
		time.setText("00:00:00");

		time.setVerticalAlignment(JLabel.CENTER);
		pause.setVerticalAlignment(JLabel.CENTER);
		start.setVerticalAlignment(JLabel.CENTER);
		stop.setVerticalAlignment(JLabel.CENTER);

		stop.setHorizontalAlignment(JLabel.CENTER);
		time.setHorizontalAlignment(JLabel.CENTER);
		pause.setHorizontalAlignment(JLabel.CENTER);
		start.setHorizontalAlignment(JLabel.CENTER);

		pause.setActionCommand("pause");
		start.setActionCommand("start");
		stop.setActionCommand("stop");

		start.setMinimumSize(buttonDimension);
		pause.setMinimumSize(buttonDimension);
		stop.setMinimumSize(buttonDimension);
	}

	public void addActionListener(){
		pause.addActionListener(timerListener);
		start.addActionListener(timerListener);
		stop.addActionListener(timerListener);
	}

	public void addComponents(){
		buttonPanel.add(pause);
		buttonPanel.add(stop);
		buttonPanel.add(start);

		this.add(time,BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.SOUTH);
	}

	public void style(){
		this.setBackground(Styling.BACKGROUND_COLOR);
		
		pause.setBackground(Styling.TASK_PANEL_COLOR);
		pause.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
		
		start.setBackground(Styling.TASK_PANEL_COLOR);
		start.setForeground(Styling.TASK_PANEL_TEXT_COLOR);

		stop.setBackground(Styling.TASK_PANEL_COLOR);
		stop.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
		
		time.setFont(Styling.TIMER_FONT);
		time.setForeground(Styling.TASK_PANEL_COLOR);

		buttonPanel.setBackground(Styling.BACKGROUND_COLOR);
		
		this.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR));
	}

	private class ButtonListener implements ActionListener{
		Timer timer;
		boolean paused;
		public ButtonListener(){
			timer = new Timer(time);
			paused = false;
		}

		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand().equalsIgnoreCase("start")){
				timer.start();
			}
			else if(event.getActionCommand().equalsIgnoreCase("pause")){
				paused = !paused;
				if(paused){
					timer.pause();
					pause.setText("Play");					
				}
				else{
					timer.resume();
					pause.setText("Pause");
				}
			
			}
			else if(event.getActionCommand().equalsIgnoreCase("stop")){
				timer.stop();
				task.addTime(task.convertTimer(timer.getLabel().getText()));

				task.setValue(task.setChangeVar(task.getChangeVar()+1));
				timer = new Timer(time);

				start.setText("Start");
				start.setActionCommand("start");

				time.setText("00:00:00");
			}
			
		}
	}
}
