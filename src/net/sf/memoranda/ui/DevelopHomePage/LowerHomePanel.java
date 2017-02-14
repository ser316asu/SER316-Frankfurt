package net.sf.memoranda.ui.DevelopHomePage;
/**

**/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.Date;
import java.util.*;
import java.awt.Insets;
import java.util.Arrays;

public class LowerHomePanel extends JLabel implements Styling
{
    private Hashtable<String,TaskCard> tasks;
	private final double lowRatio = .6;
    private JPanel tasks_P, addtasks_P, toolBar_P;
    private Dimension dimension;
    private JButton addTask_B;
    private JComboBox<String> sortTasks_CB;
    private final String [] comboCommands;
    private final TopHomePanel topHomePanel;

    public LowerHomePanel()
    {
        topHomePanel = null;
        tasks = new Hashtable<String,TaskCard>();
        comboCommands = new String [] {"sort by", "start date", "name", "date created", "est LOC", "est hours", "priority"};
        this.dimension = new Dimension(Develop.SCREEN_WIDTH -5,((int) (Develop.SCREEN_HEIGHT*lowRatio))-7);
        
    	createComponents();
        style();
    	editComponents();
    	addActionListeners();
    	addComponents();
    }
    public LowerHomePanel(TopHomePanel thp, Hashtable<String,TaskCard> tasks)
    {
        this.topHomePanel = thp;
        this.tasks = tasks;
        comboCommands = new String [] {"sort by", "start date", "name", "date created", "est LOC", "est hours", "priority"};
        this.dimension = new Dimension(Styling.TASK_BOARD_WIDTH,Styling.TASK_BOARD_HEIGHT);
        
    	createComponents();
        style();
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
          sortTasks_CB = new JComboBox<String>(comboCommands);
    }
    public void editComponents()
    {            
          this.setLayout(new OverlayLayout(this));
          tasks_P.setLayout(new FlowLayout(FlowLayout.CENTER,10,35));
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
          sortTasks_CB.addActionListener(new ComboListener());
    }
    public void addComponents()
    {
          for(TaskCard tc : tasks.values())
          {
              tasks_P.add(new TaskPanel(Styling.TASK_PANEL_WIDTH,Styling.TASK_PANEL_HEIGHT,tc, topHomePanel),JLayeredPane.DRAG_LAYER);
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
    public void style()
    {
        this.setBackground(Styling.BACKGROUND_COLOR);
        sortTasks_CB.setBackground(Styling.BACKGROUND_COLOR);
        sortTasks_CB.setForeground(Styling.LABEL_PANEL_TEXT_COLOR);
        this.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR));
        //tasks_P.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR)); 
        this.setIcon(LoadAssets.TASK_BOARD_IMAGE);
    }
    private void sortTasksByName()
    {
        tasks_P.removeAll();
        System.out.println("here");
        tasks_P.revalidate();
        tasks_P.repaint();
        Object [] tmp = this.tasks.keySet().toArray();
        Arrays.sort(tmp);
        for(int i = 0; i < 10; i++)
        {
            tasks_P.add(new TaskPanel(320,220,this.tasks.get((String) tmp[i]), topHomePanel),JLayeredPane.DRAG_LAYER);
            System.out.println(((String) tmp[i]) + " asfd");
        }
    }
    private void sortTasksByDate()
    {
        tasks_P.removeAll();
        System.out.println("here");
        tasks_P.revalidate();
        tasks_P.repaint();
    }
    /**Listeners
	* Once an event occurs the program goes here
	* and decides what to do with each event.
	*
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
    private class ComboListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String command = (String) sortTasks_CB.getSelectedItem();
            //System.out.println(command + "  " + sortTasks_CB.getSelectedItem());
			switch(command)
			{
                case "name":
                        sortTasksByName();
                    break;
                case "start date":
                    break;
			// create default error message
			}
		}
	}
}
