package net.sf.memoranda.ui.DevelopHomePage;
/**

**/
import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.*;
public class HomePanel extends JLabel implements Styling
{
    private TopHomePanel top_P;
	private LowerHomePanel low_P;
	private JSplitPane container, containerForToolbar;
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
    	container = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	containerForToolbar = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    }
    public void editComponents()
    {
    	this.setLayout(new GridLayout(1,1,0,0));
    	container.setMaximumSize(new Dimension(Styling.SCREEN_WIDTH,Styling.SCREEN_HEIGHT));
    	container.setMinimumSize(new Dimension(Styling.SCREEN_WIDTH-Styling.MAIN_TOOLBAR_WIDTH-15,Styling.SCREEN_HEIGHT));
    	container.setDividerLocation(Styling.TERMINAL_PANEL_HEIGHT+1);
    	container.setOneTouchExpandable(true);
    	container.setResizeWeight(1);
    	container.setOpaque(false);
    	
    	containerForToolbar.setMaximumSize(new Dimension(Styling.SCREEN_WIDTH,Styling.SCREEN_HEIGHT));
    	containerForToolbar.setMinimumSize(new Dimension(Styling.SCREEN_WIDTH-Styling.MAIN_TOOLBAR_WIDTH-15,Styling.SCREEN_HEIGHT));
    	containerForToolbar.setDividerLocation(Styling.MAIN_TOOLBAR_WIDTH);
    	containerForToolbar.setOneTouchExpandable(true);
    	containerForToolbar.setResizeWeight(1);
    	containerForToolbar.setOpaque(false);

    	//this.setIcon(LoadAssets.HOMEPAGE_BACKGROUND);
    }
    public void addActionListeners()
    {

    }
    public void addComponents()
    {
    	container.setTopComponent(this.top_P);
    	container.setBottomComponent(this.low_P);
    	//this.add(new MainToolBar());
    	//this.setLayer(c, layer);
    	//this.add(this.toolbar, new Integer(10));
    	//this.add(toolbar,JLayeredPane.DRAG_LAYER);
    	//this.add(container, JLayeredPane.DRAG_LAYER);
    	containerForToolbar.setLeftComponent(this.toolbar);
    	containerForToolbar.setRightComponent(this.container);
    	
    	this.add(containerForToolbar);
    	this.revalidate();
    }

    public void style()
    {
    	this.setPreferredSize(new Dimension(Styling.SCREEN_WIDTH-2,Styling.SCREEN_HEIGHT-2));
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