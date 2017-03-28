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
import javax.swing.*;

import net.sf.memoranda.Task;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class TimerPanel.
 */
public class TimerPanel extends JLabel implements Styling{
	
	/** The button dimension. */
	private Dimension dimension,buttonDimension;
	
	/** The parent. */
	private TopInnerPanel parent;
	
	/** The stop. */
	private JButton pause,start,stop;
	
	/** The time. */
	private JLabel time;
	
	/** The layout. */
	private LayoutManager layout;
	
	/** The task. */
	private Task task;
	
	/** The button panel. */
	private JPanel buttonPanel;
	
	/** The timer listener. */
	private ButtonListener timerListener;
	
	/**
	 * Instantiates a new timer panel.
	 *
	 * @param parent the parent
	 * @param task the task
	 */
	public TimerPanel(TopInnerPanel parent,Task task){
		this.task = task;
		dimension = new Dimension(Styling.TIMER_PANEL_WIDTH,Styling.TIMER_PANEL_HEIGHT);
		this.parent = parent;
		createComponents();
		editComponents();
		addActionListener();
		style();
		addComponents();
		if(task == null){
			start.setEnabled(false);
			stop.setEnabled(false);
			pause.setEnabled(false);
		}
	}

	/**
	 * Creates the components.
	 */
	public void createComponents(){
		layout = new BorderLayout();

		pause = new JButton();
		start = new JButton();
		stop = new JButton();

		time = new JLabel();

		buttonDimension = new Dimension(75,50);

		buttonPanel = new JPanel(new GridLayout(0,3,2,2));

		timerListener = new ButtonListener();		
	}

	/**
	 * Edits the components.
	 */
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

	/**
	 * Adds the action listener.
	 */
	public void addActionListener(){
		pause.addActionListener(timerListener);
		start.addActionListener(timerListener);
		stop.addActionListener(timerListener);
	}

	/**
	 * Adds the components.
	 */
	public void addComponents(){
		buttonPanel.add(pause);
		buttonPanel.add(stop);
		buttonPanel.add(start);

		this.add(time,BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.SOUTH);
	}
	


	/* (non-Javadoc)
	 * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
	 */
	public void style(){
		//this.setBackground(Styling.TIMER_PANEL_COLOR);
		
		pause.setBackground(Styling.TASK_PANEL_COLOR);
		pause.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
		
		start.setBackground(Styling.TASK_PANEL_COLOR);
		start.setForeground(Styling.TASK_PANEL_TEXT_COLOR);

		stop.setBackground(Styling.TASK_PANEL_COLOR);
		stop.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
		
		time.setFont(Styling.TIMER_FONT);
		time.setForeground(Styling.TASK_PANEL_COLOR);

		buttonPanel.setBackground(Styling.BACKGROUND_COLOR);
		
		//this.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR));
	}

	/**
	 * The listener interface for receiving button events.
	 * The class that is interested in processing a button
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addButtonListener<code> method. When
	 * the button event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ButtonEvent
	 */
	private class ButtonListener implements ActionListener{
		
		/** The timer. */
		Timer timer;
		
		/** The paused. */
		boolean paused;
		
		/**
		 * Instantiates a new button listener.
		 */
		public ButtonListener(){
			timer = new Timer(time);
			paused = false;
		}

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent event){
			if(task == null)
			{
				System.out.println("null task");
				stop.setEnabled(false);
				start.setEnabled(false);
				pause.setEnabled(false);
			}
			else{
				if(event.getActionCommand().equalsIgnoreCase("start")){
					timer.start();
					start.setEnabled(false);
				}
				else if(event.getActionCommand().equalsIgnoreCase("pause")){
					paused = !paused;
					if(paused){
						timer.pause();
						pause.setText("Play");	
						stop.setEnabled(false);
					}
					else{
						timer.resume();
						pause.setText("Pause");
						stop.setEnabled(true);
					}
				
				}
				else if(event.getActionCommand().equalsIgnoreCase("stop")){
					timer.stop();
					int acttime = task.getActualTime();
					task.setActualTime(acttime + (convertTimer(timer.getLabel().getText())));
		
					task.setValue(task);
					timer = new Timer(time);
		
					start.setText("Start");
					start.setActionCommand("start");
					start.setEnabled(true);
		
					time.setText("00:00:00");
				}
			}
		}
			
	}
		
	public double convertTimer(String time){
			String[] hhmmss = time.split(":");

			double hours = Double.parseDouble(hhmmss[0]);
			double minutes = Double.parseDouble(hhmmss[1]);
			double seconds = Double.parseDouble(hhmmss[2]);

			 
			 hours += (minutes/60.0) + (seconds/3600);


			return hours;
		}
	

	public void setTask(Task tc) {
		this.task = tc;
		stop.setEnabled(true);
		start.setEnabled(true);
		pause.setEnabled(true);
	}
}
