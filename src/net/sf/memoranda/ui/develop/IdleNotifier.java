/**************************************************************
 * Copyright (c) 2017 - 2017, Joshua Becker,
 * All rights reserved
 * SER316-Frankfurt is a project for ser316, 
 * using agile scrum.
 * Description: IdleNotifier sets up the Idle Timer and allows
 * the execution of it.
 * 
 * Contact: jdbecke3@asu.edu
 **************************************************************/
package net.sf.memoranda.ui.develop;

/**
 * The Class IdleNotifier.
 */
public class IdleNotifier{
	
	/** The Constant INSTANCE. */
	private static final IdleNotifier INSTANCE = new IdleNotifier();
	
	/**
	 * Instantiates a new idle notifier.
	 */
	private IdleNotifier(){
		
	}
	
	/** The task. */
	private IdleTimer task;
	
	/**
	 * Gets the single instance of IdleNotifier.
	 *
	 * @return single instance of IdleNotifier
	 */
	public static IdleNotifier getInstance(){
		return INSTANCE;
	}
	
	/**
	 * Execute the Idle Timer.
	 *
	 * @param run the run
	 */
	public void execute(IdleTimer run) {
		Thread thread = new Thread(run);
		this.task  = run;
		thread.start();
	}
	
	/**
	 * Stop.
	 */
	public void stop(){
		this.task.stop();
	}
	
	/**
	 * Reset timer.
	 */
	public void resetTimer() {
		if(this.task != null)
			this.task.resetTimer();
	}
}
