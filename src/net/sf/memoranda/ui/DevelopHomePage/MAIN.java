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
import java.util.*;
import java.text.*;


// TODO: Auto-generated Javadoc
/**
 * The Class MAIN.
 */
public class MAIN
{
	
	/** The d. */
	public static Develop d;
	
	/** The task. */
	public static TaskCard task;
    //public static Hashtable<String,TaskCard> tasks;
   
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args)
    {
    	task = new TaskCard();

    	MAIN.createTestCard(task);

    	d = new Develop(true);
    }


    /**
     * Creates the test card.
     *
     * @param task the task
     */
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