/**

**/
import javax.swing.*;
import java.awt.*; 
public class TopHomePanel extends JPanel
{
	private final double topRatio = .4;
    private TaskCard task;
    private JPanel leftPanel,rightParent,rightTop,rightBottom;
    private JButton startButton,pauseButton;
    private JLabel timerLabel,estimatedLOC,actualLOC,taskName,estimatedTime,actualTime;
    private JLabel locPerHour,estimatedLOCPH,startDate,endDate,scheduleStatus;
    private final String AHEAD = "Ahead of Schedule";
    private final String BEHIND = "Behind Schedule";
    private final String ONTIME = "On Time";
    private TaskCard currentTask;
    private Timer timer;
    private Dimension parentDimension,rightParentDim,leftPanelDim,rightTopDim,rightBottomDim;

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
        leftPanel = new JPanel();
        rightParent = new JPanel();
        rightTop = new JPanel();
        rightBottom = new JPanel();

        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");

        timerLabel = new JLabel();
        estimatedLOC = new JLabel();
        actualLOC = new JLabel();
        estimatedTime = new JLabel();
        actualTime = new JLabel();
        locPerHour = new JLabel();
        estimatedLOCPH = new JLabel();
        startDate = new JLabel();
        endDate = new JLabel();
        scheduleStatus = new JLabel();

        parentDimension = new Dimension(Develop.SCREEN_WIDTH - 100,((int) (Develop.SCREEN_HEIGHT*topRatio)) - 5);
        leftPanelDim = new Dimension((int)((Develop.SCREEN_WIDTH - 100) * .6),((int) (Develop.SCREEN_HEIGHT*topRatio)) - 5);
        rightParentDim = new Dimension((int)((Develop.SCREEN_WIDTH - 100) * .4),((int) (Develop.SCREEN_HEIGHT*topRatio)) - 5);
    }
    	public void editComponents()
    	{
            GridLayout parentLayout = new GridLayout(1,1);

            this.setBorder(BorderFactory.createLineBorder(Color.black));
            this.setPreferredSize(parentDimension);
            

            leftPanel.setPreferredSize(leftPanelDim);
            rightParent.setPreferredSize(rightParentDim);

            leftPanel.setOpaque(true);
            rightParent.setOpaque(true);

            leftPanel.setBackground(Color.red);
            rightParent.setBackground(Color.blue);
            

            this.setLayout(parentLayout);
    	}
    	public void addActionListeners()
    	{

    	}
    	public void addComponents()
    	{
            
            this.add(leftPanel);
            this.add(rightParent);
    	}
}
