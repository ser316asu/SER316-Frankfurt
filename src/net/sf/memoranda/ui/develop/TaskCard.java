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
import java.util.*;
import java.text.*;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskCard.
 */
public class TaskCard extends Observable{
	
	/** The estimated LOC. */
	private int estimatedLOC;
	
	/** The actual LOC. */
	private int actualLOC;
	
	/** The task name. */
	private String taskName;
	
	/** The actual time. */
	private double actualTime;
	
	/** The estimated time. */
	private double estimatedTime;
	
	/** The loc per hour. */
	private double locPerHour;
	
	/** The estimated LOCPH. */
	private double estimatedLOCPH;
	
	/** The start date. */
	private Date startDate;
	
	/** The end date. */
	private Date endDate;
    
    /** The is active. */
    private boolean isActive;
	
	/** The Constant AHEAD_OF_SCHED. */
	final static int AHEAD_OF_SCHED = 1;
	
	/** The Constant BEHIND_SCHED. */
	final static int BEHIND_SCHED = -1;
	
	/** The Constant ON_TIME. */
	final static int ON_TIME = 0;
	
	/** The day in ms. */
	private final long DAY_IN_MS = 1000 * 60 * 60 * 24;
	
	/** The schedule status. */
	private int scheduleStatus;
	
	/** The decimal format. */
	private NumberFormat decimalFormat;
	
	/** The change var. */
	private int changeVar;
	
	/** The days left. */
	private int length,daysLeft;
	
	/**
	 * Instantiates a new task card.
	 */
	public TaskCard(){
		estimatedLOC = 0;
		actualLOC = 0;
		locPerHour = 0;
		estimatedLOCPH = 0;
		taskName = "";
		actualTime = 0;
		estimatedTime = 0;
		startDate = null;
		endDate = null;
		scheduleStatus = ON_TIME;
		decimalFormat = new DecimalFormat("#0.0");
        this.isActive = false;
	}

	/**
	 * Calculate time left.
	 *
	 * @return the double
	 */
	public double calculateTimeLeft(){
		double result;
		
		double locLeft = estimatedLOC - actualLOC;

		result = locLeft / getLocPerHour();
		
		return Double.parseDouble(decimalFormat.format(result));				
	}

	/**
	 * Convert timer.
	 *
	 * @param time the time
	 * @return the double
	 */
	public double convertTimer(String time){
		String[] hhmmss = time.split(":");

		double hours = Double.parseDouble(hhmmss[0]);
		double minutes = Double.parseDouble(hhmmss[1]);
		double seconds = Double.parseDouble(hhmmss[2]);

		 
		 hours += (minutes/60.0) + (seconds/3600);


		return hours;
	}
	
	/**
	 * Adds the time.
	 *
	 * @param time the time
	 */
	public void addTime(double time){
		actualTime += time;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Calculate progress.
	 *
	 * @return the double
	 */
	public double calculateProgress(){
		return actualLOC / estimatedLOC;
	}

	/**
	 * Schedule status to string.
	 *
	 * @return the string
	 */
	public String scheduleStatusToString(){
		String result;
		double scheduleDifference = getEstimatedLOCPH() - getLocPerHour();

		// if the difference between the estimatedLOCPH and actual locPerHour is negative and less than -5 the programmer is behind
		if(scheduleDifference < 0 && scheduleDifference < -5){
			result = "BEHIND SCHEDULE";
		}
		// if the difference between the estimatedLOCPH and actual locPerHour is postive and greater than 5 than the programmer is ahead
		else if(scheduleDifference > 0 && scheduleDifference > 5){
			result = "AHEAD OF SCHEDULE";
		}
		//otherwise the programmer/user is within 5loc/h and therefore is considered on time
		else{
			result = "ON TIME";
		}
		return result;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public int getLength(){
		return (int) ((endDate.getTime() - startDate.getTime()) / DAY_IN_MS);
	}

	/**
	 * Gets the days left.
	 *
	 * @return the days left
	 */
	public int getDaysLeft(){
		return (int) ((endDate.getTime() - new Date().getTime()) / DAY_IN_MS);
	}
	
	/**
	 * Gets the schedule status.
	 *
	 * @return the schedule status
	 */
	public int getScheduleStatus(){
		return scheduleStatus;
	}
	
	/**
	 * Gets the estimated LOC.
	 *
	 * @return the estimated LOC
	 */
	public int getEstimatedLOC(){
		return estimatedLOC;
	}
	
	/**
	 * Gets the actual LOC.
	 *
	 * @return the actual LOC
	 */
	public int getActualLOC(){
		return actualLOC;
	}
	
	/**
	 * Gets the task name.
	 *
	 * @return the task name
	 */
	public String getTaskName(){
		return taskName;
	}
	
	/**
	 * Gets the actual time.
	 *
	 * @return the actual time
	 */
	public double getActualTime(){
		return Double.parseDouble(decimalFormat.format(actualTime));
	}
	
	/**
	 * Gets the estimated time.
	 *
	 * @return the estimated time
	 */
	public double getEstimatedTime(){
		return Double.parseDouble(decimalFormat.format(estimatedTime));
	}
	
	/**
	 * Gets the loc per hour.
	 *
	 * @return the loc per hour
	 */
	public double getLocPerHour(){
		return Double.parseDouble(decimalFormat.format(actualLOC / actualTime));
	}
	
	/**
	 * Gets the estimated LOCPH.
	 *
	 * @return the estimated LOCPH
	 */
	public double getEstimatedLOCPH(){
		return Double.parseDouble(decimalFormat.format(estimatedLOC / estimatedTime));
	}
	
	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate(){
		
		return startDate;
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate(){
		return endDate;
	}
	
	/**
	 * Format date.
	 *
	 * @param date the date
	 * @return the string
	 */
	public String formatDate(Date date){
		return new SimpleDateFormat("E MM-dd-yyyy").format(date);
		 //Integer.toString(date.getMonth()) + "/" + Integer.toString(date.getDay()) + "/" + Integer.toString(date.getYear());
	}

	/**
	 * Sets the estimated LOC.
	 *
	 * @param loc the new estimated LOC
	 */
	public void setEstimatedLOC(int loc){
		estimatedLOC = loc;

	}
	
	/**
	 * Sets the actual LOC.
	 *
	 * @param loc the new actual LOC
	 */
	public void setActualLOC(int loc){
		actualLOC = loc;
	}
	
	/**
	 * Sets the task name.
	 *
	 * @param name the new task name
	 */
	public void setTaskName(String name){
		taskName = name;
	}
	
	/**
	 * Sets the estimated time.
	 *
	 * @param time the new estimated time
	 */
	public void setEstimatedTime(double time){
		estimatedTime = time;
	}
	
	/**
	 * Sets the actual time.
	 *
	 * @param time the new actual time
	 */
	public void setActualTime(double time){
		actualTime = time;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param date the new start date
	 */
	public void setStartDate(Date date){
		startDate = date;
	}
	
	/**
	 * Sets the end date.
	 *
	 * @param date the new end date
	 */
	public void setEndDate(Date date){
		endDate = date;
	}
	
	/**
	 * Sets the schedule status.
	 */
	public void setScheduleStatus(){		
		double scheduleDifference = getEstimatedLOCPH() - getLocPerHour();

		// if the difference between the estimatedLOCPH and actual locPerHour is negative and less than -5 the programmer is behind
		if(scheduleDifference < 0 && scheduleDifference < -5){
			scheduleStatus = BEHIND_SCHED;
		}
		// if the difference between the estimatedLOCPH and actual locPerHour is postive and greater than 5 than the programmer is ahead
		else if(scheduleDifference > 0 && scheduleDifference > 5){
			scheduleStatus = AHEAD_OF_SCHED;
		}
		//otherwise the programmer/user is within 5loc/h and therefore is considered on time
		else{
			scheduleStatus = ON_TIME;
		}
	}
	
	/**
	 * Sets the value.
	 *
	 * @param task the new value
	 */
	public void setValue(TaskCard task){
		this.setChanged();
		this.notifyObservers(task);
	}
    
    /**
     * Sets the active.
     *
     * @param act the new active
     */
    public void setActive(boolean act)
    {
        this.isActive = act;
    }
    
    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    public boolean isActive()
    {
        return this.isActive;
    }
    
    /**
     * Sets the change var.
     *
     * @param newVar the new var
     * @return the task card
     */
    public TaskCard setChangeVar(int newVar)
    {
    	this.changeVar = newVar;
    	return this;
    }
    
    /**
     * Gets the change var.
     *
     * @return the change var
     */
    public int getChangeVar()
    {
    	return this.changeVar;
    }
}