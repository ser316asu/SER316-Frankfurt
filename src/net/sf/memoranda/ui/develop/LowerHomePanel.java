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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.Task;


import java.awt.*;
import java.util.Hashtable;
import java.util.Observer;

// TODO: Auto-generated Javadoc
/**
 * The Class LowerHomePanel.
 */
public class LowerHomePanel extends JLabel implements Styling, Observer {
    
	/** auto generated serialversion UID. */
	private static final long serialVersionUID = -8015237809090141365L;
    
    /** The tool bar P. */
    private JPanel addTasksP;
    
    /** The tool bar P. */
    private JPanel toolBarP;
    
    /** The tasks P. */
    private static JPanel tasksP;
    
    /** The top home panel. */
    private static TopHomePanel topHomePanel;
    
    /** The old height. */
    private int oldWidth;
    
    /** The old height. */
    private int oldHeight;
    
    /** The state. */
    private boolean state;

    /**
     * Instantiates a new lower home panel.
     */
    public LowerHomePanel() {
        topHomePanel = null;
        
    	createComponents();
        style();
    	editComponents();
    	addActionListeners();
    	addComponents();
    	((java.util.Observable) CurrentProject.getTaskList()).addObserver(this);
    }
    
    /**
     * Instantiates a new lower home panel.
     *
     * @param thp the thp
     * @param tasks the tasks
     */
    public LowerHomePanel(TopHomePanel thp){
        this.topHomePanel = thp;
    	createComponents();
        style();
    	editComponents();
    	addActionListeners();
    	addComponents();
    	((java.util.Observable) CurrentProject.getTaskList()).addObserver(this);
    }


    /**
     * Creates the components.
     */
    public void createComponents(){
          tasksP = new JPanel();
          addTasksP = new JPanel();
          toolBarP = new JPanel();
    }
    
    /**
     * Edits the components.
     */
    public void editComponents() {            
          this.setLayout(new OverlayLayout(this));
          tasksP.setLayout(new FlowLayout(FlowLayout.CENTER,15,40));
          toolBarP.setLayout(new BorderLayout());
          addTasksP.setLayout(new FlowLayout(FlowLayout.RIGHT));
          
          setTransparent(addTasksP);
          setTransparent(tasksP);
          setTransparent(toolBarP);
    }
    
    /**
     * Adds the action listeners.
     */
    public void addActionListeners(){
    }
    
    /**
     * Adds the components.
     */
    public void addComponents(){
          for(Object task : CurrentProject.getTaskList().getTopLevelTasks()){
              tasksP.add(new TaskPanel(((Task) task), topHomePanel),JLayeredPane.DRAG_LAYER);
          }
          
          //addTasksP.add(sortTasks_CB);
          //addTasksP.add(addTask_B);
          toolBarP.add(addTasksP, BorderLayout.NORTH);
          
          
          this.add(tasksP);
          this.add(toolBarP);
          toolBarP.revalidate();
          tasksP.revalidate();
          this.revalidate();

          
    }
    public static void updateTaskBoard()
    {
    	tasksP.removeAll();
    	for(Object task : CurrentProject.getTaskList().getTopLevelTasks())
    		tasksP.add(new TaskPanel(((Task) task), topHomePanel),JLayeredPane.DRAG_LAYER);
    	tasksP.revalidate();
    }
    /**
     * Sets the transparent.
     *
     * @param panel the new transparent
     */
    private void setTransparent(JPanel panel){
        panel.setOpaque(false);
        panel.setBackground(new Color(0,0,0,255));
    }
    
    /* (non-Javadoc)
     * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
     */
    public void style(){
        this.setBackground(Styling.BACKGROUND_COLOR);
        this.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR));
        //tasksP.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR)); 
        this.setIcon(LoadAssets.TASK_BOARD_IMAGE);
        
        this.setMaximumSize(new Dimension(Styling.TASK_BOARD_WIDTH,Styling.TASK_BOARD_HEIGHT));
        this.setPreferredSize(new Dimension(
        		Styling.TASK_BOARD_WIDTH - Styling.MAIN_TOOLBAR_WIDTH - 10,
        		Styling.TASK_BOARD_HEIGHT));
        this.setMinimumSize(new Dimension(700,400));
    }
    
    /**
     * Sort tasks by date.
     
    private void sortTasksByDate()
    {
        tasksP.removeAll();
        System.out.println("here");
        tasksP.revalidate();
        tasksP.repaint();
    }*/
    
    /**
     * Change state.
     */
    public void changeState(){
    	this.state = !state;
    }
    
    /**
     * Adds the new task.
     *
     * @param tc the tc
     */
    public void addNewTask(Task task) {
    	this.tasksP.add(new TaskPanel(task,this.topHomePanel));
    	this.tasksP.revalidate();
    	this.revalidate();
    	this.oldWidth = this.getWidth();
    	this.oldHeight = this.getHeight();
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics graph){
    	if(this.oldWidth != this.getWidth() || this.oldHeight != this.getHeight()) {
        	Image img = ((ImageIcon) this.getIcon()).getImage();
        	img = img.getScaledInstance(this.getWidth(), this.getHeight(),
        			java.awt.Image.SCALE_DEFAULT);
        	this.setIcon(new ImageIcon(img));
        	this.oldWidth = this.getWidth();
        	this.oldHeight = this.getHeight();
    	}
    	
    	super.paint(graph);
    }



	@Override
	public void update(java.util.Observable o, Object arg) {
		updateTaskBoard();		
	}
}
