package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.Box.Filler;
import javax.swing.border.Border;

import net.sf.memoranda.Task;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.Local;

public class DateInformationWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private int WIDTH;
	private int HEIGHT;

	private JScrollPane scroller;
	private JPanel mainPanel;
	private static int numberOfCreations = 1;
	
    String[] priority = {Local.getString("Lowest"), Local.getString("Low"),
            Local.getString("Normal"), Local.getString("High"),
            Local.getString("Highest")};
		
	public DateInformationWindow(JFrame parentFrame, CalendarDate date, JLabel tableInformation) {
		super(parentFrame,date.getShortDateString(), true);
		numberOfCreations++;
		if (!(numberOfCreations % 2 == 0)) {
			this.dispose();
			return;
		}
		WIDTH = 690;
		HEIGHT = 450;
		createComponents(tableInformation);
		editComponents();
		addComponents();
		this.setVisible(true);
	}

	private void createComponents(JLabel tableInfo) {
		mainPanel = new JPanel();
		scroller = new JScrollPane(tableInfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	
	private void editComponents() {
		
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		setLocation(WIDTH/2,HEIGHT/2);
		setResizable(false); 
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}	
	
	private void addComponents(){
		mainPanel.add(scroller);
		this.add(mainPanel);
	}
}
