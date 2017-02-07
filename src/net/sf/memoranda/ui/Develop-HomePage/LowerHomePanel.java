/**

**/
import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class LowerHomePanel extends JPanel
{
    private Hashtable<String,TaskCard> tasks;
	private final double lowRatio = .6;

    public LowerHomePanel()
    {
        tasks = new Hashtable<String,TaskCard>();
        fillTasks();
    	createComponents();
    	editComponents();
    	addActionListeners();
    	addComponents();
    }


    	public void createComponents()
    	{

    	}
    	public void editComponents()
    	{
            this.setBorder(BorderFactory.createLineBorder(Color.black));
            this.setBackground(HomePanel.BACKGROUND);
            
            this.setPreferredSize(new Dimension(Develop.SCREEN_WIDTH - 100,((int) (Develop.SCREEN_HEIGHT*lowRatio)) - 25));
    	}
    	public void addActionListeners()
    	{

    	}
    	public void addComponents()
    	{
            //this.add(new JLabel("Lower"));
            for(int i = 0; i < 5; i++)
            {
                this.add(new TaskPanel(200,300,this.tasks.get("task " + i)));
            }
    	}
        public void fillTasks()
        {
            TaskCard tmpTask;
            for(int i = 0; i < 5; i++)
            {
                tmpTask = new TaskCard();
                tmpTask.setTaskName("task " + i);
                this.tasks.put(tmpTask.getTaskName(), tmpTask);
            }
        }
}
