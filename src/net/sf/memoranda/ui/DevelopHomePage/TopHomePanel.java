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
    private JLabel containerForRight;
    private StatusBarPanel statusBarTop;
    private TimerPanel timerPanel;
    private TaskCard task;
    private Dimension dimension;
    private LayoutManager layout;
    


    public TopHomePanel(TaskCard task)
    {
        this.task = task;
        layout = new OverlayLayout(this);
        setSize();
        this.setLayout(layout);
        createChildPanels();
        style();
        addPanels();  


    }

    public void createChildPanels(){
    	containerForRight = new JLabel();
    	containerForRight.setLayout(new BoxLayout(containerForRight,BoxLayout.Y_AXIS));
    	containerForRight.setOpaque(false);
    	containerForRight.setPreferredSize(new Dimension(Styling.PROGRESS_PANEL_WIDTH,Styling.TOP_PANEL_HEIGHT-1));
    	containerForRight.setMaximumSize(new Dimension(Styling.PROGRESS_PANEL_WIDTH,Styling.TOP_PANEL_HEIGHT-1));
    	
        leftChild = new TopInnerPanel(Styling.TERMINAL_PANEL_WIDTH, Styling.TERMINAL_PANEL_HEIGHT);
        rightChild = new TopInnerPanel(Styling.TERMINAL_RIGHT_PANEL_WIDTH,Styling.TERMINAL_RIGHT_PANEL_HEIGHT);
        rightChild.setLayout(new BorderLayout());
        //rightChild.setLocation(Styling.TERMINAL_PANEL_WIDTH,0);

        //leftLabels = new TopLabelPanel(task,TopHomePanel.LEFT_LABEL_PANEL);
        //rightLabels = new TopLabelPanel(task,TopHomePanel.RIGHT_LABEL_PANEL);
        
        leftLabels = new TopLabelPanel(TopHomePanel.LEFT_LABEL_PANEL);
        rightLabels = new TopLabelPanel(TopHomePanel.RIGHT_LABEL_PANEL);

        statusBarTop = new StatusBarPanel(rightChild,task);
        timerPanel = new TimerPanel(rightChild,task);

        task.addObserver(leftLabels);
        task.addObserver(rightLabels);
        task.addObserver(statusBarTop);
    }

    public void addPanels(){
        leftChild.add(leftLabels);
        leftChild.add(rightLabels);

        containerForRight.add(statusBarTop);
        //rightChild.add(horizontalSeparator);
        //rightChild.add(new JSeparator(SwingConstants.HORIZONTAL));
        containerForRight.add(timerPanel);
        rightChild.add(containerForRight, BorderLayout.EAST);
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
    	this.setMaximumSize(new Dimension(Styling.SCREEN_WIDTH-Styling.MAIN_TOOLBAR_WIDTH,Styling.SCREEN_HEIGHT));
        this.setBackground(Styling.BACKGROUND_COLOR);
        leftChild.setLayout(new GridLayout(1,2,0,0));
        rightChild.setLayout(new BorderLayout(0,0));//rightChild.setLayout(new GridLayout(2,1,3,3));//new BoxLayout(rightChild,BoxLayout.Y_AXIS));
        rightChild.setBackground(Styling.TIMER_PANEL_COLOR);
        this.setIcon(LoadAssets.TERMINAL_IMAGE);
        //rightChild.setIcon(LoadAssets.TERMINAL_IMAGE);
        
        
        //rightChild.setPreferredSize(new Dimension(Styling.TERMINAL_RIGHT_PANEL_WIDTH,Styling.TERMINAL_RIGHT_PANEL_HEIGHT));
        //rightChild.setBorder(BorderFactory.createLineBorder(Color.red));
        //containerForRight.setBorder(BorderFactory.createLineBorder(Color.green));
        }
    
    public void addObservers(TaskCard tc)
    {
    	tc.addObserver(leftLabels);
    	tc.addObserver(rightLabels);
    	tc.addObserver(statusBarTop);
    }
}
