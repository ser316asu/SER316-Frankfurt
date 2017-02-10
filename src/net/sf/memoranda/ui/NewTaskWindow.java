package net.sf.memoranda.ui;
import javax.swing.*;
import javax.swing.Box.Filler;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

/** 
 * @author Jacob Leonard
 */

/*TODO Add setters/getters, incorporate JPanels, Layout, Event/Action-Listeners & dividers.
* 1. Add Button that will actually OPEN this window. This will likely be on several different pages (taskboard, tasklist, calendar)
* 2. Add input-validation 
*/
public class NewTaskWindow extends JFrame implements ActionListener {
	
	//private enum TaskState{ADD, IN_PROG, END_ADD, FINAL_RESULTS};
	
	private int WIDTH;
	private int HEIGHT;
	
	private Container mainPane;
	
	private static final long serialVersionUID = 1L;
	
	private JFrame mainFrame;
	private JPanel centerPane, bottomCenterPane, topCenterPane, topRightPane, topLeftPane;

	private JTextField jTextFieldNameName, startDate, endDate; // String
	private JLabel currentState, nameLabel, startDateLabel, endDateLabel, locEstLabel, hoursEstLabel, numFilesLabel, statusLabel;

	// Code Info
	private JTextField locEst, hoursEst, numFiles; 
	
	private JButton finishButton;
	private JButton startStop; // Hoping to only use one button that changes when pressed
	private JLabel trackedMinutes; // Timer with stored data ---> will need XML 
	
	private JTextArea taskDesc;
	
	// Results (These made visible in STATE 3)
	private JTextField actualLoc;
	private JTextField totalHours; 
	private JTextField actualNumFiles;
		
	// Default No-Arg Constructor to initialize window
	public NewTaskWindow(){
		Border blackBorder;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = 690;//(int)screenSize.getWidth()/2; // Casting as int
		HEIGHT = 450; //(int)screenSize.getHeight()/2;
		
		setVisible(true);

		/* COMPONENTS */
		//Upper-left Quadrant
		nameLabel = new JLabel("Name");
		jTextFieldNameName = new JTextField("Name of Task",28);

		startDateLabel = new JLabel("Start Date");
		startDate = new JTextField("Start Date",28);
		startDate.setMaximumSize(new Dimension(100, 1));
		
		endDateLabel = new JLabel("Due Date");
		endDate = new JTextField("Due Date", 28);
		
		locEstLabel = new JLabel("Estimated LOC");
		locEst = new JTextField("LOC Estimate", 28);
		
		hoursEstLabel = new JLabel("Estimated Hours");
		hoursEst = new JTextField("Hours Estimate", 28);
		
		numFilesLabel = new JLabel("Estimated # of Files");
		numFiles = new JTextField("Number of Files", 28);
		
		statusLabel = new JLabel("Please fill out all fields for your new task");
		
		// Upper-right Quadrant
		// TODO Consider adding JLabel here to clarify what button does
		//startStop = new JButton("START"); // Have states. OnClick, startStop.setText("STOP")
		blackBorder = BorderFactory.createLineBorder(Color.black);

		finishButton = new JButton("Add Task");
		
		trackedMinutes = new JLabel("0");
		taskDesc = new JTextArea("Enter Task Description here...", 10, 120);
		taskDesc.setBorder(blackBorder);
		
		/* WINDOW SETUP */ 
	
		mainPane = getContentPane();
		
		
		JPanel centerPane = new JPanel();
		centerPane.setLayout(new BorderLayout());
		centerPane.setBorder(blackBorder);
		centerPane.setMaximumSize(new Dimension(150,150));
		
		topLeftPane = new JPanel(new FlowLayout());
		topLeftPane.setBorder(blackBorder);
		
		topRightPane = new JPanel(new FlowLayout());
		topRightPane.setBorder(blackBorder);
		
		topCenterPane = new JPanel(new BorderLayout());
		topCenterPane.setBorder(blackBorder);
		
		bottomCenterPane = new JPanel(new BorderLayout());
		bottomCenterPane.setBorder(blackBorder);
		
		mainPane.setLayout(new BorderLayout());
		Dimension minSize = new Dimension(5,40);
		Dimension maxSize = new Dimension(80,400);
		Dimension prefSize = new Dimension(40,40);
		Dimension centerPanelSize = new Dimension(335, 200);
		
		// Change these to regular boxes so color can be applied. These for filler spaces. Currently unused
		Filler boxLeft = new Box.Filler(minSize, prefSize, maxSize);
		Filler boxRight = new Box.Filler(minSize, prefSize, maxSize);
		Filler boxCenter = new Box.Filler(new Dimension(5,5), new Dimension(5,5), new Dimension(5,5));

		//mainPane.add(boxLeft, BorderLayout.WEST);
		mainPane.add(centerPane, BorderLayout.CENTER);
		//mainPane.add(boxRight,  BorderLayout.EAST);
		
		// Top-Left-Center Pane
		topLeftPane.add(nameLabel);
		topLeftPane.add(jTextFieldNameName); // Component, row, column
		topLeftPane.add(startDateLabel);
		topLeftPane.add(startDate);
		topLeftPane.add(endDateLabel);
		topLeftPane.add(endDate);
		topLeftPane.setPreferredSize(centerPanelSize);
		
		// Top-Right-Center Pane
		topRightPane.add(locEstLabel);
		topRightPane.add(locEst);
		topRightPane.add(hoursEstLabel);
		topRightPane.add(hoursEst);
		topRightPane.add(numFilesLabel);
		topRightPane.add(numFiles);
		topRightPane.setPreferredSize(centerPanelSize);
		
		topCenterPane.add(topLeftPane, BorderLayout.WEST);
		topCenterPane.add(boxCenter,BorderLayout.CENTER);
		topCenterPane.add(topRightPane, BorderLayout.EAST);
		bottomCenterPane.add(taskDesc, BorderLayout.NORTH);
		bottomCenterPane.add(statusLabel,BorderLayout.CENTER);
		statusLabel.setHorizontalAlignment(JLabel.CENTER);
		statusLabel.setBackground(Color.lightGray);
		statusLabel.setOpaque(true);
		bottomCenterPane.add(finishButton, BorderLayout.SOUTH);
		
		/* EVENT LISTENERS */
		
		centerPane.add(topCenterPane,BorderLayout.NORTH);
		centerPane.add(bottomCenterPane, BorderLayout.CENTER);
		
		setTitle("Memoranda - New Task Window");
		setSize(WIDTH, HEIGHT);
		setLocation(WIDTH/2,HEIGHT/2);
		setResizable(true); // Fixed window size

		setDefaultCloseOperation(EXIT_ON_CLOSE); // Enum value EXIT_ON_CLOSE, not String. Interesting.
		
		finishButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*TODO
		/* Maybe in a later Iteration: Check if all fields are filled
		/* Field validation?
		/* instantiate new TaskCard from this and pass it into a collection
		*/
		
		finishButton.setText("test");
		finishButton.setVisible(false);
		
		statusLabel.setFont(new Font("Courier",Font.BOLD,24));
		statusLabel.setText("FINISHED! — You may now close this window");
		statusLabel.setBackground(Color.GREEN);
		statusLabel.setForeground(Color.WHITE);

		statusLabel.setOpaque(true);
		bottomCenterPane.revalidate();
		bottomCenterPane.repaint();		
	}
	
	/* GETTERS, THEN SETTERS */

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public Container getMainPane() {
		return mainPane;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public JPanel getCenterPane() {
		return centerPane;
	}

	public JPanel getBottomCenterPane() {
		return bottomCenterPane;
	}

	public JPanel getTopCenterPane() {
		return topCenterPane;
	}

	public JPanel getTopRightPane() {
		return topRightPane;
	}

	public JPanel getTopLeftPane() {
		return topLeftPane;
	}

	public JTextField getjTextFieldName() {
		return jTextFieldNameName;
	}

	public JTextField getStartDate() {
		return startDate;
	}

	public JTextField getEndDate() {
		return endDate;
	}

	public JLabel getCurrentState() {
		return currentState;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public JLabel getStartDateLabel() {
		return startDateLabel;
	}

	public JLabel getEndDateLabel() {
		return endDateLabel;
	}

	public JLabel getLocEstLabel() {
		return locEstLabel;
	}

	public JLabel getHoursEstLabel() {
		return hoursEstLabel;
	}

	public JLabel getNumFilesLabel() {
		return numFilesLabel;
	}

	public JLabel getStatusLabel() {
		return statusLabel;
	}

	public JTextField getLocEst() {
		return locEst;
	}

	public JTextField getHoursEst() {
		return hoursEst;
	}

	public JTextField getNumFiles() {
		return numFiles;
	}

	public JButton getFinishButton() {
		return finishButton;
	}

	public JButton getStartStop() {
		return startStop;
	}

	public JLabel getTrackedMinutes() {
		return trackedMinutes;
	}

	public JTextArea getTaskDesc() {
		return taskDesc;
	}

	public JTextField getActualLoc() {
		return actualLoc;
	}

	public JTextField getTotalHours() {
		return totalHours;
	}

	public JTextField getActualNumFiles() {
		return actualNumFiles;
	}

	public void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}

	public void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}

	public void setMainPane(Container mainPane) {
		this.mainPane = mainPane;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void setCenterPane(JPanel centerPane) {
		this.centerPane = centerPane;
	}

	public void setBottomCenterPane(JPanel bottomCenterPane) {
		this.bottomCenterPane = bottomCenterPane;
	}

	public void setTopCenterPane(JPanel topCenterPane) {
		this.topCenterPane = topCenterPane;
	}

	public void setTopRightPane(JPanel topRightPane) {
		this.topRightPane = topRightPane;
	}

	public void setTopLeftPane(JPanel topLeftPane) {
		this.topLeftPane = topLeftPane;
	}

	public void setjTextFieldNameName(JTextField jTextFieldNameName) {
		this.jTextFieldNameName = jTextFieldNameName;
	}

	public void setStartDate(JTextField startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(JTextField endDate) {
		this.endDate = endDate;
	}

	public void setCurrentState(JLabel currentState) {
		this.currentState = currentState;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public void setStartDateLabel(JLabel startDateLabel) {
		this.startDateLabel = startDateLabel;
	}

	public void setEndDateLabel(JLabel endDateLabel) {
		this.endDateLabel = endDateLabel;
	}

	public void setLocEstLabel(JLabel locEstLabel) {
		this.locEstLabel = locEstLabel;
	}

	public void setHoursEstLabel(JLabel hoursEstLabel) {
		this.hoursEstLabel = hoursEstLabel;
	}

	public void setNumFilesLabel(JLabel numFilesLabel) {
		this.numFilesLabel = numFilesLabel;
	}

	public void setStatusLabel(JLabel statusLabel) {
		this.statusLabel = statusLabel;
	}

	public void setLocEst(JTextField locEst) {
		this.locEst = locEst;
	}

	public void setHoursEst(JTextField hoursEst) {
		this.hoursEst = hoursEst;
	}

	public void setNumFiles(JTextField numFiles) {
		this.numFiles = numFiles;
	}

	public void setFinishButton(JButton finishButton) {
		this.finishButton = finishButton;
	}

	public void setStartStop(JButton startStop) {
		this.startStop = startStop;
	}

	public void setTrackedMinutes(JLabel trackedMinutes) {
		this.trackedMinutes = trackedMinutes;
	}

	public void setTaskDesc(JTextArea taskDesc) {
		this.taskDesc = taskDesc;
	}

	public void setActualLoc(JTextField actualLoc) {
		this.actualLoc = actualLoc;
	}

	public void setTotalHours(JTextField totalHours) {
		this.totalHours = totalHours;
	}

	public void setActualNumFiles(JTextField actualNumFiles) {
		this.actualNumFiles = actualNumFiles;
	}

}
