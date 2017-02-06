/**
*@author Alec Shinn, Josh Becker
**/
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
public class TopHomePanel extends JPanel
{
	private final double topRatio = .4;
    private TaskCard task;
    private JPanel leftParent,leftOne,leftTwo,leftOneLabelPanel,leftTwoLabelPanel,rightParent,rightTop,rightBottom;
    private JButton startButton,pauseButton;
    private JLabel timerLabel,estimatedLOC,actualLOC,taskName,estimatedTime,actualTime;
    private JLabel locPerHour,estimatedLOCPH,startDate,endDate,scheduleStatus;
    private final String AHEAD = "Ahead of Schedule";
    private final String BEHIND = "Behind Schedule";
    private final String ONTIME = "On Time";
    private TaskCard currentTask;
    private Timer timer;
    private Dimension parentDimension,leftParentDim,rightParentDim,innerLeftDim,innerRightDim,leftLabelDim;
    private GridLayout leftLabelLayout,parentLayout;
    private BorderLayout leftChildLayout;
    

    public TopHomePanel()
    {
        task = null;
    	
        createComponents();
    	editComponents();
    	addActionListeners();
    	addComponents();
        
        timer = new Timer(timerLabel);
    }
    public TopHomePanel(TaskCard task){
        this.task = task;

        createComponents();
        editComponents();
        addActionListeners();
        addComponents();

        timer = new Timer(timerLabel);
    }


    public void createComponents()
    {
        leftParent = new JPanel();
        leftOne = new JPanel();
        leftOneLabelPanel = new JPanel();
        leftTwoLabelPanel = new JPanel();
        leftTwo = new JPanel();
        rightParent = new JPanel();
        rightTop = new JPanel();
        rightBottom = new JPanel();

        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");

        timerLabel = new JLabel();
        estimatedLOC = new JLabel("Estimated LOC: ");
        actualLOC = new JLabel("Actual LOC: ");
        estimatedTime = new JLabel("Estimated Time(hrs): ");
        actualTime = new JLabel("Actual Time(hrs): ");
        locPerHour = new JLabel("Actual LOC/h: ");
        estimatedLOCPH = new JLabel("Estimated LOC/h: ");
        startDate = new JLabel("Start Date: ");
        endDate = new JLabel("End Date: ");
        scheduleStatus = new JLabel("Schedule Status: ");
        taskName = new JLabel("Task Name: ");

        estimatedLOC.setVerticalAlignment(JLabel.CENTER);
        actualLOC.setVerticalAlignment(JLabel.CENTER);
        estimatedTime.setVerticalAlignment(JLabel.CENTER);
        actualTime.setVerticalAlignment(JLabel.CENTER);
        locPerHour.setVerticalAlignment(JLabel.CENTER);
        estimatedLOCPH.setVerticalAlignment(JLabel.CENTER);
        startDate.setVerticalAlignment(JLabel.CENTER);
        endDate.setVerticalAlignment(JLabel.CENTER);
        scheduleStatus.setVerticalAlignment(JLabel.CENTER);
        taskName.setVerticalAlignment(JLabel.CENTER);

        taskName.setHorizontalAlignment(JLabel.CENTER);
        estimatedLOC.setHorizontalAlignment(JLabel.CENTER);
        actualLOC.setHorizontalAlignment(JLabel.CENTER);
        estimatedTime.setHorizontalAlignment(JLabel.CENTER);
        actualTime.setHorizontalAlignment(JLabel.CENTER);
        locPerHour.setHorizontalAlignment(JLabel.CENTER);
        estimatedLOCPH.setHorizontalAlignment(JLabel.CENTER);
        startDate.setHorizontalAlignment(JLabel.CENTER);
        endDate.setHorizontalAlignment(JLabel.CENTER);
        scheduleStatus.setHorizontalAlignment(JLabel.CENTER);


        parentDimension = new Dimension(Develop.SCREEN_WIDTH - 100,((int) (Develop.SCREEN_HEIGHT*topRatio)) - 5);
        leftParentDim = new Dimension((int) (parentDimension.getWidth() * .6),(int) parentDimension.getHeight());
        rightParentDim = new Dimension((int)(parentDimension.getWidth() * .4),(int)parentDimension.getHeight());
        innerLeftDim = new Dimension((int)(leftParentDim.getWidth() * .5),(int) leftParentDim.getHeight());
        innerRightDim = new Dimension((int)rightParentDim.getWidth(),(int)(rightParent.getHeight() * .5));
        leftLabelDim = new Dimension((int)(innerLeftDim.getWidth() * .9),(int)(innerLeftDim.getHeight() * .7));

        leftLabelLayout = new GridLayout(5,0);
        parentLayout = new GridLayout(1,1);
        leftChildLayout = new BorderLayout();
    }
    	public void editComponents()
    	{
    		//setFont(estimatedLOC);
    		editPanels();
 
    		startButton.setActionCommand("start");
    		pauseButton.setActionCommand("pause");
            
            if(currentTask != null){
				timerLabel = new JLabel();
				estimatedLOC = new JLabel("Estimated LOC: " + Integer.toString(currentTask.getEstimatedLOC()));
				actualLOC = new JLabel("Actual LOC: " + Integer.toString(currentTask.getActualLOC()));
				estimatedTime = new JLabel("Estimated Time(hrs): " + Float.toString(currentTask.getEstimatedTime()));
				actualTime = new JLabel("Actual Time(hrs): " + Float.toString(currentTask.getActualTime()));
				locPerHour = new JLabel("Actual LOC/h: " + Float.toString(currentTask.getLocPerHour()));
				estimatedLOCPH = new JLabel("Estimated LOC/h: " + Float.toString(currentTask.getEstimatedLOCPH()));
				startDate = new JLabel("Start Date: " + currentTask.getStartDate());
				endDate = new JLabel("End Date: " + currentTask.getEndDate());
				
				if(currentTask.getScheduleStatus() == TaskCard.ON_TIME)
					scheduleStatus = new JLabel("Schedule Status: ON TIME");
				else if(currentTask.getScheduleStatus() == TaskCard.BEHIND_SCHED)
					scheduleStatus = new JLabel("Schedule Status: BEHIND");
				else
					scheduleStatus = new JLabel("Schedule Status: AHEAD");
            }
    	}
    	public void addActionListeners()
    	{

    	}
    	public void addComponents()
    	{
    		leftOneLabelPanel.add(estimatedLOC);
    		leftOneLabelPanel.add(actualLOC);
    		leftOneLabelPanel.add(estimatedTime);
    		leftOneLabelPanel.add(actualTime);
    		leftOneLabelPanel.add(scheduleStatus);

    		leftTwoLabelPanel.add(startDate);
    		leftTwoLabelPanel.add(endDate);
    		leftTwoLabelPanel.add(locPerHour);
    		leftTwoLabelPanel.add(estimatedLOCPH);
    		leftTwoLabelPanel.add(taskName);


    		leftOne.add(leftOneLabelPanel,BorderLayout.CENTER);
    		leftTwo.add(leftTwoLabelPanel,BorderLayout.CENTER);

    		leftParent.add(leftOneLabelPanel);
    		leftParent.add(leftTwo);
       		
            this.add(leftParent);
            this.add(rightParent);
    	}

    	public void editPanels(){
    		/*leftTwo.setOpaque(true);
    		leftParent.setOpaque(true);
    		rightParent.setOpaque(true);
    		leftOne.setOpaque(true);
    		rightTop.setOpaque(true);
    		rightBottom.setOpaque(true);*/
    		//leftOneLabelPanel.setOpaque(true);
    		//leftTwoLabelPanel.setOpaque(true);

    		/*leftParent.setBackground(Color.blue);
    		rightParent.setBackground(Color.red);
    		rightTop.setBackground(Color.gray);
    		rightBottom.setBackground(Color.cyan);
    		leftTwo.setBackground(Color.black);*/
    		//leftTwoLabelPanel.setBackground(Color.cyan);
    		//leftOne.setBackground(Color.yellow);
    		//leftOneLabelPanel.setBackground(Color.red);


    		


            this.setBorder(BorderFactory.createLineBorder(Color.black));
            this.setPreferredSize(parentDimension);

            leftParent.setPreferredSize(leftParentDim);
            leftOne.setPreferredSize(innerLeftDim);
           // leftOneLabelPanel.setPreferredSize(leftLabelDim);
            leftTwo.setPreferredSize(innerLeftDim);
            //leftTwoLabelPanel.setPreferredSize(leftLabelDim);
            rightParent.setPreferredSize(rightParentDim);
            rightTop.setPreferredSize(innerRightDim);
            rightBottom.setPreferredSize(innerRightDim);


            this.setLayout(parentLayout);

            leftParent.setLayout(parentLayout);
            leftOne.setLayout(leftChildLayout);
            leftTwo.setLayout(leftChildLayout);
            leftOneLabelPanel.setLayout(leftLabelLayout);
            leftTwoLabelPanel.setLayout(leftLabelLayout);


    	}

    	private void setFont(JLabel label){
    		Font labelFont = label.getFont();
			String labelText = label.getText();

			int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
			int componentWidth = label.getWidth();

			// Find out how much the font can grow in width.
			double widthRatio = (double)componentWidth / (double)stringWidth;

			int newFontSize = (int)(labelFont.getSize() * widthRatio);
			int componentHeight = label.getHeight();

			// Pick a new font size so it will not be larger than the height of label.
			int fontSizeToUse = Math.min(newFontSize, componentHeight);

			// Set the label's font size to the newly determined size.
			label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
    	}

	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand().equalsIgnoreCase("start")){
				timer.start();
			}
		}
	}
}
