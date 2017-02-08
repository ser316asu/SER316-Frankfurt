/**
*@author Alec Shinn, Josh Becker
**/
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class TopHomePanel extends JPanel
{
	private final int THP_HEIGHT = (int) (Develop.SCREEN_HEIGHT * .4) - 5;
    private final int THP_WIDTH = Develop.SCREEN_WIDTH - 100;
    private final double TIP_LEFT_WIDTH_RATIO = .6;
    private final double TIP_RIGHT_WIDTH_RATIO = .4;
    
    private final String AHEAD = "Ahead of Schedule";
    private final String BEHIND = "Behind Schedule";
    private final String ONTIME = "On Time";
    final static int LEFT_LABEL_PANEL = 0, RIGHT_LABEL_PANEL = 1;
    private TopInnerPanel leftChild,rightChild;
    private TopLabelPanel leftLabels,rightLabels;
    private StatusBarPanel statusBarTop,statusBarBottom;
    private TaskCard task;
    private Dimension dimension;
    private BoxLayout layout;
       


    public TopHomePanel(TaskCard task)
    {
        this.task = task;
        layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setSize();
        this.setLayout(layout);
        createChildPanels();
        addPanels();

    	      
        
    }

    public void createChildPanels(){
        leftChild = new TopInnerPanel(this,task);
        rightChild = new TopInnerPanel(this,task);

        leftChild.setSize(TIP_LEFT_WIDTH_RATIO,1.0);
        rightChild.setSize(TIP_RIGHT_WIDTH_RATIO,1.0);

        leftChild.setLayout(new BoxLayout(leftChild,BoxLayout.X_AXIS));
        rightChild.setLayout(new BoxLayout(rightChild,BoxLayout.Y_AXIS));

        leftLabels = new TopLabelPanel(leftChild,task,TopHomePanel.LEFT_LABEL_PANEL);
        rightLabels = new TopLabelPanel(rightChild,task,TopHomePanel.RIGHT_LABEL_PANEL);

        statusBarTop = new StatusBarPanel(rightChild,task);
        statusBarBottom = new StatusBarPanel(rightChild,task);
    }

    public void addPanels(){
        leftChild.add(leftLabels);
        leftChild.add(rightLabels);

        rightChild.add(statusBarTop);
        rightChild.add(statusBarBottom);

        this.add(leftChild);
        this.add(rightChild);
    }

    public void setSize(){
        dimension = new Dimension(THP_WIDTH,THP_HEIGHT);
        this.setPreferredSize(dimension);
    }

    public Dimension getDimension(){
        return dimension;
    }
   


    
   
    
}
