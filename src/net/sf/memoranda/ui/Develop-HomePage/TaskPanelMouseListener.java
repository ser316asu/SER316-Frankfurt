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

public class TaskPanelMouseListener implements MouseListener
{
    private short clickCount;
    private long firstClickTime;
    private TaskPanel target;
    private TopHomePanel topPanel;
    public TaskPanelMouseListener()
    {
        clickCount = 0;
    }
    
    public TaskPanelMouseListener(TaskPanel target, TopHomePanel thp)
    {
        this.topPanel = thp;
        this.target = target;
        clickCount = 0;
    }
    /**
    * Invoked when the mouse button has been clicked (pressed and released) on a component.
    * @param e: MouseEvent
    * @return
    **/
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(clickCount == 0)
        {
            firstClickTime = System.currentTimeMillis();
            clickCount++;
            System.out.println("one Clicked");
        }else if(firstClickTime+1000 > System.currentTimeMillis() && clickCount == 1)
        {
            System.out.println("double Clicked");
            clickCount = 0;
            target.getTaskCard().setActive(true);
        }else
        {
            clickCount = 0;
        }
    } 
    /**
    * Invoked when the mouse enters a component.
    * @param e: MouseEvent
    * @return
    **/
    @Override
    public void mouseEntered(MouseEvent e)
    {
        
    }
    /**
    * Invoked when the mouse exits a component.
    * @param e: MouseEvent
    * @return
    **/
    @Override
    public void mouseExited(MouseEvent e)
    {
        
    }
    /**
    * Invoked when a mouse button has been pressed on a component.
    * @param e: MouseEvent
    * @return
    **/
    @Override
    public void mousePressed(MouseEvent e)
    {
        
    }
    /**
    * Invoked when a mouse button has been released on a component.
    * @param e: MouseEvent
    * @return
    **/
    @Override
    public void mouseReleased(MouseEvent e)
    {
        
    }
}