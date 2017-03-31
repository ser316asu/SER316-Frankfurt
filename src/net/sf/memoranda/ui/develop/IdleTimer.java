/**************************************************************
 * Copyright (c) 2017 - 2017, Joshua Becker,
 * All rights reserved
 * SER316-Frankfurt is a project for ser216, 
 * using agile scrum.
 * Description: a timer to track when the use is idle and for
 * how long. if the user is idle for to long then exit.
 * 
 * Contact: jdbecke3@asu.edu
 **************************************************************/
package net.sf.memoranda.ui.develop;

import javax.swing.JOptionPane;

import net.sf.memoranda.ui.App;
import net.sf.memoranda.ui.ExitConfirmationDialog;
import net.sf.memoranda.util.Util;

/**
 * The Class IdleTimer.
 */
public class IdleTimer implements Runnable{
	
	/** The i timer. */
	private final Timer iTimer= new Timer();
	
	/** The idle wait. */
	private final int IDLE_WAIT = 30;
	
	/** The wait to close. */
	private final int WAIT_TO_CLOSE = 10;
	
	/** The stop. */
	private boolean stop = false;
	
	/**
	 * Run starts a timer to track the user
	 * idle time.
	 */
	@Override
	public synchronized void run() {
		this.startTimer(iTimer);
		while(!this.stop){
			if(iTimer.time > IDLE_WAIT){
				Timer tmpTimer = new Timer(WAIT_TO_CLOSE);
				this.startTimer(tmpTimer);
				int result = JOptionPane.showConfirmDialog(App.getFrame(),
						"You Have been Idle for " + IDLE_WAIT + 
						" minutes would you like to continue working? \nProgram will close automatically in "+ 
						WAIT_TO_CLOSE +" seconds",
						"Idle Working",
						JOptionPane.YES_NO_OPTION);
				switch(result){
					case JOptionPane.NO_OPTION:
						tmpTimer.setState(Timer.STOP);
						close();
						break;
					default:
						resetTimer();
						break;
				}
				tmpTimer.setState(Timer.STOP);
			}
			try {
				this.wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Reset timer.
	 */
	public void resetTimer(){
		//Util.debug("Reset Timer");
		iTimer.resetTimer();
	}
	
	/**
	 * Stops the timer.
	 */
	public void stop(){
		Util.debug("Stoped Timer");
		iTimer.setState(Timer.STOP);
		this.stop = true;
	}
	
	/**
	 * prompts the user to close the program.
	 */
	public void close(){
		Util.debug("Closed Timer");
		ExitConfirmationDialog ecd = new ExitConfirmationDialog(App.getFrame(),"Idle Timer");
		ecd.setLocationRelativeTo(null);
		ecd.setVisible(true);
		if(!ecd.CANCELLED){
			System.out.println("close");
			this.stop();
			App.getFrame().doExitWithoutAsk();
		}else{
			this.resetTimer();
		}
	}
	
	/**
	 * Start timer at timer within a thread.
	 *
	 * @param timer the timer
	 */
	private void startTimer(Timer timer){
		Thread thread = new Thread(timer);
		thread.start();
	}
	
	/**
	 * The Class Timer.
	 */
	private class Timer implements Runnable{
		
		///** The Constant PAUSED. */
		//public static final int PAUSED = 2;
		
		/** The Constant PLAY. */
		public static final int PLAY = 0;
		
		/** The Constant STOP. */
		public static final int STOP = 1;
		
		/** The time. */
		private double time;
		
		/** The start time. */
		private double startTime;
		
		/** The close time. */
		private int closeTime = IDLE_WAIT + 10;
		
		/** The state. */
		private int state = PLAY;
		
		/**
		 * Instantiates a new timer.
		 */
		public Timer(){
			
		}
		
		/**
		 * Instantiates a new timer.
		 *
		 * @param closeTime the close time
		 */
		public Timer(int closeTime){
			this.closeTime = closeTime;
		}
		
		/**
		 * Reset timer.
		 */
		public void resetTimer(){
			startTime = System.currentTimeMillis();
		}
		
		/**
		 * Sets the state.
		 *
		 * @param state the new state
		 */
		public void setState(int state){
			this.state = state;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			this.startTime = System.currentTimeMillis();
			while(this.state == Timer.PLAY){
				time = ((System.currentTimeMillis() - startTime)/1000)/60;
				if(time > this.closeTime){
					setState(Timer.STOP);
					App.getFrame().doExitWithoutAsk();
				}
			}
			
		}
	}
}
