/**

**/
import javax.swing.*;
import java.awt.*;
public class TopHomePanel extends JPanel
{
	private final double topRatio = .4;
    public TopHomePanel()
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
            this.setBorder(BorderFactory.createLineBorder(Color.black));
            this.setPreferredSize(new Dimension(Develop.SCREEN_WIDTH - 100,((int) (Develop.SCREEN_HEIGHT*topRatio)) - 5));
    	}
    	public void addActionListeners()
    	{

    	}
    	public void addComponents()
    	{
            this.add(new JLabel("TOP"));
    	}
}
