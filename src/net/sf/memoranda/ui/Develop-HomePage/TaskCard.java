import java.util.*;

public class TaskCard{
	private int estimatedLOC;
	private int actualLOC;
	private String taskName;
	private double actualTime;
	private double estimatedTime;
	private double locPerHour;
	private double estimatedLOCPH;
	private Date startDate;
	private Date endDate;
	final static int AHEAD_OF_SCHED = 1;
	final static int BEHIND_SCHED = -1;
	final static int ON_TIME = 0;
	private int scheduleStatus;

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
	}

	/**
	*This method returns the time left in hours by taking the result of estimatedLOC / actualLOCPH. The value can be negative to signify how many hours overdue the project is 
	*@return A doubleing point value representing hours left
	*/
	public double calculateTimeLeft(){
		double result;
		
		double locLeft = estimatedLOC - actualLOC;

		result = locLeft / getLocPerHour();
		
		return result;
	}

	/**
	* This method returns the overall status of the task. 1 for ahead of schedule 0 for on time and -1 for behind schedule
	* @return The status of the schedule*/
	public int getScheduleStatus(){
		int result = scheduleStatus;
		double scheduleDifference = getEstimatedLOCPH() - getLocPerHour();

		// if the difference between the estimatedLOCPH and actual locPerHour is negative and less than -5 the programmer is behind
		if(scheduleDifference < 0 && scheduleDifference < -5){
			result = BEHIND_SCHED;
		}
		// if the difference between the estimatedLOCPH and actual locPerHour is postive and greater than 5 than the programmer is ahead
		else if(scheduleDifference > 0 && scheduleDifference > 5){
			result = AHEAD_OF_SCHED;
		}
		//otherwise the programmer/user is within 5loc/h and therefore is considered on time
		else{
			result = ON_TIME;
		}
		return result;
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
		return actualTime;
	}
	public double getEstimatedTime(){
		return estimatedTime;
	}
	public double getLocPerHour(){
		return actualLOC / actualTime;
	}
	public double getEstimatedLOCPH(){
		return estimatedLOC / estimatedTime;
	}
	public Date getStartDate(){
		return startDate;
	}
	public Date getEndDate(){
		return endDate;
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
}