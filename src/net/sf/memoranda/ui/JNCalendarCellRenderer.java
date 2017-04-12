/**
 * JNCalendarCellRenderer.java
 * Created on 14.02.2003, 0:09:11 Alex
 * Package: net.sf.memoranda.ui
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.Event;
import net.sf.memoranda.EventsManager;
import net.sf.memoranda.Task;
import net.sf.memoranda.date.CalendarDate;
/**
 *
 */
/*$Id: JNCalendarCellRenderer.java,v 1.5 2004/10/11 08:48:20 alexeya Exp $*/
public class JNCalendarCellRenderer extends javax.swing.table.DefaultTableCellRenderer {
    private CalendarDate date = null;
    boolean disabled = false;
    ImageIcon eventIcon = new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/en.png"));
    Task task = null;
	
	// Html table strings that will be used in creating the HTML table
    String tasks = "";
	String rowStart = "<tr>";
	String cellStart = "<td>";
	String cellEnd = "</td>";
	String rowEnd = "</tr>";
    private static CalendarDate lastDateSelected;
    
    public void setTask(Task newTask) {
        task = newTask;
    }
    
    public Task getTask() {
        return task;
    }

    public Component getTableCellRendererComponent(
        JTable table,
        Object value,
        boolean isSelected,
        boolean hasFocus,
        int row,
        int column) {
        
		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		//String currentPanel = ((AppFrame)App.getFrame()).workPanel.dailyItemsPanel.getCurrentPanel();

		// For Cells that would hold dates not in the current month
		if (date == null) {
            label.setEnabled(false);
			label.setIcon(null);
            label.setBackground(new Color(244,244,244));
            return label;
        }
        
		// Non-Selected Calendar Cells
		if (!isSelected) {
            	
        	// Set Foreground color based on work week and weekend days
        	if (date.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || date.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
        		label.setForeground(Color.LIGHT_GRAY);
        	} else {
        		label.setForeground(Color.DARK_GRAY);
        	}
        	label.setBackground(Color.WHITE);
        	
        	// today cell foreground and background colors (non-selected)
            if (date.equals(CalendarDate.today())) {
                label.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 128)));
            }
        }		

		
		// Coloring for selected cells
		if (isSelected) {
			// Today Cells
			if (date.equals(CalendarDate.today())) {
				label.setBackground(new Color(36,90,175));
				label.setForeground(Color.WHITE);
			} else { // Regular Cells
			
				label.setBackground(Color.BLACK);
		        label.setForeground(Color.WHITE);
			}
		}
		
		for(Object task : CurrentProject.getTaskList().getTopLevelTasks())
	    {
	    	Task tsk = (Task)task;
             //String taskName = tsk.getText();
	    	
	    	 //String taskTime = Integer.toString(tsk.getActualTime());
	    	
	         //tasks += rowStart + cellStart + taskName + cellEnd + cellStart + taskTime + cellEnd + rowEnd;
	    	
	    	if (date.equals(tsk.getStartDate())){
	    		
	    	
	    		if (tsk.getPriority()==0){
		    		
		    		label.setBackground(Color.GREEN);
					label.setForeground(Color.BLACK);
		    		
		    	} 
	    		else if(tsk.getPriority()==1){
	    			
	    			label.setBackground(Color.YELLOW);
					label.setForeground(Color.BLACK);
		    		
		    	}
                    else if(tsk.getPriority()== 2){
	    			
	    			label.setBackground(Color.CYAN);
					label.setForeground(Color.BLACK);
		    		
		    	}
                    else if(tsk.getPriority()== 3){
    	    			
    	    			label.setBackground(Color.PINK);
    					label.setForeground(Color.BLACK);
    		    		
    		    }
                    else if(tsk.getPriority()== 4){
    	    			
    	    			label.setBackground(Color.RED);
    					label.setForeground(Color.BLACK);
    		    		
    		    }
	    	
	    	}
	     }
		
		
		
		TaskWindow taskWindow;
		
		for(Object task : CurrentProject.getTaskList().getTopLevelTasks())
	    {
	    	Task tsk = (Task)task;
	    	
	    	if (isSelected){
	    		
	    		if (date.equals(tsk.getStartDate())){
	    			
	    			taskWindow = TaskWindow.get();
	    			
	    			JLabel taskDescription = new JLabel(tsk.getText(), SwingConstants.CENTER);
	    			taskWindow.add(taskDescription);
	    			
	    		}
	    		
	    		
	    	}
           
	     }
			
		// General Layout Alignment of labels inside cell
		label.setHorizontalAlignment(CENTER);
		label.setEnabled(true);
		
				
		// Displaying event icon and info on click
		if (EventsManager.isNREventsForDate(date)) {
			
			label.setIcon(eventIcon);
	
			
			if (isSelected) {
				Vector eventsOnDate = (Vector) EventsManager.getEventsForDate(date);
				String events = "";
				//String tasks = "";
				
				/** Iterate through every event on the clicked date
				 * and format the events using the html "building blocks" defined above
				 */
				for (Iterator i = eventsOnDate.iterator(); i.hasNext();) {
					Event e = (Event) i.next();
					String eventName = e.getText();
					String eventTime = e.getTimeString();
					events += rowStart + cellStart + eventName + cellEnd + cellStart + eventTime + cellEnd + rowEnd;
				}
				

				// Top of the Events Table Html 
				String eventsHtmlHead = "<table style=\"width:100%\"><tr><th align=\"left\">Events</th></tr></table><table style=\"width:100%\"><tr><th align=\"left\">Name</th><th align=\"left\">Time</th>";
				
				// Top of Tasks Table Html
				String tasksHtmlhead = "<tr><th align=\"left\">Tasks</th></tr><tr><th align=\"left\">Name</th><th align=\"left\">Date Due</th>";
				
				// Full html table that will be set as the calendar cell's text
				String fullHtmlTable = "<html>" + eventsHtmlHead + events + "</table></html>";
				
				JLabel htmlText = new JLabel();
				htmlText.setText(fullHtmlTable);
				
				// If the currently selected date is newly selected, display event/task window
				if (!date.equals(lastDateSelected)) {
					DateInfoPopUp dateInfo = new DateInfoPopUp(App.getFrame(),date,htmlText);
				}
			}	
		}
		else {
			label.setIcon(null);
		}
		
		if (isSelected) {
			lastDateSelected = date;
		}
		
        return label;
    }

    public void setDate(CalendarDate newDate) {
        date = newDate;
    }
}
