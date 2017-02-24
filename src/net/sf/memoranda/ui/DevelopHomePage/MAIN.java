package net.sf.memoranda.ui.DevelopHomePage;
import java.util.*;
import java.text.*;


public class MAIN
{
	public static Develop d;
	public static TaskCard task;
    //public static Hashtable<String,TaskCard> tasks;
   
    public static void main(String[] args)
    {
    	task = new TaskCard();

    	MAIN.createTestCard(task);

    	d = new Develop();
    }


    public static void createTestCard(TaskCard task){
    	task.setEstimatedLOC(500);
    	task.setEstimatedTime((double)8.5);
    	task.setActualLOC(256);
    	task.setActualTime(4.5);
    	task.setTaskName("test");
    	task.setStartDate(new Date("02/02/2017"));
    	task.setEndDate(new Date("02/20/2017"));

    }
}