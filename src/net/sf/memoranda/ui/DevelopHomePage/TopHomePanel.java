package net.sf.memoranda.ui.DevelopHomePage;
/**

*@author Alec Shinn, Josh Becker
**/
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.*;

public class TopHomePanel extends JLabel implements Styling
{
    final static int LEFT_LABEL_PANEL = 0, RIGHT_LABEL_PANEL = 1;
    private TopInnerPanel leftChild,rightChild;
    private TopLabelPanel leftLabels,rightLabels;
    private StatusBarPanel statusBarTop;
    private TimerPanel timerPanel;
    private TaskCard task;
    private Dimension dimension;
    private LayoutManager layout;
    


    public TopHomePanel(TaskCard task)
    {
        this.task = task;
        layout = new FlowLayout(FlowLayout.LEFT,1,1);
        setSize();
        this.setLayout(layout);
        createChildPanels();
        style();
        addPanels();  


    }

    public void createChildPanels(){
        leftChild = new TopInnerPanel(Styling.TERMINAL_PANEL_WIDTH, Styling.TERMINAL_PANEL_HEIGHT);
        rightChild = new TopInnerPanel(Styling.TERMINAL_RIGHT_PANEL_WIDTH,Styling.TERMINAL_RIGHT_PANEL_HEIGHT);

        leftLabels = new TopLabelPanel(task,TopHomePanel.LEFT_LABEL_PANEL);
        rightLabels = new TopLabelPanel(task,TopHomePanel.RIGHT_LABEL_PANEL);

        statusBarTop = new StatusBarPanel(rightChild,task);
        timerPanel = new TimerPanel(rightChild,task);

        task.addObserver(leftLabels);
        task.addObserver(rightLabels);
        task.addObserver(statusBarTop);
    }

    public void addPanels(){
        leftChild.add(leftLabels);
        leftChild.add(rightLabels);

        rightChild.add(statusBarTop);
        //rightChild.add(horizontalSeparator);
        //rightChild.add(new JSeparator(SwingConstants.HORIZONTAL));
        rightChild.add(timerPanel);
        //rightChild.add(horizontalSeparator);

        this.add(leftChild);
        //this.add(verticalSeparator);
        this.add(rightChild);
    }

    public void setSize(){
        dimension = new Dimension(Styling.TOP_PANEL_WIDTH,Styling.TOP_PANEL_HEIGHT);
        this.setPreferredSize(dimension);
    }

    public Dimension getDimension(){
        return dimension;
    }

    public void style(){
        this.setBackground(Styling.BACKGROUND_COLOR);
        leftChild.setLayout(new GridLayout(1,2,0,0));
        rightChild.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));//rightChild.setLayout(new GridLayout(2,1,3,3));//new BoxLayout(rightChild,BoxLayout.Y_AXIS));
        rightChild.setBackground(Styling.TIMER_PANEL_COLOR);
        this.setIcon(LoadAssets.TERMINAL_IMAGE);
        //rightChild.setIcon(LoadAssets.TERMINAL_IMAGE);
        
        
        //rightChild.setPreferredSize(new Dimension(Styling.TERMINAL_RIGHT_PANEL_WIDTH,Styling.TERMINAL_RIGHT_PANEL_HEIGHT));
        //this.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR));
    }
    
    public void addObservers(TaskCard tc)
    {
    	tc.addObserver(leftLabels);
    	tc.addObserver(rightLabels);
    	tc.addObserver(statusBarTop);
    }
}
