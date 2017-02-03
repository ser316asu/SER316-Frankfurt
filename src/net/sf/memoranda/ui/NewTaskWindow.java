package net.sf.memoranda.ui;
import javax.swing.*;
import java.awt.*;

/** 
 * 
 * @author Jacob Leonard
 *
 */

public class NewTaskWindow {
	
	
	private JFrame mainFrame;
	private JTextField name; // String
	private JTextField starteDate; // Date 
	private JTextField endDate; // Date
	
	// Code Info
	private JTextField locEst; // int - Line-of-Code Estimate
	private JTextField hoursEst; // double
	private JTextField numFiles; // numClasses/Files to help understand scope of task attempt on outset versus end-result. 
	
	
	private JButton startStop; // Hoping to only use one button that changes when pressed
	private JButton trackedMinutes; // Timer with stored data ---> will need XML 
	
	private JTextArea taskDesc;
	
	// Results
	private JTextField actualLoc;
	private JTextField totalHours; 
	private JTextField actualNumFiles;
	
	/*TODO Add setters/getters, incorporate JPanels, Layout, Event/Action-Listeners & dividers.
	* 1. Add Button that will actually OPEN this window. This will likely be on several different pages (taskboard, tasklist, calendar)
	* 2. Will need to permanently save some data to reopen the next session
	* 3. Need to change state functionality (1: ADD 2: INPROG 3: END ADD 4: FINAL RESULTS)
	*
	*
	*/

}
