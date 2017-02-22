package net.sf.memoranda.ui.DevelopHomePage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import javax.swing.OverlayLayout;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.LabelUI;

import net.sf.memoranda.ui.NewTaskWindow;

public class MainToolBar extends JLabel implements Styling{
	private JButton createNewTask_B, calendar_B;
	private LowerHomePanel taskBoard;
	private NewTaskWindow taskFrame;
	
    public Action createNewTask = new AbstractAction("") {
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
		createComponents();
		addActionListeners();
		editComponents();
		addComponents();
		this.revalidate();
	}
	public MainToolBar(LowerHomePanel taskBoard)
	{
		this.taskBoard = taskBoard;
		createComponents();
		editComponents();
		addActionListeners();
		addComponents();
		this.revalidate();
	}
	private void createComponents() {
		createNewTask_B = new JButton();
		calendar_B = new JButton();
		
	}

	private void editComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		this.setOpaque(false);
		
		editToolBarButton(createNewTask_B, "Create New Task");
		
		editToolBarButton(calendar_B, "Open Calendar");
		//this.setUI(new ButtonUI());
		
	}

	private void addActionListeners() {
		this.createNewTask_B.setAction(createNewTask);
		
	}

	private void addComponents() {
		addButtonLabel(createNewTask_B, "+");
		this.add(createNewTask_B);
		addButtonLabel(calendar_B, "#");
		this.add(calendar_B);
	}
	
	/**
	 * 
	 */
	private void addButtonLabel(JButton button, String title)
	{
		JLabel label = new JLabel(title);
		label.setFont(Styling.TOOLBAR_FONT);
		label.setOpaque(false);
		label.setForeground(Color.WHITE);
		label.setPreferredSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT+1));
		label.setMaximumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT+1));
		label.setMinimumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT+1));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		button.add(label);
	}
	private void editToolBarButton(JButton button, String toolTip)
	{
		button.setFont(Styling.TASK_PANEL_FONT);
		button.setIcon(LoadAssets.TOOLBAR_BUTTON_IMAGE);
		button.setBorderPainted(false);
		//this.createNewTask_B.setBounds(0, 0, 2, 2);
		button.setMargin(new Insets(5,0,5,0));
		button.setToolTipText(toolTip);
		button.setContentAreaFilled(false);
		button.setLayout(new OverlayLayout(button));
	}
	@Override
	public void style() {
		this.setPreferredSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,Styling.MAIN_TOOLBAR_HEIGHT));
		this.setMaximumSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,Styling.MAIN_TOOLBAR_HEIGHT));
		this.setMinimumSize(new Dimension(Styling.MAIN_TOOLBAR_WIDTH,Styling.MAIN_TOOLBAR_HEIGHT));
		
		this.createNewTask_B.setPreferredSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT));
		this.createNewTask_B.setMaximumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT));
		this.createNewTask_B.setMinimumSize(new Dimension(Styling.TOOLBAR_BUTTON_WIDTH,Styling.TOOLBAR_BUTTON_HEIGHT));
	}
}
