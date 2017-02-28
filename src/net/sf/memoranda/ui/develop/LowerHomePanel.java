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
import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

// TODO: Auto-generated Javadoc
/**
 * The Class LowerHomePanel.
 */
public class LowerHomePanel extends JLabel implements Styling {
    
	/** auto generated serialversion UID. */
	private static final long serialVersionUID = -8015237809090141365L;

	/** The tasks. */
	private Hashtable<String,TaskCard> tasks;
    
    /** The tool bar P. */
    private JPanel addTasksP;
    
    /** The tool bar P. */
    private JPanel toolBarP;
    
    /** The tasks P. */
    private JPanel tasksP;
    
    /** The top home panel. */
    private final TopHomePanel topHomePanel;
    
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
        tasks = new Hashtable<String,TaskCard>();
        
    	createComponents();
        style();
    	editComponents();
    	addActionListeners();
    	addComponents();
    }
    
    /**
     * Instantiates a new lower home panel.
     *
     * @param thp the thp
     * @param tasks the tasks
     */
    public LowerHomePanel(TopHomePanel thp, Hashtable<String,TaskCard> tasks){
        this.topHomePanel = thp;
        this.tasks = tasks;
    	createComponents();
        style();
    	editComponents();
    	addActionListeners();
    	addComponents();
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
          for(TaskCard tc : tasks.values()){
              tasksP.add(new TaskPanel(Styling.TASK_PANEL_WIDTH,Styling.TASK_PANEL_HEIGHT,tc,
            		  topHomePanel),JLayeredPane.DRAG_LAYER);
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
    public void addNewTask(TaskCard tc) {
    	this.tasksP.add(new TaskPanel(tc,this.topHomePanel));
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
}
