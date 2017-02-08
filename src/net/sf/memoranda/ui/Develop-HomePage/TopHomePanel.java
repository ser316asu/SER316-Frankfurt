/**
*@author Alec Shinn, Josh Becker
**/
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class TopHomePanel extends JPanel implements Styling
{
	private final int THP_HEIGHT = (int) (Develop.SCREEN_HEIGHT * .4) - 5;
    private final int THP_WIDTH = Develop.SCREEN_WIDTH - 100;
    private final double TIP_LEFT_WIDTH_RATIO = .6;
    private final double TIP_RIGHT_WIDTH_RATIO = .4;
    final static int LEFT_LABEL_PANEL = 0, RIGHT_LABEL_PANEL = 1;
    private final String AHEAD = "Ahead of Schedule";
    private final String BEHIND = "Behind Schedule";
    private final String ONTIME = "On Time";
    private TopInnerPanel leftChild,rightChild;
    private TopLabelPanel leftLabels,rightLabels;
    private StatusBarPanel statusBarTop,statusBarBottom;
    private TaskCard task;
    private Dimension dimension;
    private LayoutManager layout;
    private JSeparator verticalSeparator,horizontalSeparator;
       


    public TopHomePanel(TaskCard task)
    {
        this.task = task;
        layout = new FlowLayout(FlowLayout.LEFT,1,1);
        verticalSeparator = new JSeparator(JSeparator.VERTICAL);
        horizontalSeparator = new JSeparator(JSeparator.HORIZONTAL);
        setSize();
        this.setLayout(layout);
        createChildPanels();
        style();
        addPanels();       
    }

    public void createChildPanels(){
        leftChild = new TopInnerPanel(this,task);
        rightChild = new TopInnerPanel(this,task);

        leftChild.setSize(TIP_LEFT_WIDTH_RATIO,1.0);
        rightChild.setSize(TIP_RIGHT_WIDTH_RATIO,1.0);

        leftChild.setLayout(new GridLayout(1,2,0,0));
        rightChild.setLayout(new GridLayout(2,1,3,3));//new BoxLayout(rightChild,BoxLayout.Y_AXIS));


        leftLabels = new TopLabelPanel(leftChild,task,TopHomePanel.LEFT_LABEL_PANEL);
        rightLabels = new TopLabelPanel(rightChild,task,TopHomePanel.RIGHT_LABEL_PANEL);

        statusBarTop = new StatusBarPanel(rightChild,task);
        statusBarBottom = new StatusBarPanel(rightChild,task);
    }

    public void addPanels(){
        leftChild.add(leftLabels);
        leftChild.add(rightLabels);

        rightChild.add(statusBarTop);
        //rightChild.add(horizontalSeparator);
        //rightChild.add(new JSeparator(SwingConstants.HORIZONTAL));
        rightChild.add(statusBarBottom);
        //rightChild.add(horizontalSeparator);

        this.add(leftChild);
        //this.add(verticalSeparator);
        this.add(rightChild);
    }

    public void setSize(){
        dimension = new Dimension(THP_WIDTH,THP_HEIGHT);
        this.setPreferredSize(dimension);
    }

    public Dimension getDimension(){
        return dimension;
    }

    public void style(){
        this.setBackground(Styling.BACKGROUND_COLOR);

        verticalSeparator.setBackground(Color.black);
        verticalSeparator.setForeground(Color.black);

        horizontalSeparator.setMinimumSize(new Dimension(500,50));
        horizontalSeparator.setPreferredSize(new Dimension(100,50));
        
        horizontalSeparator.setForeground(Color.black);
        horizontalSeparator.setBackground(Color.black);

        //this.setBorder(BorderFactory.createLineBorder(Color.black));
    }


    
   
    
}
