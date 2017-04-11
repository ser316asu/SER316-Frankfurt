package net.sf.memoranda.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class TaskWindow extends JFrame{

	public TaskWindow() {
		
		//pack();
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		// TODO Auto-generated constructor stub
	}

}
