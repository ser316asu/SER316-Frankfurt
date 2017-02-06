/**
swhacks.org
**/
import javax.swing.*;
import java.awt.*;
public class TaskPanel extends JPanel
{
    public static final Color TASKCOLOR = new Color(246, 249, 17);
    public static final Color TASKBORDER = new Color(246, 249, 17);

	private int height, width;
    public TaskPanel()
    {
        height = 200;
        width = 250;
    	createComponents();
    	editComponents();
    	addActionListeners();
    	addComponents();
    }
        public TaskPanel(int height,int width)
    {
        this.height = height;
        this.width = width;
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
            this.setBorder(BorderFactory.createLineBorder(TaskPanel.TASKCOLOR));
            this.setBackground(TaskPanel.TASKCOLOR);
            this.setPreferredSize(new Dimension(width,height));
            
    	}
    	public void addActionListeners()
    	{

    	}
    	public void addComponents()
    	{
    	}
}
