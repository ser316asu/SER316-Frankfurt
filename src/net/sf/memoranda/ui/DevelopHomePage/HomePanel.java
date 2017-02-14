package net.sf.memoranda.ui.DevelopHomePage;
/**

**/
import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.*;
public class HomePanel extends JLayeredPane implements Styling
{
    private TopHomePanel top_P;
	private LowerHomePanel low_P;
	private JLabel container;
    private TaskCard activeTask;
    private Hashtable<String,TaskCard> tasks;
    private LoadAssets assets;
    private MainToolBar toolbar;
    public HomePanel()
    {
    	
        assets = new LoadAssets();
        this.tasks = new Hashtable<String, TaskCard>();
        this.toolbar = new MainToolBar();
        fillTasks();
    	createComponents();
    	editComponents();
    	addActionListeners();
    	addComponents();
    }
    public HomePanel(TaskCard task)
    {
    	//this.setIcon(LoadAssets.HOMEPAGE_BACKGROUND);
        assets = new LoadAssets();
        this.toolbar = new MainToolBar();
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
    	container = new JLabel();
    }
    public void editComponents()
    {
    	container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    	container.setMaximumSize(new Dimension(Styling.SCREEN_WIDTH,Styling.SCREEN_HEIGHT));
    	container.setMinimumSize(new Dimension(Styling.SCREEN_WIDTH,Styling.SCREEN_HEIGHT));
    	this.setBorder(BorderFactory.createLineBorder(Color.red)); 
    	this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    	this.container.setLocation(0,0);
    	this.toolbar.setLocation(0,0);
    }
    public void addActionListeners()
    {

    }
    public void addComponents()
    {
    	container.add(top_P);
    	container.add(low_P);
    	//this.add(new MainToolBar());
    	//this.setLayer(c, layer);
    	//this.add(this.toolbar, new Integer(10));
    	this.toolbar.setAlwaysOnTop(true);
    	this.toolbar.setVisible(true);
    	this.add(container, new Integer(0));
    	this.revalidate();
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