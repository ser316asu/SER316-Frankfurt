package net.sf.memoranda.ui.DevelopHomePage;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;

public class MainToolBar extends JFrame implements Styling{
	private JButton createNewTask_B;
	public MainToolBar(JLayeredPane pane)
	{
		this.setLocationRelativeTo(pane);
		createComponents();
		editComponents();
		addActionListeners();
		addComponents();
	}
	
	public MainToolBar()
	{
		System.out.println(" in Constructor of Main Tool Bar");
		this.setLocation(0, Styling.SCREEN_HEIGHT/2 - Styling.MAIN_TOOLBAR_HEIGHT);
		createComponents();
		editComponents();
		addActionListeners();
		addComponents();
	}
	private void createComponents() {
		createNewTask_B = new JButton("task");
		
	}

	private void editComponents() {
		this.setPreferredSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,Styling.MAIN_TOOLBAR_HEIGHT));
		this.setMaximumSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,Styling.MAIN_TOOLBAR_HEIGHT));
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setUndecorated(true);
	}

	private void addActionListeners() {
		// TODO Auto-generated method stub
		
	}

	private void addComponents() {
		this.add(createNewTask_B);
		this.pack();
	}

	@Override
	public void style() {
		// TODO Auto-generated method stub
		
	}
}
