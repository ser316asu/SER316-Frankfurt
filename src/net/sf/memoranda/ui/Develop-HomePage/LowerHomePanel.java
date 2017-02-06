/**

**/
import javax.swing.*;
import java.awt.*;
public class LowerHomePanel extends JPanel
{
	private final double lowRatio = .6;
    public LowerHomePanel()
    {

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
            this.setBorder(BorderFactory.createLineBorder(HomePanel.BACKGROUNDCOLOR));
            
            this.setPreferredSize(new Dimension(Develop.SCREEN_WIDTH - 100,((int) (Develop.SCREEN_HEIGHT*lowRatio)) - 25));
    	}
    	public void addActionListeners()
    	{

    	}
    	public void addComponents()
    	{
            //this.add(new JLabel("Lower"));
            for(int i = 0; i < 5; i++)
                this.add(new TaskPanel());
    	}
}
