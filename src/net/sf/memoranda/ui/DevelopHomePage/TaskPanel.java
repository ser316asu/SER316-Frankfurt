package net.sf.memoranda.ui.DevelopHomePage;
/**

swhacks.org
Joshua Becker
**/
import javax.swing.*;
import java.awt.*;

public class TaskPanel extends JLabel implements Styling
{
    private Dimension dimension;
    private TaskCard task;
    private JLabel name_L, schStat_L, startDate_L, header_L, footer_L,labelName,labelSched,labelStart;
    private TopHomePanel topHomePanel;
    public TaskPanel()
    {
        this.dimension = new Dimension(200,400);
    	createComponents();
    	editComponents();
    	addActionListeners();
    	addComponents();
    }
    public TaskPanel(int width,int height, TaskCard card, TopHomePanel thp)
    {
        this.topHomePanel = thp;
        this.dimension = new Dimension(width, height);
        this.task = card;
        createComponents();
        editComponents();
        addActionListeners();
        style();
        addComponents();
    }


    public void createComponents()
    {
           name_L = new JLabel(this.task.getTaskName());
           startDate_L = new JLabel();
           schStat_L = new JLabel();
           header_L = new JLabel();
           footer_L = new JLabel();
           labelName = new JLabel();
           labelSched = new JLabel();
           labelStart = new JLabel();
    }
       public void style()
       {
           this.setBackground(Styling.TASK_PANEL_COLOR);
           this.setFont(Styling.TASK_PANEL_FONT);
           //this.setBorder(BorderFactory.createLineBorder(Styling.TASK_PANEL_COLOR));
           //header_L.setBorder(BorderFactory.createLineBorder(Styling.TASK_PANEL_BOARDER_COLOR));
           //footer_L.setBorder(BorderFactory.createLineBorder(Styling.TASK_PANEL_BOARDER_COLOR));
           
           name_L.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
           startDate_L.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
           schStat_L.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
           header_L.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
           footer_L.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
           labelName.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
           labelSched.setForeground(Styling.TASK_PANEL_TEXT_COLOR);
           labelStart.setForeground(Styling.TASK_PANEL_TEXT_COLOR);

           labelName.setFont(Styling.TASK_PANEL_FONT);
           labelSched.setFont(Styling.TASK_PANEL_FONT);
           labelStart.setFont(Styling.TASK_PANEL_FONT);
           name_L.setFont(Styling.TASK_PANEL_FONT);
           startDate_L.setFont(Styling.TASK_PANEL_FONT);
           schStat_L.setFont(Styling.TASK_PANEL_FONT);
           header_L.setFont(Styling.TASK_PANEL_FONT);
           footer_L.setFont(Styling.TASK_PANEL_FONT);
           
           this.setIcon(LoadAssets.TASK_PANEL_IMAGE);
       }
    public void editComponents()
    {
           this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
           header_L.setLayout(new BoxLayout(header_L, BoxLayout.Y_AXIS));
           footer_L.setLayout(new BoxLayout(footer_L, BoxLayout.Y_AXIS));
           
           header_L.setPreferredSize(new Dimension(this.dimension.width,(int)(this.dimension.height*.31)));
           footer_L.setPreferredSize(new Dimension(this.dimension.width,(int)(this.dimension.height*.69)));
           this.setPreferredSize(this.dimension);
           
           header_L.setMinimumSize(new Dimension(this.dimension.width,(int)(this.dimension.height*.31)));
           footer_L.setMinimumSize(new Dimension(this.dimension.width,(int)(this.dimension.height*.69)));
           header_L.setMaximumSize(new Dimension(this.dimension.width,(int)(this.dimension.height*.31)));
           footer_L.setMaximumSize(new Dimension(this.dimension.width,(int)(this.dimension.height*.69)));
           
           schStat_L.setText(this.task.scheduleStatusToString());
           startDate_L.setText(this.task.formatDate(this.task.getEndDate()));
           
    }
    public void addActionListeners()
    {
           this.addMouseListener(new TaskPanelMouseListener(this, this.topHomePanel));
    }
    public void addComponents()
    {
           addSpacing(header_L);
           labelName.setText("Task Name");
           header_L.add(labelName);
           header_L.add(name_L);
           addSpacing(header_L);
           
           addSpacing(footer_L);
           labelSched.setText("Schedule State");
           footer_L.add(labelSched);
           footer_L.add(schStat_L);
           addSpacing(footer_L);
           labelStart.setText("Start Date");
           footer_L.add(labelStart);
           footer_L.add(startDate_L);
           addSpacing(footer_L);
           
           centerLabels(header_L);
           centerLabels(footer_L);
           
           this.add(header_L);
           this.add(footer_L);
           centerLabels(this);

    }
       private void centerLabels(JLabel label)
       {
           for(Component tmp : label.getComponents())
           {
               ((JLabel) tmp).setAlignmentX(Component.CENTER_ALIGNMENT);
           }
       }
       private void centerLabels(JPanel panel)
       {
           for(Component tmp : panel.getComponents())
           {
               ((JLabel) tmp).setAlignmentX(Component.CENTER_ALIGNMENT);
           }
       }
       private void addSpacing(JLabel label)
       {
           label.add(new JLabel("\n"));
       }
       private void addSpacing(JPanel panel)
       {
           panel.add(new JLabel("\n"));
       }
       public void resetSize()
       {
           this.setPreferredSize(this.dimension);
           this.revalidate();
       }

       public int getHeight()
       {
           return this.dimension.height;
       }
       public int getWidth()
       {
           return this.dimension.width;
       }
       public void setWidth(int width)
       {
           this.dimension.width = width;
       }
       public void setHeight(int height)
       {
           this.dimension.height = height;
       }
       public TaskCard getTaskCard()
       {
           return this.task;
       }
       public void setTaskCard(TaskCard task)
       {
           this.task = task;
       }
}
