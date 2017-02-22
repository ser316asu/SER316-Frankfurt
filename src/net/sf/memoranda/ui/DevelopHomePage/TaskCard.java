package net.sf.memoranda.ui.DevelopHomePage;
import java.util.*;
import java.text.*;

public class TaskCard extends Observable{
	private int estimatedLOC;
	private int actualLOC;
	private int numFiles;
	private int actualNumFiles;
	private String taskName;
	private String taskDescription;
	private double actualTime;
	private double estimatedTime;
	private double locPerHour;
	private double estimatedLOCPH;
	private Date startDate;
	private Date endDate;
    private boolean isActive;
	final static int AHEAD_OF_SCHED = 1;
	final static int BEHIND_SCHED = -1;
	final static int ON_TIME = 0;
	private final long DAY_IN_MS = 1000 * 60 * 60 * 24;
	private int scheduleStatus;
	private NumberFormat decimalFormat;
	private int changeVar;
	private int length,daysLeft;
	
	public TaskCard(){
		estimatedLOC = 0;
		actualLOC = 0;
		numFiles = 0;
		actualNumFiles = 0;
		locPerHour = 0;
		estimatedLOCPH = 0;
		taskName = "";
		taskDescription = "";
		actualTime = 0;
		estimatedTime = 0;
		startDate = null;
		endDate = null;
		scheduleStatus = ON_TIME;
		decimalFormat = new DecimalFormat("#0.0");
        this.isActive = false;
	}
	
	public TaskCard(int estimatedLOC, int estimatedTime, int numFiles, Date startDate, Date endDate, String taskDescription, String taskName){
		this.estimatedLOC = estimatedLOC;
		this.estimatedTime = (double) estimatedTime;
		this.startDate = startDate;
		this.endDate = endDate;
        this.isActive = false;
        
		locPerHour = 0;
		estimatedLOCPH = 0;
		taskName = "";
		actualTime = 0;
		actualNumFiles = 0;
		scheduleStatus = ON_TIME;
		decimalFormat = new DecimalFormat("#0.0");

	}


	/**
	*This method returns the time left in hours by taking the result of estimatedLOC / actualLOCPH. The value can be negative to signify how many hours overdue the project is 
	*@return A doubleing point value representing hours left
	*/
	public double calculateTimeLeft(){
		double result;
		
		double locLeft = estimatedLOC - actualLOC;

		result = locLeft / getLocPerHour();
		
		return Double.parseDouble(decimalFormat.format(result));				
	}

	public double convertTimer(String time){
		String[] hhmmss = time.split(":");

		double hours = Double.parseDouble(hhmmss[0]);
		double minutes = Double.parseDouble(hhmmss[1]);
		double seconds = Double.parseDouble(hhmmss[2]);

		 
		 hours += (minutes/60.0) + (seconds/3600);


		return hours;
	}
	
	public void addTime(double time){
		actualTime += time;
		setChanged();
		notifyObservers();
	}
	
	public double calculateProgress(){
		return actualLOC / estimatedLOC;
	}

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

	public int getLength(){
		return (int) ((endDate.getTime() - startDate.getTime()) / DAY_IN_MS);
	}

	public int getDaysLeft(){
		return (int) ((endDate.getTime() - new Date().getTime()) / DAY_IN_MS);
	}
	
	public int getScheduleStatus(){
		return scheduleStatus;
	}
	public int getEstimatedLOC(){
		return estimatedLOC;
	}
	public int getActualLOC(){
		return actualLOC;
	}
	public String getTaskName(){
		return taskName;
	}
	public double getActualTime(){
		return Double.parseDouble(decimalFormat.format(actualTime));
	}
	public double getEstimatedTime(){
		return Double.parseDouble(decimalFormat.format(estimatedTime));
	}
	public double getLocPerHour(){
		return Double.parseDouble(decimalFormat.format(actualLOC / actualTime));
	}
	public double getEstimatedLOCPH(){
		return Double.parseDouble(decimalFormat.format(estimatedLOC / estimatedTime));
	}
	public Date getStartDate(){
		
		return startDate;
	}
	public Date getEndDate(){
		return endDate;
	}
	public String formatDate(Date date){
		return new SimpleDateFormat("E MM-dd-yyyy").format(date);
		 //Integer.toString(date.getMonth()) + "/" + Integer.toString(date.getDay()) + "/" + Integer.toString(date.getYear());
	}

	public void setEstimatedLOC(int loc){
		estimatedLOC = loc;

	}
	public void setActualLOC(int loc){
		actualLOC = loc;
	}
	public void setTaskName(String name){
		taskName = name;
	}
	public void setEstimatedTime(double time){
		estimatedTime = time;
	}
	public void setActualTime(double time){
		actualTime = time;
	}
	public void setStartDate(Date date){
		startDate = date;
	}
	public void setEndDate(Date date){
		endDate = date;
	}
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
	
	public void setValue(TaskCard task){
		this.setChanged();
		this.notifyObservers(task);
	}
    public void setActive(boolean act)
    {
        this.isActive = act;
    }
    public boolean isActive()
    {
        return this.isActive;
    }
    public TaskCard setChangeVar(int newVar)
    {
    	this.changeVar = newVar;
    	return this;
    }
    public int getChangeVar()
    {
    	return this.changeVar;
    }
}