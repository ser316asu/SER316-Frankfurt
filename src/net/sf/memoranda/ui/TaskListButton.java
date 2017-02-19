package net.sf.memoranda.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import net.sf.memoranda.util.Local;

public class TaskListButton extends JButton {
	
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
	
	void taskButton_actionPerformed(ActionEvent e) {
    	this.setBackground(Color.CYAN);
    }

}

