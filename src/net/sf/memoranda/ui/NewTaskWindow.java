package net.sf.memoranda.ui;
import javax.swing.*;
import javax.swing.Box.Filler;
import javax.swing.border.Border;
import javax.swing.text.DateFormatter;
import net.sf.memoranda.ui.DevelopHomePage.TaskCard;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


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

	private Calendar today;
	private JFormattedTextField startDate, endDate;
	private JTextField jTextFieldName; // String
	private JLabel currentState, nameLabel, startDateLabel, endDateLabel, locEstLabel, hoursEstLabel, numFilesLabel, statusLabel;

	// Code Info
	private JFormattedTextField locEst, hoursEst, numFiles; 
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
		jTextFieldName = new JTextField("Name of Task",28);

		startDateLabel = new JLabel("Start Date (MM/DD/YYYY)");
		DateFormat newDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		startDate = new JFormattedTextField(newDateFormat); // Setting Date formatted textfield
		startDate.setColumns(28); // Setting width of text-field
		today = Calendar.getInstance(); // default constructor for Date class is current-date. This passed to DateFormat instance.
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		
		startDate.setValue(today.getTime()); // Set default Date object to today 
		//startDate.setText(newDateFormat.format(today)); // set String text to today's date
		startDate.setMaximumSize(new Dimension(100, 1));
		
		endDateLabel = new JLabel("Due Date");
		endDate = new JFormattedTextField(newDateFormat);
		endDate.setColumns(28);
		endDate.setText("MM/DD/YYYY");
		
		
		locEstLabel = new JLabel("Estimated LOC");
		NumberFormat integerFieldFormatter = NumberFormat.getIntegerInstance(); // Factory?
		locEst = new JFormattedTextField(integerFieldFormatter);
		locEst.setColumns(28);
		locEst.setText("LOC Estimate");
		
		hoursEstLabel = new JLabel("Estimated Hours");
		hoursEst = new JFormattedTextField(integerFieldFormatter);
		hoursEst.setColumns(28);
		hoursEst.setText("Hours Estimate");
		
		numFilesLabel = new JLabel("Estimated # of Files");
		
		numFiles = new JFormattedTextField(integerFieldFormatter);
		numFiles.setColumns(28);
		numFiles.setText("Number of Files/Classes");
		statusLabel = new JLabel("Please fill out all fields for your new task");
		
		// Upper-right Quadrant
		// TODO Consider adding JLabel here to clarify what button does
		//startStop = new JButton("START"); // Have states. OnClick, startStop.setText("STOP")
		blackBorder = BorderFactory.createLineBorder(Color.black);

		finishButton = new JButton("Add Task");
		
		trackedMinutes = new JLabel("0");
		taskDesc = new JTextArea("Enter Task Description here...", 10, 120);
		taskDesc.setBorder(blackBorder);
		

		//###################
		/* WINDOW SETUP */ 
		//###################
		
		mainPane = getContentPane();
		JPanel centerPane = new JPanel();
		centerPane.setLayout(new BorderLayout());
		centerPane.setBorder(blackBorder);
		centerPane.setMaximumSize(new Dimension(150,150));
		
		topLeftPane = new JPanel(new FlowLayout());
		topLeftPane.setBorder(blackBorder);
		topLeftPane.setBackground(Color.LIGHT_GRAY); //new Color(50, 34, 24)
		
		topRightPane = new JPanel(new FlowLayout());
		topRightPane.setBorder(blackBorder);
		topRightPane.setBackground(Color.LIGHT_GRAY);
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
		topLeftPane.add(jTextFieldName); // Component, row, column
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
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Enum value EXIT_ON_CLOSE, not String. Interesting.
		finishButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*TODO
		/* COMPLETED: Maybe in a later Iteration: Check if all fields are filled
		/* instantiate new TaskCard from this and pass it into a collection
		*/
		// CHECK IF INPUT IS VALID
		if(validateInput()){ 
			
			finishButton.setVisible(false);
			// TODO - Create new taskcard here
			//public TaskCard(int estimatedLOC, int estimatedTime, Date startDate, Date endDate, String taskDescription, String taskName){
			TaskCard task = new TaskCard(Integer.parseInt(locEst.getText()), Integer.parseInt(hoursEst.getText()), Integer.parseInt(numFiles.getText()), (Date)startDate.getValue(), (Date)endDate.getValue(), taskDesc.getText(), jTextFieldName.getText());
			
			System.out.println(task.getStartDate() + " " + task.getEndDate() + " " + task.getEstimatedTime());
			
			statusLabel.setFont(new Font("Courier",Font.BOLD,24));
			statusLabel.setText("FINISHED! — You may now close this window");
			statusLabel.setBackground(Color.GREEN);
			statusLabel.setForeground(Color.WHITE);
			
			System.out.println("Testing Finalization block");

		}
		

		statusLabel.setOpaque(true);
		bottomCenterPane.revalidate();
		bottomCenterPane.repaint();		
	}
	
	public boolean validateInput(){
		
		boolean isValid = true; // If checks below pass, isValid remains true. 
		Date startDateObj = (Date) startDate.getValue(); // Casting as Date instead of SimpleDateFormat for conditional.
		Date endDateObj = (Date) endDate.getValue();

		//TODO: Check if Duplicate task exists
		// CHECK TASK NAME
		if(jTextFieldName.getText() == null || jTextFieldName.getText().equals("Name of Task") || jTextFieldName.getText().equals("startDate is invalid")){
			System.out.println("Task Name is invalid");
			jTextFieldName.setText("startDate is invalid");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			jTextFieldName.setBackground(Color.red);
			isValid = false;
		}
		else if(jTextFieldName.getText() != null){
			jTextFieldName.setBackground(Color.WHITE);

		}
		
		// CHECK START DATE
		if(startDate.getValue() == null){
			System.out.println("startDate is invalid");
			startDate.setText("RE-ENTER! (dd/mm/yyyy)");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			startDate.setBackground(Color.red);
			isValid = false;
		}
		else if(startDate.getValue() != null){
			startDate.setBackground(Color.WHITE);

		}
		// CHECK END DATE
		// TODO: CHECK TO MAKE SURE END-DATE >= START-DATE. Use compareTo()
		if(endDate.getValue() == null){
			System.out.println("endDate is invalid");
			endDate.setText("RE-ENTER! (dd/mm/yyyy)");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			endDate.setBackground(Color.red);
			isValid = false;
		}
		else if(!endDateObj.after(startDateObj) && !endDateObj.equals(startDateObj)){ // Is endDate before startDate? Ensure HR/MIN/SEC/MS are set to 0. 
			endDate.setText("RE-ENTER! (dd/mm/yyyy)");
			endDate.setBackground(Color.red);
			isValid = false;
		}
		else if(endDate.getValue() != null){
			endDate.setBackground(Color.WHITE);
		}		
		
		// CHECK LOC ESTIMATE
		if(locEst.getValue() == null){
			System.out.println("LOC EST is invalid");
			locEst.setText("Re-Enter Lines of Code Estimate!");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			locEst.setBackground(Color.red);
			isValid = false;
		}
		else if(locEst.getValue() != null){
			locEst.setBackground(Color.WHITE);
		}	
		
		// CHECK HOURS ESTIMATE
		if(hoursEst.getValue() == null){
			System.out.println("Hours EST is invalid");
			hoursEst.setText("Re-Enter Lines of Code Estimate!");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			hoursEst.setBackground(Color.red);
			isValid = false;
		}
		else if(hoursEst.getValue() != null){
			hoursEst.setBackground(Color.WHITE);
		}	
		
		//TODO
		// CHECK NUMFILES ESTIMATE
		if(numFiles.getValue() == null){
			System.out.println("# of Files is invalid");
			numFiles.setText("Re-Enter Estimated # of Files");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			numFiles.setBackground(Color.red);
			isValid = false;
		}
		else if(numFiles.getValue() != null){
			numFiles.setBackground(Color.WHITE);
		}
	
		// CHECK TASK DESCRIPTION
		if(taskDesc.getText() == null || taskDesc.getText().equals("") || taskDesc.getText().equals("Enter Task Description here...") || taskDesc.getText().equals("Task Description is invalid")){
			System.out.println("Task Description is Invalid");
			taskDesc.setText("Task Description is invalid");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			taskDesc.setBackground(Color.red);
			isValid = false;
		}
		else
			taskDesc.setBackground(Color.WHITE);

		return isValid; 
		
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
		return jTextFieldName;
	}

	public JTextField getStartDate() {
		return startDate;
	}

	public JFormattedTextField getEndDate() {
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
  
	public void setjTextFieldName(JTextField jTextFieldName) {
		this.jTextFieldName = jTextFieldName;
	}

	public void setStartDate(JFormattedTextField startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(JFormattedTextField endDate) {
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

	public void setLocEst(JFormattedTextField locEst) {
		this.locEst = locEst;
	}

	public void setHoursEst(JFormattedTextField hoursEst) {
		this.hoursEst = hoursEst;
	}

	public void setNumFiles(JFormattedTextField numFiles) {
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
