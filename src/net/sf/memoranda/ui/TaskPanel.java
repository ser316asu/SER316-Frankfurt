package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.History;
import net.sf.memoranda.NoteList;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectListener;
import net.sf.memoranda.ResourcesList;
import net.sf.memoranda.Task;
import net.sf.memoranda.TaskList;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.date.DateListener;
import net.sf.memoranda.ui.develop.DailyItemsPanelViewPort;
import net.sf.memoranda.util.Context;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Util;
  
/*$Id: TaskPanel.java,v 1.27 2007/01/17 20:49:12 killerjoe Exp $*/
public class TaskPanel extends JPanel implements Observer {
	
	private static TaskPanel singletonTaskPanel;
	private static int numberOfNewTaskPopUps = 1;
	private static int numberOfEditTaskPopUps = 1;
	
    static BorderLayout borderLayout1 = new BorderLayout();
    static JToolBar tasksToolBar = new JToolBar();
    static JButton newTaskB = new JButton();
    static JButton subTaskB = new JButton();
    static JButton editTaskB = new JButton();
    static JButton removeTaskB = new JButton();
    static JButton completeTaskB = new JButton();
    
    static JCheckBoxMenuItem ppShowActiveOnlyChB = new JCheckBoxMenuItem();
		
    static JScrollPane scrollPane = new JScrollPane();
    static TaskTable taskTable = new TaskTable();
    static JMenuItem ppEditTask = new JMenuItem();
    static JPopupMenu taskPPMenu = new JPopupMenu();
    static JMenuItem ppRemoveTask = new JMenuItem();
    static JMenuItem ppNewTask = new JMenuItem();
    static JMenuItem ppCompleteTask = new JMenuItem();
	//JMenuItem ppSubTasks = new JMenuItem();
	//JMenuItem ppParentTask = new JMenuItem();
    static JMenuItem ppAddSubTask = new JMenuItem();
    static JMenuItem ppCalcTask = new JMenuItem();
    static DailyItemsPanel parentPanel = null;
	
	SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
	
	public synchronized static TaskPanel getInstance(DailyItemsPanel _parentPanel) {
		  if (singletonTaskPanel == null) {
			  singletonTaskPanel = new TaskPanel(_parentPanel);
			  try {
				  singletonTaskPanel.jbInit();
			   }
			    catch(Exception ex) {
			      new ExceptionDialog(ex);
			   }
		  }
		  return singletonTaskPanel;
	  }

    private TaskPanel(DailyItemsPanel _parentPanel) {
        //((Observable) CurrentProject.getTaskList()).addObserver(this);
    	try {
            parentPanel = _parentPanel;
            //System.out.println("\n\n\n DEBUG LINE");
            //jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void jbInit() throws Exception {
    	this.scrollPane.setViewport(new DailyItemsPanelViewPort(DailyItemsPanelViewPort.TASK_VIEW));

        tasksToolBar.setFloatable(false);

        newTaskB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_new.png")));
        newTaskB.setEnabled(true);
        newTaskB.setMaximumSize(new Dimension(24, 24));
        newTaskB.setMinimumSize(new Dimension(24, 24));
        newTaskB.setToolTipText(Local.getString("Create new task"));
        newTaskB.setRequestFocusEnabled(false);
        newTaskB.setPreferredSize(new Dimension(24, 24));
        newTaskB.setFocusable(false);
        newTaskB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newTaskB_actionPerformed(e);
            }
        });
        newTaskB.setBorderPainted(false);
        
        subTaskB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_new_sub.png")));
        subTaskB.setEnabled(true);
        subTaskB.setMaximumSize(new Dimension(24, 24));
        subTaskB.setMinimumSize(new Dimension(24, 24));
        subTaskB.setToolTipText(Local.getString("Add subtask"));
        subTaskB.setRequestFocusEnabled(false);
        subTaskB.setPreferredSize(new Dimension(24, 24));
        subTaskB.setFocusable(false);
        subTaskB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addSubTask_actionPerformed(e);
            }
        });
        subTaskB.setBorderPainted(false);

        editTaskB.setBorderPainted(false);
        editTaskB.setFocusable(false);
        editTaskB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editTaskB_actionPerformed(e);
            }
        });
        editTaskB.setPreferredSize(new Dimension(24, 24));
        editTaskB.setRequestFocusEnabled(false);
        editTaskB.setToolTipText(Local.getString("Edit task"));
        editTaskB.setMinimumSize(new Dimension(24, 24));
        editTaskB.setMaximumSize(new Dimension(24, 24));
//        editTaskB.setEnabled(true);
        editTaskB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_edit.png")));

        removeTaskB.setBorderPainted(false);
        removeTaskB.setFocusable(false);
        removeTaskB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTaskB_actionPerformed(e);
            }
        });
        removeTaskB.setPreferredSize(new Dimension(24, 24));
        removeTaskB.setRequestFocusEnabled(false);
        removeTaskB.setToolTipText(Local.getString("Remove task"));
        removeTaskB.setMinimumSize(new Dimension(24, 24));
        removeTaskB.setMaximumSize(new Dimension(24, 24));
        removeTaskB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_remove.png")));
        
        completeTaskB.setBorderPainted(false);
        completeTaskB.setFocusable(false);
        completeTaskB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppCompleteTask_actionPerformed(e);
            }
        });
        completeTaskB.setPreferredSize(new Dimension(24, 24));
        completeTaskB.setRequestFocusEnabled(false);
        completeTaskB.setToolTipText(Local.getString("Complete task"));
        completeTaskB.setMinimumSize(new Dimension(24, 24));
        completeTaskB.setMaximumSize(new Dimension(24, 24));
        completeTaskB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_complete.png")));

		// added by rawsushi
//		showActiveOnly.setBorderPainted(false);
//		showActiveOnly.setFocusable(false);
//		showActiveOnly.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				toggleShowActiveOnly_actionPerformed(e);
//			}
//		});
//		showActiveOnly.setPreferredSize(new Dimension(24, 24));
//		showActiveOnly.setRequestFocusEnabled(false);
//		if (taskTable.isShowActiveOnly()) {
//			showActiveOnly.setToolTipText(Local.getString("Show All"));			
//		}
//		else {
//			showActiveOnly.setToolTipText(Local.getString("Show Active Only"));			
//		}
//		showActiveOnly.setMinimumSize(new Dimension(24, 24));
//		showActiveOnly.setMaximumSize(new Dimension(24, 24));
//		showActiveOnly.setIcon(
//			new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_remove.png")));
		// added by rawsushi
		
		ppShowActiveOnlyChB.setFont(new java.awt.Font("Dialog", 1, 11));
		ppShowActiveOnlyChB.setText(
			Local.getString("Show Active only"));
		ppShowActiveOnlyChB
			.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleShowActiveOnly_actionPerformed(e);
			}
		});		
		boolean isShao =
			(Context.get("SHOW_ACTIVE_TASKS_ONLY") != null)
				&& (Context.get("SHOW_ACTIVE_TASKS_ONLY").equals("true"));
		ppShowActiveOnlyChB.setSelected(isShao);
		toggleShowActiveOnly_actionPerformed(null);

		/*showActiveOnly.setPreferredSize(new Dimension(24, 24));
		showActiveOnly.setRequestFocusEnabled(false);
		if (taskTable.isShowActiveOnly()) {
			showActiveOnly.setToolTipText(Local.getString("Show All"));			
		}
		else {
			showActiveOnly.setToolTipText(Local.getString("Show Active Only"));			
		}
		showActiveOnly.setMinimumSize(new Dimension(24, 24));
		showActiveOnly.setMaximumSize(new Dimension(24, 24));
		showActiveOnly.setIcon(
			new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_active.png")));*/
		// added by rawsushi


        singletonTaskPanel.setLayout(borderLayout1);
        scrollPane.getViewport().setBackground(Color.white);
        /*taskTable.setMaximumSize(new Dimension(32767, 32767));
        taskTable.setRowHeight(24);*/
        ppEditTask.setFont(new java.awt.Font("Dialog", 1, 11));
    ppEditTask.setText(Local.getString("Edit task")+"...");
    ppEditTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppEditTask_actionPerformed(e);
            }
        });
    ppEditTask.setEnabled(false);
    ppEditTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_edit.png")));
    taskPPMenu.setFont(new java.awt.Font("Dialog", 1, 10));
    ppRemoveTask.setFont(new java.awt.Font("Dialog", 1, 11));
    ppRemoveTask.setText(Local.getString("Remove task"));
    ppRemoveTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppRemoveTask_actionPerformed(e);
            }
        });
    ppRemoveTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_remove.png")));
    ppRemoveTask.setEnabled(false);
    ppNewTask.setFont(new java.awt.Font("Dialog", 1, 11));
    ppNewTask.setText(Local.getString("New task")+"...");
    ppNewTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppNewTask_actionPerformed(e);
            }
        });
    ppNewTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_new.png")));

    ppAddSubTask.setFont(new java.awt.Font("Dialog", 1, 11));
    ppAddSubTask.setText(Local.getString("Add subtask"));
    ppAddSubTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppAddSubTask_actionPerformed(e);
            }
        });
    ppAddSubTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_new_sub.png")));

    /*
    ppSubTasks.setFont(new java.awt.Font("Dialog", 1, 11));
    ppSubTasks.setText(Local.getString("List sub tasks"));
    ppSubTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppListSubTasks_actionPerformed(e);
            }
        });
    ppSubTasks.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_new.png")));

    ppParentTask.setFont(new java.awt.Font("Dialog", 1, 11));
    ppParentTask.setText(Local.getString("Parent Task"));
    ppParentTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppParentTask_actionPerformed(e);
            }
        });
    ppParentTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_new.png")));
    */

	ppCompleteTask.setFont(new java.awt.Font("Dialog", 1, 11));
	ppCompleteTask.setText(Local.getString("Complete task"));
	ppCompleteTask.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ppCompleteTask_actionPerformed(e);
			}
		});
	ppCompleteTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_complete.png")));
	ppCompleteTask.setEnabled(false);

	ppCalcTask.setFont(new java.awt.Font("Dialog", 1, 11));
	ppCalcTask.setText(Local.getString("Calculate task data"));
	ppCalcTask.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ppCalcTask_actionPerformed(e);
			}
		});
	ppCalcTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_complete.png")));
	ppCalcTask.setEnabled(false);

    scrollPane.getViewport().add(taskTable, null);
    	singletonTaskPanel.add(scrollPane, BorderLayout.CENTER);
        tasksToolBar.addSeparator(new Dimension(8, 24));

        tasksToolBar.add(newTaskB, null);
        tasksToolBar.add(subTaskB, null);
        tasksToolBar.add(removeTaskB, null);
        tasksToolBar.addSeparator(new Dimension(8, 24));
        tasksToolBar.add(editTaskB, null);
        tasksToolBar.add(completeTaskB, null);

		//tasksToolBar.add(showActiveOnly, null);
        

        singletonTaskPanel.add(tasksToolBar, BorderLayout.NORTH);

        PopupListener ppListener = singletonTaskPanel.new PopupListener();
        scrollPane.addMouseListener(ppListener);
        taskTable.addMouseListener(ppListener);



        CurrentDate.addDateListener(new DateListener() {
            public void dateChange(CalendarDate d) {
                newTaskB.setEnabled(d.inPeriod(CurrentProject.get().getStartDate(), CurrentProject.get().getEndDate()));
            }
        });
        CurrentProject.addProjectListener(new ProjectListener() {
            public void projectChange(Project p, NoteList nl, TaskList tl, ResourcesList rl) {
                newTaskB.setEnabled(
                    CurrentDate.get().inPeriod(p.getStartDate(), p.getEndDate()));
            }
            public void projectWasChanged() {
            	//taskTable.setCurrentRootTask(null); //XXX
            }
        });
        taskTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                boolean enbl = (taskTable.getRowCount() > 0)&&(taskTable.getSelectedRow() > -1);
                editTaskB.setEnabled(enbl);ppEditTask.setEnabled(enbl);
                removeTaskB.setEnabled(enbl);ppRemoveTask.setEnabled(enbl);
				
				ppCompleteTask.setEnabled(enbl);
				completeTaskB.setEnabled(enbl);
				ppAddSubTask.setEnabled(enbl);
				//ppSubTasks.setEnabled(enbl); // default value to be over-written later depending on whether it has sub tasks
				ppCalcTask.setEnabled(enbl); // default value to be over-written later depending on whether it has sub tasks
				
				/*if (taskTable.getCurrentRootTask() == null) {
					ppParentTask.setEnabled(false);
				}
				else {
					ppParentTask.setEnabled(true);
				}XXX*/
				
                if (enbl) {   
    				String thisTaskId = taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString();
    				
    				boolean hasSubTasks = CurrentProject.getTaskList().hasSubTasks(thisTaskId);
    				//ppSubTasks.setEnabled(hasSubTasks);
    				ppCalcTask.setEnabled(hasSubTasks);
    				Task t = CurrentProject.getTaskList().getTask(thisTaskId);
                    parentPanel.getCalendar().jnCalendar.renderer.setTask(t);
                    parentPanel.getCalendar().jnCalendar.updateUI();
                }    
                else {
                    parentPanel.getCalendar().jnCalendar.renderer.setTask(null);
                    parentPanel.getCalendar().jnCalendar.updateUI();
                }
            }
        });
        editTaskB.setEnabled(false);
        removeTaskB.setEnabled(false);
		completeTaskB.setEnabled(false);
		ppAddSubTask.setEnabled(false);
		//ppSubTasks.setEnabled(false);
		//ppParentTask.setEnabled(false);
    taskPPMenu.add(ppEditTask);
    
    taskPPMenu.addSeparator();
    taskPPMenu.add(ppNewTask);
    taskPPMenu.add(ppAddSubTask);
    taskPPMenu.add(ppRemoveTask);
    
    taskPPMenu.addSeparator();
	taskPPMenu.add(ppCompleteTask);
	taskPPMenu.add(ppCalcTask);
	
    //taskPPMenu.addSeparator();
    
    //taskPPMenu.add(ppSubTasks);
    
    //taskPPMenu.addSeparator();
    //taskPPMenu.add(ppParentTask);
    
    taskPPMenu.addSeparator();
	taskPPMenu.add(ppShowActiveOnlyChB);

	
		// define key actions in TaskPanel:
		// - KEY:DELETE => delete tasks (recursivly).
		// - KEY:INTERT => insert new Subtask if another is selected.
		// - KEY:INSERT => insert new Task if nothing is selected.
		// - KEY:SPACE => finish Task.
		taskTable.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e){
				if(taskTable.getSelectedRows().length>0 
					&& e.getKeyCode()==KeyEvent.VK_DELETE)
					ppRemoveTask_actionPerformed(null);
				
				else if(e.getKeyCode()==KeyEvent.VK_INSERT) {
					if(taskTable.getSelectedRows().length>0) {
						ppAddSubTask_actionPerformed(null);
					}
					else {
						ppNewTask_actionPerformed(null);						
					}
				}
				
				else if(e.getKeyCode()==KeyEvent.VK_SPACE
						&& taskTable.getSelectedRows().length>0) {
					ppCompleteTask_actionPerformed(null);
				}
			}
			public void	keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){} 
		});	

    }

    static void editTaskB_actionPerformed(ActionEvent e) {
        Task t =
            CurrentProject.getTaskList().getTask(
                taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString());
        
        numberOfEditTaskPopUps++;
        NewTaskWindow ntw = null;
        
        if (numberOfEditTaskPopUps % 2 == 0) {
        	ntw = new NewTaskWindow(App.getFrame(), "Edit Task", t);
        }
        if(ntw == null){
        	ntw = new NewTaskWindow(App.getFrame(), "Edit Task", t);
        }

       /* TaskDialog dlg = new TaskDialog(App.getFrame(), Local.getString("Edit task"));
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.todoField.setText(t.getText());
        dlg.descriptionField.setText(t.getDescription());
        dlg.startDate.getModel().setValue(t.getStartDate().getDate());
        dlg.endDate.getModel().setValue(t.getEndDate().getDate());
        dlg.priorityCB.setSelectedIndex(t.getPriority());
        dlg.effortField.setText(Util.getHoursFromMillis(t.getEffort()));
	if((t.getStartDate().getDate()).after(t.getEndDate().getDate()))
		dlg.chkEndDate.setSelected(false);
	else
		dlg.chkEndDate.setSelected(true);
		dlg.progress.setValue(new Integer(t.getProgress()));
	dlg.chkEndDate_actionPerformed(null);*/	
        System.out.println("\n\n\n\n Start Date Year: ");

        ntw.setVisible(true);
        System.out.println("\n\n\n\n Start Date Year: ");
        if (ntw.CANCELLED)
            return;

        String startDateString = ntw.getStartDate().getText();
        //String[] startDateArray = startDateString.split("/");
      //  Date startDate = new Date(Integer.parseInt(startDateArray[2]) - 1900, Integer.parseInt(startDateArray[0]), Integer.parseInt(startDateArray[1]));

        String endDateString = ntw.getEndDate().getText();
   //     String[] endDateArray = endDateString.split("/");
      //  Date endDate = new Date(Integer.parseInt(endDateArray[2]) - 1900, Integer.parseInt(endDateArray[0]), Integer.parseInt(endDateArray[1]));

        System.out.println("\n\n\n\n Start Date Year: ");
        
        CalendarDate sd = null;
        CalendarDate ed = null;
		try {
			sd = new CalendarDate(new SimpleDateFormat("MM/dd/yyyy").parse(startDateString));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//ed = new CalendarDate(
//        CalendarDate ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
		try {
			ed = new CalendarDate(new SimpleDateFormat("MM/dd/yyyy").parse(endDateString));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 		/*if(ntw.getEndDate().isSelected())
 			ed = new CalendarDate((ntw.getEndDate().getText()));
 		else
 			ed = null;*/
        t.setStartDate(sd);
        t.setEndDate(ed);
        t.setText(ntw.getjTextFieldName().getText());
        t.setDescription(ntw.getTaskDesc().getText());
        t.setPriority(ntw.priorityCB.getSelectedIndex());
        //t.setEffort(Util.getMillisFromHours(ntw.getTotalHours().getText()));
        t.setProgress(((Integer)ntw.progress.getValue()).intValue());
        t.setEstLOC(Integer.parseInt(ntw.getLocEst().getText()));
        t.setActLOC(Integer.parseInt(ntw.getLocAct().getText()));
        t.setEstNumOfFiles(Integer.parseInt(ntw.getNumFiles().getText()));
        t.setActNumOfFiles(Integer.parseInt(ntw.getActualNumFiles().getText()));
        t.setHoursEst(Double.parseDouble(ntw.getHoursEst().getText()));
        t.setHoursAct(Double.parseDouble(ntw.getHoursAct().getText()));
        
        
//		CurrentProject.getTaskList().adjustParentTasks(t);

        CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
        taskTable.tableChanged();
        parentPanel.updateIndicators();
        //taskTable.updateUI();
    }

    static void newTaskB_actionPerformed(ActionEvent e) {
    	NewTaskWindow dlg;
    	numberOfNewTaskPopUps++;
    	
    	if (numberOfNewTaskPopUps % 2 == 0) {
    		dlg = new NewTaskWindow(App.getFrame(),"Create a New Task"); // Creates task creation window
    	} else {
    		return;
    	}
        
        //XXX String parentTaskId = taskTable.getCurrentRootTask();
        
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.width - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
        /*CalendarDate sd = new CalendarDate((Date) dlg.startDate.getModel().getValue());
//        CalendarDate ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
          CalendarDate ed;
 		if(dlg.chkEndDate.isSelected())
 			ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
 		else
 			ed = null;*/
        long effort = Util.getMillisFromHours(dlg.progress.getValue().toString());
		//XXX Task newTask = CurrentProject.getTaskList().createTask(sd, ed, dlg.todoField.getText(), dlg.priorityCB.getSelectedIndex(),effort, dlg.descriptionField.getText(),parentTaskId);
		Task newTask = CurrentProject.getTaskList().createTask(dlg.getStartDate().getText(),
				dlg.getEndDate().getText(),
				dlg.getjTextFieldName().getText(), 
				dlg.priorityCB.getSelectedIndex(),effort, dlg.getTaskDesc().getText(),null, dlg.getHoursEst().getText(),
				dlg.getLocEst().getText(), 
				dlg.getNumFiles().getText(), 
				dlg.getLocAct().getText(), 
				dlg.getHoursAct().getText(), 
				dlg.getActualNumFiles().getText());
		//actual - lines, time, files
//		CurrentProject.getTaskList().adjustParentTasks(newTask);
		newTask.setProgress(((Integer)dlg.progress.getValue()).intValue());
        CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
        taskTable.tableChanged();
        parentPanel.updateIndicators();
        taskTable.updateUI();
        System.out.println("new Task Create" + CurrentProject.getTaskList());
    }

    static void addSubTask_actionPerformed(ActionEvent e) {
        TaskDialog dlg = new TaskDialog(App.getFrame(), Local.getString("New Task"));
        String parentTaskId = taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString();
        
//        Util.debug("Adding sub task under " + parentTaskId);
        
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
		Task parent = CurrentProject.getTaskList().getTask(parentTaskId);
		CalendarDate todayD = CurrentDate.get();
		if (todayD.after(parent.getStartDate()))
			dlg.setStartDate(todayD);
		else
			dlg.setStartDate(parent.getStartDate());
		if (parent.getEndDate() != null) 
			dlg.setEndDate(parent.getEndDate());
		else 
			dlg.setEndDate(CurrentProject.get().getEndDate());
		dlg.setStartDateLimit(parent.getStartDate(), parent.getEndDate());
		dlg.setEndDateLimit(parent.getStartDate(), parent.getEndDate());
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
        CalendarDate sd = new CalendarDate((Date) dlg.startDate.getModel().getValue());
//        CalendarDate ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
          CalendarDate ed;
 		if(dlg.chkEndDate.isSelected())
 			ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
 		else
 			ed = null;
        long effort = Util.getMillisFromHours(dlg.effortField.getText());
		//Task newTask = CurrentProject.getTaskList().createTask(sd, ed, dlg.todoField.getText(), dlg.priorityCB.getSelectedIndex(),effort, dlg.descriptionField.getText(),parentTaskId);
        //newTask.setProgress(((Integer)dlg.progress.getValue()).intValue());
//		CurrentProject.getTaskList().adjustParentTasks(newTask);

		CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
        taskTable.tableChanged();
        parentPanel.updateIndicators();
        //taskTable.updateUI();
    }

    static void calcTask_actionPerformed(ActionEvent e) {
        TaskCalcDialog dlg = new TaskCalcDialog(App.getFrame());
        dlg.pack();
        Task t = CurrentProject.getTaskList().getTask(taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString());
        
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED) {
            return;            
        }
        
        TaskList tl = CurrentProject.getTaskList();
        if(dlg.calcEffortChB.isSelected()) {
            t.setEffort(tl.calculateTotalEffortFromSubTasks(t));
        }
        
        if(dlg.compactDatesChB.isSelected()) {
            t.setStartDate(tl.getEarliestStartDateFromSubTasks(t));
            t.setEndDate(tl.getLatestEndDateFromSubTasks(t));
        }
        
        if(dlg.calcCompletionChB.isSelected()) {
            long[] res = tl.calculateCompletionFromSubTasks(t);
            int thisProgress = (int) Math.round((((double)res[0] / (double)res[1]) * 100));
            t.setProgress(thisProgress);
        }
        
//        CalendarDate sd = new CalendarDate((Date) dlg.startDate.getModel().getValue());
////        CalendarDate ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
//          CalendarDate ed;
// 		if(dlg.chkEndDate.isSelected())
// 			ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
// 		else
// 			ed = new CalendarDate(0,0,0);
//        long effort = Util.getMillisFromHours(dlg.effortField.getText());
//		Task newTask = CurrentProject.getTaskList().createTask(sd, ed, dlg.todoField.getText(), dlg.priorityCB.getSelectedIndex(),effort, dlg.descriptionField.getText(),parentTaskId);
//		
		
        CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
        taskTable.tableChanged();
//        parentPanel.updateIndicators();
        //taskTable.updateUI();
    }

    static void listSubTasks_actionPerformed(ActionEvent e) {
        String parentTaskId = taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString();
        
        //XXX taskTable.setCurrentRootTask(parentTaskId); 
		taskTable.tableChanged();

//        parentPanel.updateIndicators();
//        //taskTable.updateUI();
    }

    static void parentTask_actionPerformed(ActionEvent e) {
//    	String taskId = taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString();
//      
//    	Task t = CurrentProject.getTaskList().getTask(taskId);
    	/*XXX Task t2 = CurrentProject.getTaskList().getTask(taskTable.getCurrentRootTask());
    	
    	String parentTaskId = t2.getParent();
    	if((parentTaskId == null) || (parentTaskId.equals(""))) {
    		parentTaskId = null;
    	}
    	taskTable.setCurrentRootTask(parentTaskId); 
    	taskTable.tableChanged();*/

//      parentPanel.updateIndicators();
//      //taskTable.updateUI();
  }

    static void removeTaskB_actionPerformed(ActionEvent e) {
        String msg;
        String thisTaskId = taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString();
        
        if (taskTable.getSelectedRows().length > 1)
            msg = Local.getString("Remove")+" "+taskTable.getSelectedRows().length +" "+Local.getString("tasks")+"?"
             + "\n"+Local.getString("Are you sure?");
        else {        	
        	Task t = CurrentProject.getTaskList().getTask(thisTaskId);
        	// check if there are subtasks
			if(CurrentProject.getTaskList().hasSubTasks(thisTaskId)) {
				msg = Local.getString("Remove task")+"\n'" + t.getText() + Local.getString("' and all subtasks") +"\n"+Local.getString("Are you sure?");
			}
			else {		            
				msg = Local.getString("Remove task")+"\n'" + t.getText() + "'\n"+Local.getString("Are you sure?");
			}
        }
        int n =
            JOptionPane.showConfirmDialog(
                App.getFrame(),
                msg,
                Local.getString("Remove task"),
                JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION)
            return;
        Vector toremove = new Vector();
        for (int i = 0; i < taskTable.getSelectedRows().length; i++) {
            Task t =
            CurrentProject.getTaskList().getTask(
                taskTable.getModel().getValueAt(taskTable.getSelectedRows()[i], TaskTable.TASK_ID).toString());
            if (t != null)
                toremove.add(t);
        }
        for (int i = 0; i < toremove.size(); i++) {
            CurrentProject.getTaskList().removeTask((Task)toremove.get(i));
        }
        taskTable.tableChanged();
        CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
        parentPanel.updateIndicators();
        //taskTable.updateUI();

    }

	static void ppCompleteTask_actionPerformed(ActionEvent e) {
		String msg;
		Vector tocomplete = new Vector();
		for (int i = 0; i < taskTable.getSelectedRows().length; i++) {
			Task t =
			CurrentProject.getTaskList().getTask(
				taskTable.getModel().getValueAt(taskTable.getSelectedRows()[i], TaskTable.TASK_ID).toString());
			if (t != null)
				tocomplete.add(t);
		}
		for (int i = 0; i < tocomplete.size(); i++) {
			Task t = (Task)tocomplete.get(i);
			t.setProgress(100);
		}
		taskTable.tableChanged();
		CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
		parentPanel.updateIndicators();
		//taskTable.updateUI();
	}

	// toggle "show active only"
	static void toggleShowActiveOnly_actionPerformed(ActionEvent e) {
		Context.put(
			"SHOW_ACTIVE_TASKS_ONLY",
			new Boolean(ppShowActiveOnlyChB.isSelected()));
		taskTable.tableChanged();
	}

    class PopupListener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
		if ((e.getClickCount() == 2) && (taskTable.getSelectedRow() > -1)){
			// ignore "tree" column
			//if(taskTable.getSelectedColumn() == 1) return;
			
			editTaskB_actionPerformed(null);
		}
        }

                public void mousePressed(MouseEvent e) {
                    maybeShowPopup(e);
                }

                public void mouseReleased(MouseEvent e) {
                    maybeShowPopup(e);
                }

                private void maybeShowPopup(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        taskPPMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }

    }

  static void ppEditTask_actionPerformed(ActionEvent e) {
    editTaskB_actionPerformed(e);
  }
  static void ppRemoveTask_actionPerformed(ActionEvent e) {
    removeTaskB_actionPerformed(e);
  }
  static void ppNewTask_actionPerformed(ActionEvent e) {
    newTaskB_actionPerformed(e);
  }

  static void ppAddSubTask_actionPerformed(ActionEvent e) {
  	addSubTask_actionPerformed(e);
  }

  static void ppListSubTasks_actionPerformed(ActionEvent e) {
  	listSubTasks_actionPerformed(e);
  }

  static void ppParentTask_actionPerformed(ActionEvent e) {
  	parentTask_actionPerformed(e);
  }

  static void ppCalcTask_actionPerformed(ActionEvent e) {
      calcTask_actionPerformed(e);
  }
@Override
public void update(Observable arg0, Object arg1) {
	taskTable.tableChanged();
    parentPanel.updateIndicators();
    taskTable.updateUI();
}

  
}