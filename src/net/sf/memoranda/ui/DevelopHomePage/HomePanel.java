package net.sf.memoranda.ui.DevelopHomePage;
/**

**/
import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.util.*;
public class HomePanel extends JLabel implements Styling
{
    private TopHomePanel top_P;
	private LowerHomePanel low_P;
	private final double topRatio = .4;
	private final double lowRatio = .6;
    private TaskCard activeTask;
    private Hashtable<String,TaskCard> tasks;
    private LoadAssets assets;
    public HomePanel()
    {
        assets = new LoadAssets();
        this.tasks = new Hashtable<String, TaskCard>();
        fillTasks();
    	createComponents();
    	editComponents();
    	addActionListeners();
    	addComponents();
    }
    public HomePanel(TaskCard task)
    {
    	this.setIcon(LoadAssets.HOMEPAGE_BACKGROUND);
        assets = new LoadAssets();
        this.tasks = new Hashtable<String, TaskCard>();
        fillTasks();
        createComponents();
        editComponents();
        addActionListeners();
        style();
        addComponents();
    }


    public void createComponents()
    {
    	top_P = new TopHomePanel(this.tasks.get("task 1"));
    	low_P = new LowerHomePanel(this.top_P, this.tasks);
    }
    public void editComponents()
    {
    	this.setLayout(new FlowLayout());
    }
    public void addActionListeners()
    {

    }
    public void addComponents()
    {
    	this.add(top_P);
    	this.add(low_P);
    }

    public void style()
    {
        this.setBackground(Styling.BACKGROUND_COLOR);
    }
    public void fillTasks()
    {
        TaskCard tmpTask;
        for(int i = 0; i < 10; i++)
        {
            tmpTask = new TaskCard();
            tmpTask.setEstimatedLOC(500+i);
            tmpTask.setEstimatedTime((double)8.5+i);
            tmpTask.setActualLOC(256+i);
            tmpTask.setActualTime(4.5+i);
            tmpTask.setTaskName("task " + i);
            tmpTask.setStartDate(new Date("02/02/2017"));
            tmpTask.setEndDate(new Date("02/20/2017"));
            this.tasks.put(tmpTask.getTaskName(), tmpTask);
        }
        this.tasks.get("task 1").setActive(true);
    }
    public void createTasks()
    {

    }
}