package net.sf.memoranda.ui.DevelopHomePage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TimerPanel extends JPanel implements Styling{
	private Dimension dimension,buttonDimension;
	private TopInnerPanel parent;
	private JButton pause,toggle;
	private JLabel time;
	private LayoutManager layout;
	private TaskCard task;
	private JPanel buttonPanel;
	private ButtonListener timerListener;
	
	public TimerPanel(TopInnerPanel parent,TaskCard task){
		this.task = task;
		this.parent = parent;
		createComponents();
		editComponents();
		addActionListener();
		style();
		addComponents();
	}

	public void createComponents(){
		layout = new BorderLayout();

		pause = new JButton();
		toggle = new JButton();

		time = new JLabel();
		
		int width = (int) parent.getDimension().getWidth();
		int height = (int) parent.getDimension().getHeight() + 5;

		dimension = new Dimension(width,height);

		buttonDimension = new Dimension(25,20);

		buttonPanel = new JPanel(new GridLayout(1,3,3,0));

		timerListener = new ButtonListener();		
	}

	public void editComponents(){
		this.setPreferredSize(dimension);
		this.setMaximumSize(dimension);
		this.setMinimumSize(dimension);

		this.setLayout(layout);

		pause.setText("Pause");
		toggle.setText("Start");
		
		time.setText(("00:00:00"));

		time.setVerticalAlignment(JLabel.CENTER);
		pause.setVerticalAlignment(JLabel.CENTER);
		toggle.setVerticalAlignment(JLabel.CENTER);

		time.setHorizontalAlignment(JLabel.CENTER);
		pause.setHorizontalAlignment(JLabel.CENTER);
		toggle.setHorizontalAlignment(JLabel.CENTER);

		pause.setActionCommand("pause");
		toggle.setActionCommand("start");

		toggle.setPreferredSize(buttonDimension);
		pause.setPreferredSize(buttonDimension);
	}

	public void addActionListener(){
		pause.addActionListener(timerListener);
		toggle.addActionListener(timerListener);
	}

	public void addComponents(){
		buttonPanel.add(pause);
		buttonPanel.add(toggle);

		this.add(time,BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.SOUTH);
	}

	public void style(){
		this.setBackground(Styling.BACKGROUND_COLOR);
		
		pause.setBackground(Styling.TASK_PANEL_COLOR);
		pause.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
		
		toggle.setBackground(Styling.TASK_PANEL_COLOR);
		toggle.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
		
		time.setFont(Styling.TIMER_FONT);
		time.setForeground(Styling.TASK_PANEL_COLOR);

		buttonPanel.setBackground(Styling.BACKGROUND_COLOR);
		
		this.setBorder(BorderFactory.createLineBorder(Styling.BORDER_COLOR));
	}

	private class ButtonListener implements ActionListener{
		Timer timer;
		boolean paused;
		public ButtonListener(){
			timer = new Timer(time);
			paused = false;
		}

		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand().equalsIgnoreCase("start")){
				timer.start();
				toggle.setText("Stop");
				toggle.setActionCommand("Stop");
			}
			else if(event.getActionCommand().equalsIgnoreCase("pause")){
				paused = !paused;
				if(paused){
					timer.pause();
					pause.setText("Play");					
				}
				else{
					timer.resume();
					pause.setText("Pause");
				}
			
			}
			else if(event.getActionCommand().equalsIgnoreCase("stop")){
				timer.stop();
				try{
				
					Date timerDuration = timer.getDateFormatter().parse(time.getText());
					//getHours is deprecated
					double timeToAdd = (double)(timerDuration.getHours() + (timerDuration.getMinutes() / 60) + (timerDuration.getSeconds() / (60 * 60)));
					task.addTime(timeToAdd);
					System.out.println(task.countObservers());
					task.notifyObservers();

				}catch(Exception e){
					e.printStackTrace();
				}
				timer = new Timer(time);

				toggle.setText("Start");
				toggle.setActionCommand("start");

				time.setText("00:00:00");
			}
			
		}
	}
}
