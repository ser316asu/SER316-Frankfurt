/**
swhacks.org
**/
import javax.swing.*;
import java.awt.*;

public class TaskPanel extends JPanel
{
    private Dimension dimension;
    private TaskCard task;
    private JLabel name_L;
    public TaskPanel()
    {
        this.dimension = new Dimension(200,400);
    	createComponents();
    	editComponents();
    	addActionListeners();
    	addComponents();
    }
        public TaskPanel(int height,int width, TaskCard card)
    {
        this.dimension = new Dimension(height, width);
        this.task = card;
        createComponents();
        editComponents();
        addActionListeners();
        addComponents();
    }


    	public void createComponents()
    	{
            name_L = new JLabel(this.task.getTaskName());
    	}
    	public void editComponents()
    	{
            this.setBorder(BorderFactory.createLineBorder(Color.black));
            this.setPreferredSize(this.dimension);
            this.setBackground(Color.gray);
    	}
    	public void addActionListeners()
    	{

    	}
    	public void addComponents()
    	{
            this.add(name_L);
    	}
        public void resetSize()
        {
            this.setPreferredSize(this.dimension);
            this.revalidate();
        }
        public int getHeight()
        {
            return this.dimension.height;
        }
        public int getWidth()
        {
            return this.dimension.width;
        }
        public void setWidth(int width)
        {
            this.dimension.width = width;
        }
        public void setHeight(int height)
        {
            this.dimension.height = height;
        }
        public TaskCard getTaskCard()
        {
            return this.task;
        }
        public void setTaskCard(TaskCard task)
        {
            this.task = task;
        }
}
