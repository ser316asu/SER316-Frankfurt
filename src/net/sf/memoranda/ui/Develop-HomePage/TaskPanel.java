/**
swhacks.org
**/
import javax.swing.*;
import java.awt.*;
public class TaskPanel extends JPanel
{
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
            this.setBorder(BorderFactory.createLineBorder(Color.black));
            this.setPreferredSize(new Dimension(width,height));
            this.setBackground(Color.gray);
    	}
    	public void addActionListeners()
    	{

    	}
    	public void addComponents()
    	{
    	}
}
