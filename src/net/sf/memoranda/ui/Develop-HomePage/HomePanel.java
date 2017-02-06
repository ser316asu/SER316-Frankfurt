/**
Joshua Becker
**/
import javax.swing.*;
import java.awt.Dimension;
public class HomePanel extends JPanel
{
	private TopHomePanel top_P;
	private LowerHomePanel low_P;
	private final double topRatio = .4;
	private final double lowRatio = .6;
    private TaskCard task;
    public HomePanel()
    {
    	//this.super();

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
        addComponents();
    }


    	public void createComponents()
    	{
    		top_P = new TopHomePanel(task);
    		low_P = new LowerHomePanel();
    	}
    	public void editComponents()
    	{

    		//top_P.setPreferredSize(new Dimension(Develop.SCREEN_WIDTH - 100,((int) (Develop.SCREEN_HEIGHT*topRatio)) - 5));
    		//low_P.setPreferredSize(new Dimension(Develop.SCREEN_WIDTH - 100,((int) (Develop.SCREEN_HEIGHT*lowRatio)) - 5));

    	}
    	public void addActionListeners()
    	{

    	}
    	public void addComponents()
    	{
    		this.add(top_P);
    		this.add(low_P);
    	}
}