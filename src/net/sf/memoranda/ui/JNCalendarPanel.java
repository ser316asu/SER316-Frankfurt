package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.NoteList;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectListener;
import net.sf.memoranda.ResourcesList;
import net.sf.memoranda.TaskList;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.util.Local;

/**
 * 
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

/*$Id: JNCalendarPanel.java,v 1.9 2004/04/05 10:05:44 alexeya Exp $*/
public class JNCalendarPanel extends JPanel {

  CalendarDate _date = CurrentDate.get();
  JToolBar navigationBar = new JToolBar();
  JPanel mntyPanel = new JPanel(new BorderLayout());
  JPanel navigationPanel = new JPanel(new GridLayout(1,3));
  
  // 1st level Panels
  JPanel todayAndPrevNextPanel = new JPanel(new GridLayout(1,2));
  JPanel monthAndYearPanel =  new JPanel();
  JPanel searchAndCreateTaskPanel = new JPanel(new GridLayout(1,2));
  
  // 2nd level Panels
  JPanel todayButtonPanel = new JPanel();
  JPanel prevAndNextMonthPanel = new JPanel(new GridLayout(1,2));
  JPanel searchButtonPanel = new JPanel();
  JPanel addTaskButtonPanel = new JPanel();
  
  // 3rd level Panels
  JPanel monthBackButtonPanel = new JPanel();
  JPanel monthForwardButtonPanel = new JPanel();
  
  // Buttons
  JButton todayButton = new JButton();
  JButton monthBackButton = new JButton();
  JButton monthForwardButton = new JButton();
  JButton searchButton = new JButton();
  JButton addTaskButton = new JButton(); 
  
  // Labels
  JLabel monthAndYearLabel = new JLabel();
  
  JComboBox monthsCB = new JComboBox(Local.getMonthNames());
  BorderLayout borderLayout4 = new BorderLayout();
  JNCalendar jnCalendar = new JNCalendar(CurrentDate.get());
  JPanel jnCalendarPanel = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JSpinner yearSpin = new JSpinner(new SpinnerNumberModel(jnCalendar.get().getYear(), 1980, 2999, 1));
  JSpinner.NumberEditor yearSpinner = new JSpinner.NumberEditor(yearSpin, "####");

  boolean ignoreChange = false;

  private Vector selectionListeners = new Vector();

  Border border1;
  Border border2;

  public JNCalendarPanel() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      new ExceptionDialog(ex);
    }
  }
  
  public Action todayAction =
	        new AbstractAction(
	            "Go to today",
	            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/today16.png"))) {
	        public void actionPerformed(ActionEvent e) {
	            todayButton_actionPerformed(e);
	        }
	  };

  public Action dayBackAction =
        new AbstractAction(
            "Go one month back",
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/back16.png"))) {
        public void actionPerformed(ActionEvent e) {
            monthBackButton_actionPerformed(e);
        }
  };
  
  public Action dayForwardAction =
        new AbstractAction(
            "Go one month forward",
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/forward16.png"))) {
        public void actionPerformed(ActionEvent e) {
            monthForwardButton_actionPerformed(e);
        }
  };
  
  public Action searchAction =
        new AbstractAction(
            "search for task",
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/today16.png"))) {
        public void actionPerformed(ActionEvent e) {
            searchButton_actionPerformed(e);
        }
  };
	  
  public Action addTaskAction =
        new AbstractAction(
            "add task",
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/today16.png"))) {
        public void actionPerformed(ActionEvent e) {
            addTaskButton_actionPerformed(e);
        }
  };
  
      
  void jbInit() throws Exception {
    //dayBackAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, KeyEvent.ALT_MASK));
    //dayForwardAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, KeyEvent.ALT_MASK));
    todayAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_HOME, KeyEvent.ALT_MASK));
    
    monthsCB.setRequestFocusEnabled(false);
    monthsCB.setMaximumRowCount(12);
    monthsCB.setPreferredSize(new Dimension(50 , 20));
    border1 = BorderFactory.createEmptyBorder(0,0,5,0);
    border2 = BorderFactory.createEmptyBorder();
    this.setLayout(new BorderLayout());
    navigationBar.setFloatable(false);
    
    // Month / Year Label Creation
    monthAndYearLabel.setText(Local.getMonthName(_date.getMonth()) + " " + _date.getYear());
    
    // Creation of Buttons
    todayButton.setAction(todayAction);
    todayButton.setMinimumSize(new Dimension(24, 24));
    todayButton.setOpaque(false);
    todayButton.setPreferredSize(new Dimension(24, 24));
    todayButton.setRequestFocusEnabled(false);
    todayButton.setBorderPainted(false);
    todayButton.setFocusPainted(false);
    todayButton.setContentAreaFilled(false);
    todayButton.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/today_icon.png")));
    todayButton.setText("");
    todayButton.setToolTipText(Local.getString("To today"));
    
    monthBackButton.setAction(dayBackAction);
    monthBackButton.setMinimumSize(new Dimension(24, 24));
    monthBackButton.setOpaque(false);
    monthBackButton.setPreferredSize(new Dimension(24, 24));
    monthBackButton.setRequestFocusEnabled(false);
    monthBackButton.setToolTipText("");
    monthBackButton.setBorderPainted(false);
    monthBackButton.setFocusPainted(false);
    monthBackButton.setContentAreaFilled(false);
    monthBackButton.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/prevday_icon.png")));
    monthBackButton.setText("");
    monthBackButton.setToolTipText(Local.getString("One day back"));
    
    monthForwardButton.setAction(dayForwardAction);
    monthForwardButton.setMinimumSize(new Dimension(24, 24));
    monthForwardButton.setOpaque(false);
    monthForwardButton.setPreferredSize(new Dimension(24, 24));
    monthForwardButton.setRequestFocusEnabled(false);
    monthForwardButton.setBorderPainted(false);
    monthForwardButton.setFocusPainted(false);
    monthForwardButton.setContentAreaFilled(false);
    monthForwardButton.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/nextday_icon.png")));
    monthForwardButton.setText("");
    monthForwardButton.setToolTipText(Local.getString("One day forward"));
    
    searchButton.setAction(searchAction);
    searchButton.setMinimumSize(new Dimension(24, 24));
    searchButton.setOpaque(false);
    searchButton.setPreferredSize(new Dimension(24, 24));
    searchButton.setRequestFocusEnabled(false);
    searchButton.setToolTipText("");
    searchButton.setBorderPainted(false);
    searchButton.setFocusPainted(false);
    searchButton.setContentAreaFilled(false);
    searchButton.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/search_icon.png")));
    searchButton.setText("");
    searchButton.setToolTipText(Local.getString("Search for Task"));
    
    addTaskButton.setAction(addTaskAction);
    addTaskButton.setMinimumSize(new Dimension(24, 24));
    addTaskButton.setOpaque(false);
    addTaskButton.setPreferredSize(new Dimension(24, 24));
    addTaskButton.setRequestFocusEnabled(false);
    addTaskButton.setToolTipText("");
    addTaskButton.setBorderPainted(false);
    addTaskButton.setFocusPainted(false);
    addTaskButton.setContentAreaFilled(false);
    addTaskButton.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/addtask_icon.png")));
    addTaskButton.setText("");
    addTaskButton.setToolTipText(Local.getString("Add Task"));
    
    // Creation of Button Specific Panels
    todayButtonPanel.setMinimumSize(new Dimension(68, 24));
    todayButtonPanel.setOpaque(false);
    todayButtonPanel.setPreferredSize(new Dimension(51, 24));
    todayButtonPanel.add(todayButton, null);
    
    monthForwardButtonPanel.setAlignmentX((float) 0.0);
    monthForwardButtonPanel.setMinimumSize(new Dimension(40, 24));
    monthForwardButtonPanel.setOpaque(false);
    monthForwardButtonPanel.setPreferredSize(new Dimension(40, 24));
    monthForwardButtonPanel.add(monthForwardButton, null);
    
    monthBackButtonPanel.setAlignmentX((float) 1.5);
    monthBackButtonPanel.setMinimumSize(new Dimension(40, 24));
    monthBackButtonPanel.setOpaque(false);
    monthBackButtonPanel.setPreferredSize(new Dimension(40, 24));
    monthBackButtonPanel.add(monthBackButton, null);  
    
    searchButtonPanel.setAlignmentX((float) 1.5);
    searchButtonPanel.setMinimumSize(new Dimension(40, 24));
    searchButtonPanel.setOpaque(false);
    searchButtonPanel.setPreferredSize(new Dimension(40, 24));
    searchButtonPanel.add(searchButton, null);  
    
    addTaskButtonPanel.setAlignmentX((float) 1.5);
    addTaskButtonPanel.setMinimumSize(new Dimension(40, 24));
    addTaskButtonPanel.setOpaque(false);
    addTaskButtonPanel.setPreferredSize(new Dimension(40, 24));
    addTaskButtonPanel.add(addTaskButton, null); 

    prevAndNextMonthPanel.add(monthBackButtonPanel);
    prevAndNextMonthPanel.add(monthForwardButtonPanel);
    
    todayAndPrevNextPanel.add(todayButtonPanel);
    todayAndPrevNextPanel.add(prevAndNextMonthPanel);
    
    monthAndYearPanel.add(monthAndYearLabel);
    
    searchAndCreateTaskPanel.add(searchButtonPanel);
    searchAndCreateTaskPanel.add(addTaskButtonPanel);
    
    navigationPanel.add(todayAndPrevNextPanel);
    navigationPanel.add(monthAndYearPanel);
    navigationPanel.add(searchAndCreateTaskPanel);
    
    yearSpin.setPreferredSize(new Dimension(70, 20));
    yearSpin.setRequestFocusEnabled(false);
        yearSpin.setEditor(yearSpinner);
    navigationPanel.setMinimumSize(new Dimension(202, 30));
    navigationPanel.setOpaque(false);
    navigationPanel.setPreferredSize(new Dimension(155, 30));
    jnCalendar.getTableHeader().setFont(new java.awt.Font("Dialog", 1, 10));
    jnCalendar.setFont(new java.awt.Font("Dialog", 0, 10));
    jnCalendar.setGridColor(Color.lightGray);
    jnCalendarPanel.setLayout(borderLayout5);    
    
    navigationBar.add(navigationPanel, null);
    
    mntyPanel.add(monthsCB, BorderLayout.CENTER);
    mntyPanel.add(yearSpin,  BorderLayout.EAST);

    // Putting Large Pieces together for calendar
    this.add(navigationBar, BorderLayout.NORTH);
    this.add(jnCalendarPanel,  BorderLayout.CENTER);
//    this.add(mntyPanel,  BorderLayout.SOUTH); Temp Removal of month and year scroll pickers
    
    jnCalendar.getTableHeader().setPreferredSize(new Dimension(200, 15));
    jnCalendarPanel.add(jnCalendar.getTableHeader(), BorderLayout.NORTH);
    jnCalendarPanel.add(jnCalendar, BorderLayout.CENTER);
    jnCalendar.addSelectionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        setCurrentDateDay(jnCalendar.get(), jnCalendar.get().getDay());
      }
    });
    /*CurrentDate.addChangeListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        _date = CurrentDate.get();
        refreshView();
      }
    });*/
    monthsCB.setFont(new java.awt.Font("Dialog", 0, 11));

    monthsCB.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        monthsCB_actionPerformed(e);
      }
    });

    yearSpin.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        yearSpin_actionPerformed();
      }
    });
    CurrentProject.addProjectListener(new ProjectListener() {
            public void projectChange(Project p, NoteList nl, TaskList tl, ResourcesList rl) {}
            public void projectWasChanged() {
                jnCalendar.updateUI();
            }
        });


    refreshView();
    yearSpin.setBorder(border2);
    
  }

  public void set(CalendarDate date) {
    _date = date;
    refreshView();
  }

  public CalendarDate get() {
    return _date;
  }

  public void addSelectionListener(ActionListener al) {
        selectionListeners.add(al);
    }

  private void notifyListeners() {
        for (Enumeration en = selectionListeners.elements(); en.hasMoreElements();)
             ((ActionListener) en.nextElement()).actionPerformed(new ActionEvent(this, 0, "Calendar event"));
  }

  private void setCurrentDateDay(CalendarDate dt, int d) {
    if (ignoreChange) return;
    if (_date.equals(dt)) return;
    _date = new CalendarDate(d, _date.getMonth(), _date.getYear());
    notifyListeners();
  }

  private void refreshView() {
    ignoreChange = true;
    jnCalendar.set(_date);
    monthsCB.setSelectedIndex(new Integer(_date.getMonth()));
    yearSpin.setValue(new Integer(_date.getYear()));
    monthAndYearLabel.setText(Local.getMonthName(_date.getMonth()) + " " + _date.getYear());
    ignoreChange = false;
  }

  void monthsCB_actionPerformed(ActionEvent e) {
    if (ignoreChange) return;
    _date = new CalendarDate(_date.getDay(), monthsCB.getSelectedIndex(), _date.getYear());
    jnCalendar.set(_date);
    notifyListeners();
  }

  void yearSpin_actionPerformed() {
    if (ignoreChange) return;
    _date = new CalendarDate(_date.getDay(), _date.getMonth(), ((Integer)yearSpin.getValue()).intValue());
    jnCalendar.set(_date);
    notifyListeners();
  }

  void monthBackButton_actionPerformed(ActionEvent e) {
    Calendar cal = _date.getCalendar();
    cal.add(Calendar.MONTH, -1); cal.getTime();
    _date = new CalendarDate(cal);
    refreshView();
    notifyListeners();
  }

  void todayButton_actionPerformed(ActionEvent e) {
    _date = CalendarDate.today();
    refreshView();
    notifyListeners();
  }

  void monthForwardButton_actionPerformed(ActionEvent e) {
    Calendar cal = _date.getCalendar();
    cal.add(Calendar.MONTH, 1); cal.getTime();
    _date = new CalendarDate(cal);
    refreshView();
    notifyListeners();
  }
  
  // TODO search button backend logic
  void searchButton_actionPerformed(ActionEvent e) {
	  System.out.println("Called to search tasks");
	  TaskSearchPanel searchPanel = new TaskSearchPanel();
	  this.add(searchPanel,  BorderLayout.SOUTH);
	  refreshView();
  }
  
  void addTaskButton_actionPerformed(ActionEvent e) {
	  System.out.println("Called to add a task");
	  NewTaskWindow taskCreationWindow = new NewTaskWindow(); // Creates task creation window
  }



}