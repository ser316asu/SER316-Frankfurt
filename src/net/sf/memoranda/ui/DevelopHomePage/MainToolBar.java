package net.sf.memoranda.ui.DevelopHomePage;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;

import net.sf.memoranda.ui.NewTaskWindow;

public class MainToolBar extends JLabel implements Styling{
	private JButton createNewTask_B;
	private LowerHomePanel taskBoard;
	private NewTaskWindow taskFrame;
	
    public Action createNewTask = new AbstractAction("New Task Creation") {
        /**
		 * 
		 */
	private static final long serialVersionUID = -6751747715539881623L;

	public void actionPerformed(ActionEvent e) {
		taskFrame = new NewTaskWindow();
        }
    };
	
	public MainToolBar()
	{
		System.out.println(" in Constructor of Main Tool Bar");
		//this.setLocation(0, Styling.SCREEN_HEIGHT/2 - Styling.MAIN_TOOLBAR_HEIGHT);
		createComponents();
		editComponents();
		addActionListeners();
		addComponents();
		this.revalidate();
	}
	public MainToolBar(LowerHomePanel taskBoard)
	{
		this.taskBoard = taskBoard;
		System.out.println(" in Constructor of Main Tool Bar");
		//this.setLocation(0, Styling.SCREEN_HEIGHT/2 - Styling.MAIN_TOOLBAR_HEIGHT);
		createComponents();
		editComponents();
		addActionListeners();
		addComponents();
		this.revalidate();
	}
	private void createComponents() {
		createNewTask_B = new JButton("task");
		
	}

	private void editComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setOpaque(false);
		
		this.createNewTask_B.setAction(createNewTask);
		
	}

	private void addActionListeners() {
		// TODO Auto-generated method stub
		
	}

	private void addComponents() {
		this.add(createNewTask_B);
		//this.pack();
	}

	@Override
	public void style() {
		this.setPreferredSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,Styling.MAIN_TOOLBAR_HEIGHT));
		this.setMaximumSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,Styling.MAIN_TOOLBAR_HEIGHT));
		this.setMinimumSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,Styling.MAIN_TOOLBAR_HEIGHT));
		
	}
}
