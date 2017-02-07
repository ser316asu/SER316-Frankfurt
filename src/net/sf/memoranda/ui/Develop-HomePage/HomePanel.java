/**
Joshua Becker
**/
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
public class HomePanel extends JPanel
{
    public static final Color BACKGROUND = new Color(113, 114, 106);

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
            this.setBackground(HomePanel.BACKGROUND);
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