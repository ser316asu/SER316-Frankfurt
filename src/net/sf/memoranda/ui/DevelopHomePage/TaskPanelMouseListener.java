/**************************************************************
 * Copyright (c) 2017 - 2017, Joshua Becker, All rights reserved
 * SER316-Frankfurt
 * Description:
 */
package net.sf.memoranda.ui.DevelopHomePage;
/**

* @file: TaskPanelMouseListener.java
* @author: Joshua Becker
* @date: 
* @description: Mouse Listener for the taksPanels, this wll allow for a
*               double click on the panel then populate the labels in
*               the top left panel.
* 
**/
import java.awt.event.*;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving taskPanelMouse events.
 * The class that is interested in processing a taskPanelMouse
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addTaskPanelMouseListener<code> method. When
 * the taskPanelMouse event occurs, that object's appropriate
 * method is invoked.
 *
 * @see TaskPanelMouseEvent
 */
public class TaskPanelMouseListener implements MouseListener
{
    
    /** The click count. */
    private short clickCount;
    
    /** The first click time. */
    private long firstClickTime;
    
    /** The target. */
    private TaskPanel target;
    
    /** The task. */
    private TaskCard task;
    
    /** The top panel. */
    private TopHomePanel topPanel;
    
    /**
     * Instantiates a new task panel mouse listener.
     */
    public TaskPanelMouseListener()
    {
        clickCount = 0;
    }
    
    /**
     * Instantiates a new task panel mouse listener.
     *
     * @param target the target
     * @param thp the thp
     */
    public TaskPanelMouseListener(TaskPanel target, TopHomePanel thp)
    {
    	this.topPanel = thp;
        this.target = target;
        this.task = target.getTaskCard();
        this.clickCount = 0;
    }
    
    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     *
     * @param e the e
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        /*(if(clickCount == 0)
        {
            firstClickTime = System.currentTimeMillis();
            clickCount++;
            System.out.println("One Clicked");
        }else if(firstClickTime+1000 > System.currentTimeMillis() && clickCount == 1)
        {
            System.out.println("Double Clicked");
            clickCount = 0;
            this.topPanel.addObservers(task);
            /*
             * setValue: changes the state of the current taskCard and
             * 			 notifies observers (Task Panels)
             * setChangeVar: changes the state of the new TaskCard and 
             * 				 returns itself in order to pass to the 
             * 				 other observers
             * getChangeVar: is used to make a change in order to modify 
             * 				 the state of the observing panels
             */
            /*task.setValue(task.setChangeVar(task.getChangeVar()+1));
        }else
        {
            clickCount = 0;
        }*/
        System.out.println("Clicked");
        clickCount = 0;
        this.topPanel.addObservers(task);
        /*
         * setValue: changes the state of the current taskCard and
         * 			 notifies observers (Task Panels)
         * setChangeVar: changes the state of the new TaskCard and 
         * 				 returns itself in order to pass to the 
         * 				 other observers
         * getChangeVar: is used to make a change in order to modify 
         * 				 the state of the observing panels
         */
        task.setValue(task.setChangeVar(task.getChangeVar()+1));
    	
    } 
    
    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the e
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {
    	target.setIcon(LoadAssets.TASK_PANEL_SELCETED_IMAGE);
    }
    
    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the e
     */
    @Override
    public void mouseExited(MouseEvent e)
    {
    	target.setIcon(LoadAssets.TASK_PANEL_IMAGE);
    }
    
    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the e
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        
    }
    
    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the e
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        
    }
}