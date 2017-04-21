package net.sf.memoranda.ui;
import javax.swing.*;
import javax.swing.Box.Filler;
import javax.swing.border.Border;
import javax.swing.text.DateFormatter;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.EventsManager;
import net.sf.memoranda.EventsScheduler;
import net.sf.memoranda.Task;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.util.Configuration;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Util;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


/** 
 * @author Jacob Leonard
 */

/*TODO Add setters/getters, incorporate JPanels, Layout, Event/Action-Listeners & dividers.
* 1. Add Button that will actually OPEN this window. This will likely be on several different pages (taskboard, tasklist, calendar)
* 2. Add input-validation 
*/
public class NewTaskWindow extends JDialog implements ActionListener {
	
	public boolean CANCELLED = true;
	private boolean isAnEditWindow = false;
	
	private int WIDTH;
	private int HEIGHT;
	private final String DATE_FORMAT = "MM/dd/yyyy";
	private Container mainPane;
	
	private static final long serialVersionUID = 1L;

	
	private JFrame mainFrame;
	private JPanel centerPane, bottomCenterPane, topCenterPane, topRightPane, topLeftPane;

	private Calendar today;
	private JFormattedTextField startDate, endDate;
	private JTextField jTextFieldName; // String
	private JLabel currentState, nameLabel, startDateLabel, endDateLabel, locEstLabel, locActLabel, hoursEstLabel, hoursActLabel, estNumFilesLabel, actNumFilesLabel, statusLabel;

	
	// Code Info
	private JFormattedTextField locEst, locAct, hoursEst, hoursAct, estNumFiles, actNumFiles; 
	private JButton finishButton;
	private JButton startStop; // Hoping to only use one button that changes when pressed
	private JButton notifB;
	private JLabel trackedMinutes; // Timer with stored data ---> will need XML 
	
	private JTextArea taskDesc;
	
	private JTextField actualLoc;
	private JTextField totalHours; 
	
    String[] priority = {Local.getString("Lowest"), Local.getString("Low"),
            Local.getString("Normal"), Local.getString("High"),
            Local.getString("Highest")};
    
    JComboBox<String> priorityCB;
    
	JPanel jPanelProgress;
	JLabel jLabelProgress;
	JSpinner progress;
		
	// Default No-Arg Constructor to initialize window
	public NewTaskWindow(JFrame parentFrame, String title){
		super(parentFrame,title, true);
		WIDTH = 690;//(int)screenSize.getWidth()/2; // Casting as int
		HEIGHT = 450; //(int)screenSize.getHeight()/2;
		// Upper-right Quadrant
		// TODO Consider adding JLabel here to clarify what button does
		//startStop = new JButton("START"); // Have states. OnClick, startStop.setText("STOP")
		createComponents();
		editComponents();
		addComponents();
		
		//this.pack();
		//this.setVisible(true);
	}
	
	/*public static void main(String[] args)
	{
		NewTaskWindow ntw = new NewTaskWindow(new JFrame(), "Testing");
		ntw.setVisible(true);
	}*/
	
	public NewTaskWindow(JFrame parentFrame, String title, Task task){
		super(parentFrame,title, true);
		WIDTH = 690;//(int)screenSize.getWidth()/2; // Casting as int
		HEIGHT = 450; //(int)screenSize.getHeight()/2;
		// Upper-right Quadrant
		// TODO Consider adding JLabel here to clarify what button does
		//startStop = new JButton("START"); // Have states. OnClick, startStop.setText("STOP")
		isAnEditWindow = true;
		createComponents();
		editComponents();
		addTaskElements(task);
		addComponents();
		
		//this.pack();
		//this.setVisible(true);
	}

	private void addTaskElements(Task task) {
		
		// formatting dates from Day/Month/Year to Month/Day/Year
        
        
		this.locAct.setText(task.getActualLOC() + "");
		this.locAct.setValue(task.getActLOC());
		
		this.locEst.setText(task.getEstLOC() + "");
		this.locEst.setValue(task.getEstLOC());
		
		this.estNumFiles.setText(task.getEstNumOfFiles() + "");
		this.estNumFiles.setValue(task.getEstNumOfFiles());
		
		this.actNumFiles.setText(task.getActNumOfFiles() + "");
		this.actNumFiles.setValue(task.getActNumOfFiles());
		
		this.hoursAct.setText(task.getHoursAct() + "");
		this.hoursAct.setValue(task.getHoursAct());
		
		this.hoursEst.setText(task.getHoursEst() + "");
		this.hoursEst.setValue(task.getHoursEst());
		
		this.endDate.setText(task.getEndDate().getShortDateString()); // value unnecessary I think...?
		//this.endDate.setValue(task.getEndDate().getShortDateString());
		
		this.startDate.setText(task.getStartDate().getShortDateString());
		//this.startDate.setValue(task.getStartDate().getShortDateString());
		
		this.progress.setValue(task.getProgress());
		this.jTextFieldName.setText(task.getText()); // May need to set Value. 
		this.priorityCB.setSelectedItem(priority[task.getPriority()]);
		this.taskDesc.setText(task.getDescription());
	}

	private void createComponents() {
		DateFormat newDateFormat = new SimpleDateFormat(DATE_FORMAT);
		NumberFormat integerFieldFormatter = NumberFormat.getIntegerInstance(); // Factory?

		topCenterPane = new JPanel(new BorderLayout());
		topRightPane = new JPanel(new FlowLayout());
		bottomCenterPane = new JPanel(new BorderLayout());
		topLeftPane = new JPanel(new FlowLayout());
		centerPane = new JPanel();
		
		estNumFiles = new JFormattedTextField(integerFieldFormatter);
		actNumFiles = new JFormattedTextField(integerFieldFormatter);
		locEst = new JFormattedTextField(integerFieldFormatter);
		locAct = new JFormattedTextField(integerFieldFormatter);
		hoursEst = new JFormattedTextField(integerFieldFormatter);
		hoursAct = new JFormattedTextField(integerFieldFormatter);
		
		endDate = new JFormattedTextField(newDateFormat);
		startDate = new JFormattedTextField(newDateFormat); // Setting Date formatted textfield
		
		nameLabel = new JLabel("Name");
		locEstLabel = new JLabel("Estimated LOC");
		locActLabel = new JLabel("Actual LOC");
		startDateLabel = new JLabel("Start Date (MM/DD/YYYY)");
		endDateLabel = new JLabel("Due Date");
		hoursEstLabel = new JLabel("Estimated Hours");
		hoursActLabel = new JLabel("Actual Hours");
		trackedMinutes = new JLabel("0");
		statusLabel = new JLabel("Please fill out all fields for your new task");
		estNumFilesLabel = new JLabel("Estimated # of Files");
		actNumFilesLabel = new JLabel("Actual # of Files");
		
		jTextFieldName = new JTextField("Name of Task",28);
		taskDesc = new JTextArea("Enter Task Description here...", 6, 120);
		
		finishButton = new JButton("Save Task");
		notifB = new JButton("Add Notifcation");
		
		jPanelProgress = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jLabelProgress = new JLabel();
		progress = new JSpinner(new SpinnerNumberModel(0, 0, 100, 5));
		
		priorityCB = new JComboBox<String>(priority);
		

	}
	private void editComponents(){
		Border blackBorder = BorderFactory.createLineBorder(Color.black);
		
		/* COMPONENTS */
		//Upper-left Quadrant

		startDate.setColumns(28); // Setting width of text-field
		today = Calendar.getInstance(); // default constructor for Date class is current-date. This passed to DateFormat instance.
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		
		startDate.setValue(today.getTime()); // Set default Date object to today 
		//startDate.setText(newDateFormat.format(today)); // set String text to today's date
		startDate.setMaximumSize(new Dimension(100, 1));
		

		endDate.setColumns(28);
		endDate.setText("MM/DD/YYYY");

		locEst.setColumns(28);
		locEst.setText("Estimated LOC");
		
		locAct.setColumns(28);
		locAct.setText("0");

		hoursEst.setColumns(28);
		hoursEst.setText("Hours Estimate");
		
		hoursAct.setColumns(28);
		hoursAct.setText("0");
		
		estNumFiles.setColumns(28);
		estNumFiles.setText("Estimated # Files");
		
		actNumFiles.setColumns(28);
		actNumFiles.setText("0");
		
		taskDesc.setBorder(blackBorder);
		
		notifB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Before new event action");
            	//setNotifB_actionPerformed(e);
            	System.out.println("After new event action");
            }
        });
		

		//###################
		/* WINDOW SETUP */ 
		//###################
		
		mainPane = getContentPane();
		
		topLeftPane.setBorder(blackBorder);
		topLeftPane.setBackground(Color.LIGHT_GRAY); //new Color(50, 34, 24)
		

		topRightPane.setBorder(blackBorder);
		topRightPane.setBackground(Color.LIGHT_GRAY);

		topCenterPane.setBorder(blackBorder);
		

		bottomCenterPane.setBorder(blackBorder);
		
		mainPane.setLayout(new BorderLayout());
		
		finishButton.addActionListener(this);
		
		centerPane.setLayout(new BorderLayout());
		centerPane.setBorder(blackBorder);
		centerPane.setMaximumSize(new Dimension(150,150));
		
		priorityCB.setFont(new java.awt.Font("Dialog", 0, 11));
		priorityCB.setSelectedItem(Local.getString("Normal"));
		priorityCB.setOpaque(false);
		
		jLabelProgress.setText(Local.getString("Progress:"));
		jLabelProgress.setOpaque(false);
		
		jPanelProgress.setOpaque(false);
		
		setTitle("Memoranda - New Task Window");
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		setLocation(WIDTH/2,HEIGHT/2);
		setResizable(false); // Fixed window size
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Enum value EXIT_ON_CLOSE, not String. Interesting.
	}
	
	private void addComponents(){
		Dimension centerPanelSize = new Dimension(335, 270);
		
		// Change these to regular boxes so color can be applied. These for filler spaces. Currently unused
		//Filler boxLeft = new Box.Filler(minSize, prefSize, maxSize);
		//Filler boxRight = new Box.Filler(minSize, prefSize, maxSize);
		Filler boxCenter = new Box.Filler(new Dimension(5,5), new Dimension(5,5), new Dimension(5,5));
		
        jPanelProgress.add(progress, null);
		
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
		topLeftPane.add(jLabelProgress);
		topLeftPane.add(jPanelProgress);
		topLeftPane.add(this.notifB);
		topLeftPane.add(new JLabel("Priority:"));
		topLeftPane.add(priorityCB);
		topLeftPane.add(locEstLabel);
		topLeftPane.add(locEst);
		topLeftPane.setPreferredSize(centerPanelSize);
		// Top-Right-Center Pane
		topRightPane.add(hoursEstLabel);
		topRightPane.add(hoursEst);
		topRightPane.add(estNumFilesLabel);
		topRightPane.add(estNumFiles);
		topRightPane.add(locActLabel);
		topRightPane.add(locAct);
		topRightPane.add(hoursActLabel);
		topRightPane.add(hoursAct);
		topRightPane.add(actNumFilesLabel);
		topRightPane.add(actNumFiles);

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
		centerPane.add(topCenterPane,BorderLayout.NORTH);
		centerPane.add(bottomCenterPane, BorderLayout.CENTER);
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
//			/TaskCard task = new TaskCard(Integer.parseInt(locEst.getText()), Integer.parseInt(hoursEst.getText()), Integer.parseInt(numFiles.getText()), (Date)startDate.getValue(), (Date)endDate.getValue(), taskDesc.getText(), jTextFieldName.getText());
			
			//System.out.println(task.getStartDate() + " " + task.getEndDate() + " " + task.getEstimatedTime());
			
			statusLabel.setFont(new Font("Courier",Font.BOLD,24));
			statusLabel.setText("FINISHED! You may now close this window");
			statusLabel.setBackground(Color.GREEN);
			statusLabel.setForeground(Color.WHITE);
			
			System.out.println("Testing Finalization block");
			CANCELLED = false;
			this.dispose();
		}
		

		statusLabel.setOpaque(true);
		bottomCenterPane.revalidate();
		bottomCenterPane.repaint();		
	}
	
	public boolean validateInput(){
		boolean isValid = true; // If checks below pass, isValid remains true.

		//TODO: Check if Duplicate task exists
		// CHECK TASK NAME
		if(jTextFieldName.getText() == null || jTextFieldName.getText().equals("Name of Task") 
				|| jTextFieldName.getText().equals("Task Name is invalid") || jTextFieldName.getText().equals("")){
			System.out.println("Task Name is invalid");
			jTextFieldName.setText("Task Name is invalid");
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
			startDate.setText("RE-ENTER! (mm/dd/yyyy)");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			startDate.setBackground(Color.red);
			isValid = false;
		}
		else if(startDate.getValue() != null){
			startDate.setBackground(Color.WHITE);

		}
		// CHECK END DATE
		// CHECK TO MAKE SURE END-DATE >= START-DATE. Use compareTo()
		if(endDate.getText() == null || !(isValidDateFormat(endDate.getText()))){ //((endDate.getText() != null && endDate.getValue() == null))
			endDate.setText("RE-ENTER! (mm/dd/yyyy)");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			endDate.setBackground(Color.red);
			isValid = false;
		}

		/*else if(!endDateObj.after(startDateObj) && !endDateObj.equals(startDateObj)){ // Is endDate before startDate? Ensure HR/MIN/SEC/MS are set to 0. 
			endDate.setText("RE-ENTER! (dd/mm/yyyy)");
			endDate.setBackground(Color.red);
			isValid = false;
		}
		else if(endDate.getValue() != null){
			endDate.setBackground(Color.WHITE);
		}*/
		
		// CHECK LOC ESTIMATE
		if(locEst.getValue() == null || locEst.getText() == null){
			System.out.println("LOC EST is invalid");
			locEst.setText("Re-Enter Lines of Code Estimate!");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			locEst.setBackground(Color.red);
			isValid = false;
		}
		else if(locEst.getValue() != null){
			locEst.setBackground(Color.WHITE);
		}	
		
		if(locAct.getValue() == null || locAct.getText() == null){
			System.out.println("LOC Act is invalid");
			locAct.setText("Re-enter Lines of Code Estimate!");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			locAct.setBackground(Color.red);
			isValid = false;
		}
		else if(locAct.getValue() != null){
			locAct.setBackground(Color.WHITE);
		}
		
		
		// CHECK HOURS ESTIMATE
		if(hoursEst.getValue() == null || hoursEst.getText() == null){
			System.out.println("Hours EST is invalid");
			hoursEst.setText("Re-Enter Lines of Code Estimate!");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			hoursEst.setBackground(Color.red);
			isValid = false;
		}
		else if(hoursEst.getValue() != null){
			hoursEst.setBackground(Color.WHITE);
		}
		
		if(hoursAct.getValue() == null || hoursAct.getText() == null){
			System.out.println("Hours Act is invalid");
			hoursAct.setText("Re-Enter Actual Hours!");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			hoursAct.setBackground(Color.red);
			isValid = false;
		}
		else if(hoursAct.getValue() != null){
			hoursAct.setBackground(Color.WHITE);
		}
		
		//TODO
		// CHECK NUMFILES ESTIMATE
		if(estNumFiles.getValue() == null || estNumFiles.getText() == null){
			System.out.println("# of Files is invalid");
			estNumFiles.setText("Re-Enter Estimated # of Files");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			estNumFiles.setBackground(Color.red);
			isValid = false;
		}
		else if(estNumFiles.getValue() != null){
			estNumFiles.setBackground(Color.WHITE);
		}
		
		if(actNumFiles.getValue() == null || actNumFiles.getText() == null){
			System.out.println("Act Num Files is invalid");
			actNumFiles.setText("Re-Enter Actual # Files!");
			statusLabel.setText("Invalid Entry: Please fix marked fields");
			actNumFiles.setBackground(Color.red);
			isValid = false;
		}
		else if(actNumFiles.getValue() != null){
			actNumFiles.setBackground(Color.WHITE);
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
	
    private void setNotifB_actionPerformed(ActionEvent e) {
    	String Date = this.startDate.getText();
    	String [] time = Date.split("/");
    	
    	int month = Integer.parseInt(time[0]);
    	int day = Integer.parseInt(time[1]);
    	int year = Integer.parseInt(time[2]);
    	
    	CalendarDate startDate = new CalendarDate(day,month,year);
    	
    	Date = this.startDate.getText();
    	time = Date.split("/");
    	
    	month = Integer.parseInt(time[0]);
    	day = Integer.parseInt(time[1]);
    	year = Integer.parseInt(time[2]);
    	
    	CalendarDate endDate = new CalendarDate(day,month,year);
    	
    	System.out.println("sdate " + startDate + "  eDate " + endDate);
    	((AppFrame)App.getFrame()).workPanel.dailyItemsPanel.eventsPanel.newEventB_actionPerformed(e, 
			this.getTaskDesc().getText(), startDate.getDate(),endDate.getDate());
    }
    
    public boolean isValidDateFormat(String date){
    	
    	boolean isValid = true;
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    	try {
			if(sdf.parse(date) != null){
				isValid = true;
			}

		} catch (ParseException e) {
			return false;
			//e.printStackTrace();
		}
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
	
	public JLabel getHoursActLabel() {
		return hoursActLabel;
	}

	public JLabel getNumFilesLabel() {
		return estNumFilesLabel;
	}

	public JLabel getStatusLabel() {
		return statusLabel;
	}

	public JTextField getLocEst() {
		return locEst;
	}
	
	public JTextField getLocAct() {
		return locAct;
	}

	public JTextField getHoursEst() {
		return hoursEst;
	}

	public JTextField getNumFiles() {
		return estNumFiles;
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
	
	public JTextField getHoursAct() {
		return hoursAct;
	}

	public JTextField getActualNumFiles() {
		return actNumFiles;
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
		this.estNumFilesLabel = numFilesLabel;
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
		this.estNumFiles = numFiles;
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

	public void setActualLoc(JFormattedTextField actualLoc) {
		this.locAct = actualLoc;
	}

	public void setTotalHours(JTextField totalHours) {
		this.totalHours = totalHours;
	}

	public void setActualNumFiles(JFormattedTextField actualNumFiles) {
		this.actNumFiles = actualNumFiles;
	}
	
}
