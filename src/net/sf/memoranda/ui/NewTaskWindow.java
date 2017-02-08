package net.sf.memoranda.ui;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

/** 
 * 
 * @author Jacob Leonard
 *
 */

/*TODO Add setters/getters, incorporate JPanels, Layout, Event/Action-Listeners & dividers.
* 1. Add Button that will actually OPEN this window. This will likely be on several different pages (taskboard, tasklist, calendar)
* 2. Will need to permanently save some data to reopen the next session
* 3. Need to change state functionality (1: ADD 2: INPROG 3: END ADD 4: FINAL RESULTS)
* 
*/

public class NewTaskWindow extends JFrame {
	
	private enum TaskState{ADD, IN_PROG, END_ADD, FINAL_RESULTS};
	
	private int WIDTH;
	private int HEIGHT;
	
	private Container mainPane;
	
	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;
	
	private JTextField name, startDate, endDate; // String
	private JLabel currentState, nameLabel, startDateLabel, endDateLabel;

	// Code Info
	private JTextField locEst, hoursEst, numFiles; 
	
	
	private JButton startStop; // Hoping to only use one button that changes when pressed
	private JLabel trackedMinutes; // Timer with stored data ---> will need XML 
	
	private JTextArea taskDesc;
	
	// Results (These made visible in STATE 3)
	private JTextField actualLoc;
	private JTextField totalHours; 
	private JTextField actualNumFiles;
	
	private TaskState state;
	
	// Default No-Arg Constructor
	public NewTaskWindow(){
		

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int)screenSize.getWidth()/2; // Casting as int
		HEIGHT = (int)screenSize.getHeight()/2;
		
		/* COMPONENTS */
		//Upper-left Quadrant
		name = new JTextField("Name of Task",15);

		startDateLabel = new JLabel("Start Date");
		startDate = new JTextField("Start Date",15);
		startDate.setMaximumSize(new Dimension(100, 1));
		
		endDateLabel = new JLabel("Due Date");
		endDate = new JTextField("Due Date", 10);
		
		
		locEst = new JTextField("LOC Estimate", 10);
		hoursEst = new JTextField("Hours Estimate", 10);
		
		// Upper-right Quadrant
		// TODO Consider adding JLabel here to clarify what button does
		startStop = new JButton("START"); // Have states. OnClick, startStop.setText("STOP")
		trackedMinutes = new JLabel("0");
		taskDesc = new JTextArea("Enter Task Description here...");
		
		/* WINDOW SETUP */ 
	
		mainPane = getContentPane();
		Border blackBorder;
		
		blackBorder = BorderFactory.createLineBorder(Color.black);
		
		JPanel topLeftPane = new JPanel();
		topLeftPane.setLayout(new BoxLayout(topLeftPane,BoxLayout.Y_AXIS));
		topLeftPane.setBorder(blackBorder);
		
		JPanel topRightPane = new JPanel();
		topRightPane.setBorder(blackBorder);
		
		JPanel bottomLeftPane = new JPanel();
		bottomLeftPane.setBorder(blackBorder);
		
		JPanel bottomRightPane = new JPanel();
		bottomRightPane.setBorder(blackBorder);
		
		mainPane.setLayout(new GridLayout(2,2));
		
		mainPane.add(topLeftPane);
		mainPane.add(topRightPane);
		mainPane.add(bottomRightPane);
		mainPane.add(bottomLeftPane);
		
		topLeftPane.add(Box.createRigidArea(new Dimension(0,25)));
		topLeftPane.add(Box.createHorizontalGlue());
		
		topLeftPane.add(startDateLabel);
		topLeftPane.add(startDate);
		
		topLeftPane.add(Box.createRigidArea(new Dimension(0,40)));
		topLeftPane.add(name); // Component, row, column

		topLeftPane.add(endDate);
		topLeftPane.add(locEst);
		topLeftPane.add(Box.createRigidArea(new Dimension(25,5)));
		topLeftPane.add(hoursEst);
				
		setTitle("New Task Window");
		setSize(WIDTH, HEIGHT);
		setLocation(WIDTH/2,HEIGHT/2);
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE); // Enum value EXIT_ON_CLOSE, not String. Interesting.
		
	}

	public TaskState getTaskState() {
		return state;
	}


	public void setTaskState(TaskState state) {
		this.state = state;
	}
}
