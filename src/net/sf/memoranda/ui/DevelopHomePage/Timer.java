/**************************************************************
 * Copyright (c) 2017 - 2017, Joshua Becker, Alec Shinn,
 * All rights reserved
 * SER316-Frankfurt is a project for ser216, 
 * using agile scrum.
 * Description:
 * 
 * Contact: jdbecke3@asu.edu, atshinn@asu.edu
 **************************************************************/
package net.sf.memoranda.ui.DevelopHomePage;
import javax.swing.*;
import java.util.*;
import java.text.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Date getStartDate().
 */
public class Timer implements Runnable{
	
	/** The thread. */
	private Thread thread;
	
	/** The thread name. */
	private String threadName;
	
	/** The label. */
	private JLabel label;
	
	/** The current time. */
	private long currentTime;
	
	/** The start time. */
	private long startTime;
	
	/** The delta time. */
	private long deltaTime;
	
	/** The total time. */
	private long totalTime;
	
	/** The stopped. */
	private boolean paused,stopped;
	
	/** The sdf. */
	SimpleDateFormat sdf;
	
	/** The result. */
	Date result;
	
	/**
	 * Instantiates a new timer.
	 *
	 * @param label the label
	 */
	public Timer(JLabel label){
		threadName = "Task Timer";
		this.label = label;
		currentTime = 0;
		startTime = 0;
		deltaTime = currentTime - startTime;
		totalTime = 0;
		paused = false;
		stopped = false;

		sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		result = new Date(deltaTime);
	}

	/**
	 * Gets the label.
	 *
	 * @return the active
	 */
	public JLabel getLabel(){
		return label;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run(){		
		startTime = System.currentTimeMillis();
		
		while(!stopped){

			synchronized(this){
	
				while(paused){
	
					try{
						wait();
						
						totalTime += deltaTime;
						
						currentTime = 0;
						
						deltaTime = 0;
						
						startTime = System.currentTimeMillis();
					}catch(Exception e){
						e.printStackTrace();
	
					}
	
				}
	
			}				
			
			currentTime = System.currentTimeMillis();
			
			deltaTime = currentTime - startTime;
			
			result.setTime(totalTime + deltaTime);

			label.setText(sdf.format(result));
			
		}

		totalTime += deltaTime;
		currentTime = 0;
		startTime = 0;
		deltaTime = 0;
		result.setTime(totalTime);
		label.setText(sdf.format(result));		
	}

	/**
	 * Stop.
	 */
	public void stop(){
		stopped = true;
		try{
			thread.join();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	/**
	 * Pause.
	 */
	public void pause(){
		paused = true;
	}

	/**
	 * Resume.
	 */
	synchronized void resume(){
		paused = false;
		notify();
	}	

	/**
	 * Start.
	 */
	public void start(){
		thread = new Thread(this,threadName);
		thread.start();
	}

	/**
	 * Gets the date formatter.
	 *
	 * @return the date formatter
	 */
	public SimpleDateFormat getDateFormatter(){
		return sdf;
	}

}