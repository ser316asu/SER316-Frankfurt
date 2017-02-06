import java.util.*;
import java.text.*;

public class MAIN
{
	public static Develop d;
	public static TaskCard task;
   
    public static void main(String[] args)
    {
    	task = new TaskCard();
    	MAIN.createTestCard(task);
    	d = new Develop(task);
    }


    public static void createTestCard(TaskCard task){
    	task.setEstimatedLOC(500);
    	task.setEstimatedTime((double)8.5);
    	task.setActualLOC(256);
    	task.setActualTime(4.5);
    	task.setTaskName("test");
    	task.setStartDate(new Date());
    	task.setEndDate(new Date());

    }
}