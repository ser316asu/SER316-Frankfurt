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
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
				
		// Displaying event icon and info on click
		if (EventsManager.isNREventsForDate(date)) {
			
			label.setIcon(eventIcon);
			
			if (isSelected) {
				Vector eventsOnDate = (Vector) EventsManager.getEventsForDate(date);
				JLabel nameOfEvents = new JLabel();
				for (Iterator i = eventsOnDate.iterator(); i.hasNext();) {
					Event e = (Event) i.next();
					String eventName = e.getText();
					String eventTime = e.getTimeString();
					nameOfEvents.setText( nameOfEvents.getText() + ("<br>" + eventName + " - " + eventTime));
				}
				
				nameOfEvents.setText(nameOfEvents.getText());
				label.setText("<html>" + date.getDay() + nameOfEvents.getText() + "</html>");
				String eventsHtmlHead = "<table style=\"width:100%\"><tr><th align=\"left\">Events</th></tr></table>";
				String eventsHtmlCore = "<table style=\"width:100%\"><tr><th align=\"left\">Name</th><th align=\"left\">Time</th><tr><td>Clean Up</td><td>5 oclock</td></tr><tr><td>Dirty Up</td><td>7 oclock</td></tr><tr><td>7 Up</td><td>9 oclock</td></tr>";
				String tasksHtml = "<tr><th align=\"left\">Tasks</th></tr><tr><th align=\"left\">Name</th><th align=\"left\">Date Due</th><tr><td>Clean Up</td><td>May 22nd</td></tr><tr><td>Do Chores</td><td>May 23rd</td></tr></table>";
				String fullHtmlTable = eventsHtmlHead + eventsHtmlCore + tasksHtml;
				
				label.setText("<html>" + fullHtmlTable + "</html>");
				JScrollPane scroller = new JScrollPane(label, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scroller.setPreferredSize(new Dimension(120,40));
				return scroller;
			}	
		}
		else {
			label.setIcon(null);
		}
		
        return label;
    }

    public void setDate(CalendarDate newDate) {
        date = newDate;
    }
}
