package net.sf.memoranda.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.Local;

public class TaskListButton extends JButton {
	
	private CalendarDate dateDue;
	
	public TaskListButton() {
		this.setMinimumSize(new Dimension(500, 20));
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(500, 20));
		this.setRequestFocusEnabled(false);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(true);
		this.setText("");
		this.setToolTipText(Local.getString("Task Date"));
		
		
		this.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskButton_actionPerformed(e);
            }
        });
	}
	
	public void setButtonLabel(String newLabel) {
		this.setText(newLabel);
	}
	
	public void getButtonLabel() {
		this.getText();
	}
	
	public CalendarDate getDateDue() {
		return dateDue;
	}
	
	public void setDateDue(CalendarDate dateDue) {
		this.dateDue = dateDue;
	}
	
	void taskButton_actionPerformed(ActionEvent e) {
		JNCalendarPanel currentPanel = JNCalendarPanel.getInstance();
		System.out.println("Inside button listener");
		currentPanel.set(dateDue);
    	//this.setBackground(Color.CYAN);
    }

}

