/**************************************************************
 * Copyright (c) 2017 - 2017, Joshua Becker, Alec Shinn,
 * All rights reserved
 * SER316-Frankfurt is a project for ser216, 
 * using agile scrum.
 * Description:
 * 
 * Contact: jdbecke3@asu.edu, atshinn@asu.edu
 **************************************************************/
package net.sf.memoranda.ui.DevelopHomePage;
/**

swhacks.org
Joshua Becker
**/
import javax.swing.*;
import java.awt.*;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskPanel.
 */
public class TaskPanel extends JLabel implements Styling
{
    
    /** The dimension. */
    private Dimension dimension;
    
    /** The task. */
    private TaskCard task;
    
    /** The label start. */
    private JLabel name_L, schStat_L, startDate_L, header_L, footer_L,labelName,labelSched,labelStart;
    
    /** The top home panel. */
    private TopHomePanel topHomePanel;
    
    /**
     * Instantiates a new task panel.
     */
    public TaskPanel()
    {
        this.dimension = new Dimension(Styling.TASK_PANEL_WIDTH,Styling.TASK_PANEL_HEIGHT);
    	createComponents();
    	editComponents();
    	addActionListeners();
    	addComponents();
    }
    
    /**
     * Instantiates a new task panel.
     *
     * @param width the width
     * @param height the height
     * @param card the card
     * @param thp the thp
     */
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
    
    /**
     * Instantiates a new task panel.
     *
     * @param card the card
     * @param thp the thp
     */
    public TaskPanel(TaskCard card, TopHomePanel thp)
    {
        this.topHomePanel = thp;
        this.dimension = new Dimension(Styling.TASK_PANEL_WIDTH,Styling.TASK_PANEL_HEIGHT);
        this.task = card;
        createComponents();
        editComponents();
        addActionListeners();
        style();
        addComponents();
    }

    /**
     * Creates the components.
     */
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
       
       /* (non-Javadoc)
        * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
        */
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
    
    /**
     * Edits the components.
     */
    public void editComponents()
    {
           this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
           header_L.setLayout(new BoxLayout(header_L, BoxLayout.Y_AXIS));
           footer_L.setLayout(new BoxLayout(footer_L, BoxLayout.Y_AXIS));
           
           header_L.setPreferredSize(new Dimension(this.dimension.width,(int)(this.dimension.height)));
           footer_L.setPreferredSize(this.dimension);
           this.setPreferredSize(this.dimension);
           
           header_L.setMinimumSize(new Dimension(this.dimension.width,(int)(this.dimension.height*.35)));
           footer_L.setMinimumSize(this.dimension);
           header_L.setMaximumSize(new Dimension(this.dimension.width,(int)(this.dimension.height*.35)));
           footer_L.setMaximumSize(this.dimension);
           
           schStat_L.setText(this.task.scheduleStatusToString());
           startDate_L.setText(this.task.formatDate(this.task.getEndDate()));
           
    }
    
    /**
     * Adds the action listeners.
     */
    public void addActionListeners()
    {
           this.addMouseListener(new TaskPanelMouseListener(this, this.topHomePanel));
    }
    
    /**
     * Adds the components.
     */
    public void addComponents()
    {
           addSpacing(footer_L);
           labelName.setText("Task Name");
           footer_L.add(labelName);
           footer_L.add(name_L);
           addSpacing(header_L);
           //addSpacing(header_L);
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
           
           //this.add(header_L);
           this.add(footer_L);
           centerLabels(this);

    }
       
       /**
        * Center labels.
        *
        * @param label the label
        */
       private void centerLabels(JLabel label)
       {
           for(Component tmp : label.getComponents())
           {
               ((JLabel) tmp).setAlignmentX(Component.CENTER_ALIGNMENT);
           }
       }
       
       /**
        * Center labels.
        *
        * @param panel the panel
        */
       private void centerLabels(JPanel panel)
       {
           for(Component tmp : panel.getComponents())
           {
               ((JLabel) tmp).setAlignmentX(Component.CENTER_ALIGNMENT);
           }
       }
       
       /**
        * Adds the spacing.
        *
        * @param label the label
        */
       private void addSpacing(JLabel label)
       {
           label.add(new JLabel("\n"));
       }
       
       /**
        * Adds the spacing.
        *
        * @param panel the panel
        */
       private void addSpacing(JPanel panel)
       {
           panel.add(new JLabel("\n"));
       }
       
       /**
        * Reset size.
        */
       public void resetSize()
       {
           this.setPreferredSize(this.dimension);
           this.revalidate();
       }

       /* (non-Javadoc)
        * @see javax.swing.JComponent#getHeight()
        */
       public int getHeight()
       {
           return this.dimension.height;
       }
       
       /* (non-Javadoc)
        * @see javax.swing.JComponent#getWidth()
        */
       public int getWidth()
       {
           return this.dimension.width;
       }
       
       /**
        * Sets the width.
        *
        * @param width the new width
        */
       public void setWidth(int width)
       {
           this.dimension.width = width;
       }
       
       /**
        * Sets the height.
        *
        * @param height the new height
        */
       public void setHeight(int height)
       {
           this.dimension.height = height;
       }
       
       /**
        * Gets the task card.
        *
        * @return the task card
        */
       public TaskCard getTaskCard()
       {
           return this.task;
       }
       
       /**
        * Sets the task card.
        *
        * @param task the new task card
        */
       public void setTaskCard(TaskCard task)
       {
           this.task = task;
       }
}
