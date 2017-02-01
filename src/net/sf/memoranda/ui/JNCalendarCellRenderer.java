/**
 * JNCalendarCellRenderer.java
 * Created on 14.02.2003, 0:09:11 Alex
 * Package: net.sf.memoranda.ui
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda.ui;
import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;

import net.sf.memoranda.CurrentProject;
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
		String currentPanel = ((AppFrame)App.getFrame()).workPanel.dailyItemsPanel.getCurrentPanel();

		// For Cells that would hold dates not in the current month
		if (date == null) {
            label.setEnabled(false);
			label.setIcon(null);
            label.setBackground(new Color(244,244,244));
            return label;
        }
        
		// Non-Selected Calendar Cells
		if (!isSelected) {
//			CalendarDate projectStartDate = CurrentProject.get().getStartDate();
//            CalendarDate projectEndDate = CurrentProject.get().getEndDate();
            //if (!(((date.after(projectStartDate)) && (date.before(projectEndDate))) || (date.equals(projectStartDate)) || (date.equals(projectEndDate)))) {
            	
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
				return label;
			//}
        }		

		
		// Selected Cells
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
		
		// General Layout Alignment of labels inside cell
		label.setHorizontalAlignment(CENTER);
		label.setEnabled(true);

		
//		// set foreground color
//		if (date.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
////            label.setForeground(new Color(255, 0, 0));
//        }
//		else { 		
//			label.setForeground(Color.BLACK);
//		}

//		// set background color
//		if (currentPanel == null)
//			label.setBackground(Color.WHITE);
		
		// TODO Remove these checks once drastic architecture reform is complete
//		else if (currentPanel.equals("TASKS") && (task != null) && 
//			(date.inPeriod(task.getStartDate(), task.getEndDate()))) 
//				label.setBackground( new Color(230, 255, 230));
//		
//		else if(currentPanel.equals("NOTES") && 
//		CurrentProject.getNoteList().getNoteForDate(date) != null) 
//					label.setBackground(new Color(255,245,200));
//		
//		else if(currentPanel.equals("EVENTS") && 
//		(!(EventsManager.getEventsForDate(date).isEmpty()))) 
//					label.setBackground(new Color(255,230,230));
		
//		else if(!isSelected)
//			label.setBackground(Color.WHITE);
				
		// Displaying events / tasks
		if (EventsManager.isNREventsForDate(date)) //will need to change this to isTaskForDate(d) once new task structure is created
			label.setIcon(eventIcon);
		else
			label.setIcon(null);
		
        return label;
    }

    public void setDate(CalendarDate newDate) {
        date = newDate;
    }
}
