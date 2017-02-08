/**

**/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.Date;
import java.awt.Insets;

public class LowerHomePanel extends JPanel
{
    private Hashtable<String,TaskCard> tasks;
	private final double lowRatio = .6;
    private JPanel tasks_P, addtasks_P, toolBar_P;
    private Dimension dimension;
    private JButton addTask_B;
    private JComboBox sortTasks_CB;
    private final String [] comboCommands;

    public LowerHomePanel()
    {
        tasks = new Hashtable<String,TaskCard>();
        comboCommands = new String [] {"sort by", "end date", "name", "date created", "est LOC", "est hours", "priority"};
        this.dimension = new Dimension(Develop.SCREEN_WIDTH - 100,((int) (Develop.SCREEN_HEIGHT*lowRatio)) - 25);
        fillTasks();
    	createComponents();
    	editComponents();
    	addActionListeners();
    	addComponents();
    }


    	public void createComponents()
    	{
            tasks_P = new JPanel();
            addTask_B = new JButton("+");
            addtasks_P = new JPanel();
            toolBar_P = new JPanel();
            sortTasks_CB = new JComboBox(comboCommands);
    	}
    	public void editComponents()
    	{
            this.setBorder(BorderFactory.createLineBorder(Color.black));
            tasks_P.setBorder(BorderFactory.createLineBorder(Color.black));
            
            this.setBackground(HomePanel.BACKGROUND);
            sortTasks_CB.setBackground(HomePanel.BACKGROUND);
            
            
            this.setLayout(new OverlayLayout(this));
            tasks_P.setLayout(new FlowLayout(FlowLayout.LEFT,5,40));
            toolBar_P.setLayout(new BorderLayout());
            addtasks_P.setLayout(new FlowLayout(FlowLayout.RIGHT));
            
            this.setPreferredSize(dimension);
            addTask_B.setPreferredSize(new Dimension(20,25));
            tasks_P.setPreferredSize(dimension);
            toolBar_P.setPreferredSize(dimension);
            sortTasks_CB.setPreferredSize(new Dimension(150,25));
            
            tasks_P.setMinimumSize(dimension);
            tasks_P.setMaximumSize(dimension);
            toolBar_P.setMinimumSize(dimension);
            toolBar_P.setMaximumSize(dimension);
            
            setTransparent(addtasks_P);
            setTransparent(tasks_P);
            setTransparent(toolBar_P);
            
            addTask_B.setMargin(new Insets(0,0,0,0));
    	}
    	public void addActionListeners()
    	{
            addTask_B.addActionListener(new ButtonListener());
    	}
    	public void addComponents()
    	{
            for(int i = 0; i < 5; i++)
            {
                tasks_P.add(new TaskPanel(300,200,this.tasks.get("task " + i)),JLayeredPane.DRAG_LAYER);
            }
            
            addtasks_P.add(sortTasks_CB);
            addtasks_P.add(addTask_B);
            toolBar_P.add(addtasks_P, BorderLayout.NORTH);
            
            
            this.add(tasks_P);
            this.add(toolBar_P);
            toolBar_P.revalidate();
            tasks_P.revalidate();
            addTask_B.revalidate();
            this.revalidate();

            
    	}
        private void setTransparent(JPanel panel)
        {
            panel.setOpaque(false);
            panel.setBackground(new Color(0,0,0,255));
        }
        public void fillTasks()
        {
            TaskCard tmpTask;
            for(int i = 0; i < 5; i++)
            {
                tmpTask = new TaskCard();
                tmpTask.setTaskName("task " + i);
                tmpTask.setEndDate(new Date());
                this.tasks.put(tmpTask.getTaskName(), tmpTask);
            }
        }
    /**Listeners
	* Once an event occurs the program goes here
	* and decides what to do with each event.
	*
	*@peram MenuPanel.
	*@peram nothing.
	* J.B.
	**/
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String command = event.getActionCommand();
			switch(command)
			{
				case "addTask":
                    //Insert Add Task Window HERE...
					break;

			}
		}  
	}
}
