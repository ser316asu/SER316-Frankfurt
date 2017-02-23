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
import javax.imageio.ImageIO;
/**

**/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Hashtable;
import java.util.Date;
import java.util.*;
import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * The Class LowerHomePanel.
 */
public class LowerHomePanel extends JLabel implements Styling
{
    
    /** The tasks. */
    private Hashtable<String,TaskCard> tasks;
	
	/** The low ratio. */
	private final double lowRatio = .6;
    
    /** The tool bar P. */
    private JPanel tasks_P, addtasks_P, toolBar_P;
    
    /** The dimension. */
    private Dimension dimension;
    
    /** The add task B. */
    private JButton addTask_B;
    
    /** The sort tasks CB. */
    private JComboBox<String> sortTasks_CB;
    
    /** The combo commands. */
    private final String [] comboCommands;
    
    /** The top home panel. */
    private final TopHomePanel topHomePanel;
    
    /** The old height. */
    private int oldWidth, oldHeight;
    
    /** The state. */
    private boolean state;

    /**
     * Instantiates a new lower home panel.
     */
    public LowerHomePanel()
    {
        topHomePanel = null;
        tasks = new Hashtable<String,TaskCard>();
        comboCommands = new String [] {"sort by", "start date", "name", "date created", "est LOC", "est hours", "priority"};
        this.dimension = new Dimension(Develop.SCREEN_WIDTH -5,((int) (Develop.SCREEN_HEIGHT*lowRatio))-7);
        
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
    public LowerHomePanel(TopHomePanel thp, Hashtable<String,TaskCard> tasks)
    {
        this.topHomePanel = thp;
        this.tasks = tasks;
        comboCommands = new String [] {"sort by", "start date", "name", "date created", "est LOC", "est hours", "priority"};
        this.dimension = new Dimension(Styling.TASK_BOARD_WIDTH,Styling.TASK_BOARD_HEIGHT);
        
    	createComponents();
        style();
    	editComponents();
    	addActionListeners();
    	addComponents();
    }


    /**
     * Creates the components.
     */
    public void createComponents()
    {
          tasks_P = new JPanel();
          addTask_B = new JButton("+");
          addtasks_P = new JPanel();
          toolBar_P = new JPanel();
          sortTasks_CB = new JComboBox<String>(comboCommands);
    }
    
    /**
     * Edits the components.
     */
    public void editComponents()
    {            
          this.setLayout(new OverlayLayout(this));
          tasks_P.setLayout(new FlowLayout(FlowLayout.CENTER,10,35));
          toolBar_P.setLayout(new BorderLayout());
          addtasks_P.setLayout(new FlowLayout(FlowLayout.RIGHT));
          
          this.setPreferredSize(dimension);
          this.setMaximumSize(new Dimension(Styling.SCREEN_WIDTH-Styling.MAIN_TOOLBAR_WIDTH,Styling.SCREEN_HEIGHT));
          addTask_B.setPreferredSize(new Dimension(20,25));
          tasks_P.setPreferredSize(dimension);
          toolBar_P.setPreferredSize(dimension);
          sortTasks_CB.setPreferredSize(new Dimension(150,25));
          
          tasks_P.setMinimumSize(dimension);
          tasks_P.setMaximumSize(dimension);
          toolBar_P.setMinimumSize(dimension);
          toolBar_P.setMaximumSize(dimension);
          
          setTransparent(addtasks_P);
          setTransparent(tasks_P);
          setTransparent(toolBar_P);
          
          addTask_B.setMargin(new Insets(0,0,0,0));
    }
    
    /**
     * Adds the action listeners.
     */
    public void addActionListeners()
    {
          addTask_B.addActionListener(new ButtonListener());
          sortTasks_CB.addActionListener(new ComboListener());
    }
    
    /**
     * Adds the components.
     */
    public void addComponents()
    {
          for(TaskCard tc : tasks.values())
          {
              tasks_P.add(new TaskPanel(Styling.TASK_PANEL_WIDTH,Styling.TASK_PANEL_HEIGHT,tc, topHomePanel),JLayeredPane.DRAG_LAYER);
          }
          
          //addtasks_P.add(sortTasks_CB);
          //addtasks_P.add(addTask_B);
          toolBar_P.add(addtasks_P, BorderLayout.NORTH);
          
          
          this.add(tasks_P);
          this.add(toolBar_P);
          toolBar_P.revalidate();
          tasks_P.revalidate();
          addTask_B.revalidate();
          this.revalidate();

          
    }
    
    /**
     * Sets the transparent.
     *
     * @param panel the new transparent
     */
    private void setTransparent(JPanel panel)
    {
        panel.setOpaque(false);
        panel.setBackground(new Color(0,0,0,255));
    }
    
    /* (non-Javadoc)
     * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
     */
    public void style()
    {
        this.setBackground(Styling.BACKGROUND_COLOR);
        sortTasks_CB.setBackground(Styling.BACKGROUND_COLOR);
        sortTasks_CB.setForeground(Styling.LABEL_PANEL_TEXT_COLOR);
        this.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR));
        //tasks_P.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR)); 
        this.setIcon(LoadAssets.TASK_BOARD_IMAGE);
    }
    
    /**
     * Sort tasks by name.
     */
    private void sortTasksByName()
    {
        tasks_P.removeAll();
        System.out.println("here");
        tasks_P.revalidate();
        tasks_P.repaint();
        Object [] tmp = this.tasks.keySet().toArray();
        Arrays.sort(tmp);
        for(int i = 0; i < 10; i++)
        {
            tasks_P.add(new TaskPanel(320,220,this.tasks.get((String) tmp[i]), topHomePanel),JLayeredPane.DRAG_LAYER);
            System.out.println(((String) tmp[i]) + " asfd");
        }
    }
    
    /**
     * Sort tasks by date.
     */
    private void sortTasksByDate()
    {
        tasks_P.removeAll();
        System.out.println("here");
        tasks_P.revalidate();
        tasks_P.repaint();
    }
    
    /**
     * Change state.
     */
    public void changeState()
    {
    	this.state = !state;
    }
    
    /**
     * The listener interface for receiving button events.
     * The class that is interested in processing a button
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addButtonListener<code> method. When
     * the button event occurs, that object's appropriate
     * method is invoked.
     *
     * @see ButtonEvent
     */
	private class ButtonListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent event)
		{
			String command = event.getActionCommand();
			switch(command)
			{
				case "addTask":
                    //Insert Add Task Window HERE...
					break;
			}
		}  
	}
    
    /**
     * The listener interface for receiving combo events.
     * The class that is interested in processing a combo
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addComboListener<code> method. When
     * the combo event occurs, that object's appropriate
     * method is invoked.
     *
     * @see ComboEvent
     */
    private class ComboListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent event)
		{
			String command = (String) sortTasks_CB.getSelectedItem();
            //System.out.println(command + "  " + sortTasks_CB.getSelectedItem());
			switch(command)
			{
                case "name":
                        sortTasksByName();
                    break;
                case "start date":
                    break;
			// create default error message
			}
		}
	}
    
    /**
     * Adds the new task.
     *
     * @param tc the tc
     */
    public void addNewTask(TaskCard tc)
    {
    	this.tasks_P.add(new TaskPanel(tc,this.topHomePanel));
    	this.tasks_P.revalidate();
    	this.revalidate();
    	this.oldWidth = this.getWidth();
    	this.oldHeight = this.getHeight();
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g)
    {
    	if(this.oldWidth != this.getWidth() || this.oldHeight != this.getHeight())
    	{
        	Image img = ((ImageIcon) this.getIcon()).getImage();
        	img = img.getScaledInstance(this.getWidth(), this.getHeight(),  java.awt.Image.SCALE_SMOOTH);
        	this.setIcon(new ImageIcon(img));
        	this.oldWidth = this.getWidth();
        	this.oldHeight = this.getHeight();
    	}
    	
    	super.paint(g);
    }
}
