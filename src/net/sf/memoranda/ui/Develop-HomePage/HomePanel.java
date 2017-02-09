/**

**/
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.util.*;
public class HomePanel extends JPanel implements Styling
{
    private TopHomePanel top_P;
	private LowerHomePanel low_P;
	private final double topRatio = .4;
	private final double lowRatio = .6;
    private TaskCard task;
    public HomePanel()
    {
    	createComponents();
    	editComponents();
    	addActionListeners();
    	addComponents();
    }
    public HomePanel(TaskCard task){
        this.task = task;
        createComponents();
        editComponents();
        addActionListeners();
        style();
        addComponents();
    }


    public void createComponents()
    {
    	top_P = new TopHomePanel(task);
    	low_P = new LowerHomePanel();
    }
    public void editComponents()
    {
    }
    public void addActionListeners()
    {

    }
    public void addComponents()
    {
    	this.add(top_P);
    	this.add(low_P);
    }

    public void style()
    {
        this.setBackground(Styling.BACKGROUND_COLOR);
    }
}