package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.text.Document;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.Note;
import net.sf.memoranda.NoteList;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectListener;
import net.sf.memoranda.ResourcesList;
import net.sf.memoranda.Task;
import net.sf.memoranda.TaskList;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Local;

/*$Id: TaskSearchPanel.java,v 1.5 2017/02/07 10:05:44 trevor forrey Exp $*/
public class TaskSearchPanel extends JPanel {
	
    BorderLayout borderLayout1 = new BorderLayout();
    Border border1;
    TitledBorder titledBorder1;
    
    TasksList taskList = new TasksList(TasksList.EMPTY);
    JScrollPane scrollPane = new JScrollPane();
    
    JPanel searchInputPanel = new JPanel(new BorderLayout());
    JPanel searchSettingsAndButtonPanel = new JPanel(new BorderLayout());
    JPanel searchSettingComboBoxesPanel = new JPanel(new BorderLayout());
    JPanel searchFieldPanel = new JPanel(new BorderLayout());
    JPanel searchButtonsPanel = new JPanel(new GridLayout(1,2));
    JPanel buttonListPanel = new JPanel();
    
    JTextField searchField = new JTextField();
    
    JButton searchB = new JButton();
    JButton exitSearchButton = new JButton();
    JButton goToDateButton = new JButton();
    
    JCheckBox caseSensCB = new JCheckBox();
    JProgressBar progressBar = new JProgressBar();

    public TaskSearchPanel() {
        try {
            jbInit();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }
    
    void jbInit() throws Exception {
        border1 = BorderFactory.createEmptyBorder(2, 2, 2, 2);

        titledBorder1 = new TitledBorder(BorderFactory.createEmptyBorder(), Local.getString("Search") + ":");

        this.setLayout(borderLayout1);

        searchFieldPanel.setBorder(titledBorder1);
        
        buttonListPanel.setPreferredSize(new Dimension(1380, 80));
        buttonListPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        buttonListPanel.setVisible(true);
        
        titledBorder1.setTitleFont(new java.awt.Font("Dialog", 1, 11));
        searchField.setFont(new java.awt.Font("Dialog", 1, 10));
        
        caseSensCB.setText(Local.getString("Case sensitive"));
        caseSensCB.setFont(new java.awt.Font("Dialog", 1, 10));
        caseSensCB.setMargin(new Insets(0, 0, 0, 0));

        searchB.setEnabled(false);
        searchB.setFont(new java.awt.Font("Dialog", 1, 11));
        searchB.setMaximumSize(new Dimension(72, 25));
        searchB.setMinimumSize(new Dimension(2, 25));
        searchB.setPreferredSize(new Dimension(70, 25));
        searchB.setMargin(new Insets(0, 0, 0, 0));
        searchB.setText(Local.getString("Search"));
        
        goToDateButton.setEnabled(false);
        goToDateButton.setFont(new java.awt.Font("Dialog", 1, 11));
        goToDateButton.setMaximumSize(new Dimension(72, 25));
        goToDateButton.setMinimumSize(new Dimension(2, 25));
        goToDateButton.setPreferredSize(new Dimension(70, 25));
        goToDateButton.setMargin(new Insets(0, 0, 0, 0));
        goToDateButton.setText(Local.getString("Go to Date"));
        
        exitSearchButton.setEnabled(true);
        exitSearchButton.setFont(new java.awt.Font("Dialog", 1, 11));
        exitSearchButton.setMaximumSize(new Dimension(72, 25));
        exitSearchButton.setMinimumSize(new Dimension(2, 25));
        exitSearchButton.setPreferredSize(new Dimension(70, 25));
        exitSearchButton.setMargin(new Insets(0, 0, 0, 0));
        exitSearchButton.setText(Local.getString("Exit Search"));
        
        searchSettingComboBoxesPanel.add(caseSensCB, BorderLayout.SOUTH);
        
        searchSettingsAndButtonPanel.add(searchSettingComboBoxesPanel, BorderLayout.NORTH);
        searchSettingsAndButtonPanel.add(searchB, BorderLayout.SOUTH);
        
        searchFieldPanel.add(searchField, BorderLayout.CENTER);
        
        searchInputPanel.add(searchSettingsAndButtonPanel, BorderLayout.CENTER);
        searchInputPanel.add(searchFieldPanel, BorderLayout.NORTH);
                
        scrollPane.getViewport().add(buttonListPanel);
        
        searchButtonsPanel.add(goToDateButton);
        searchButtonsPanel.add(exitSearchButton);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(searchInputPanel, BorderLayout.NORTH);
        this.add(searchButtonsPanel, BorderLayout.SOUTH);
        
        // Listeners
        searchField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(CaretEvent e) {
                searchField_caretUpdate(e);
            }
        });
        
        scrollPane.addMouseListener(new MouseListener() {
        	public void mouseClicked(MouseEvent e) {
        		scrollPane_mouseClick(e);
        	}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
        });
        
        searchB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchB_actionPerformed(e);
            }
        });
        
        goToDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToDateButton_actionPerformed(e);
            }
        });
        
        exitSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitSearchButton_actionPerformed(e);
            }
        });
        
        CurrentProject.addProjectListener(new ProjectListener() {
            public void projectChange(Project p, NoteList nl, TaskList tl, ResourcesList rl) {
                taskList.update(new Vector());
            }
            public void projectWasChanged() {}
        });
        //notesList.update(new Vector());

    }

    Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);

    void searchB_actionPerformed(ActionEvent e) {
        Cursor cur = App.getFrame().getCursor();
        App.getFrame().setCursor(waitCursor);
        doSearch();
        App.getFrame().setCursor(cur);
    }
    
    void goToDateButton_actionPerformed(ActionEvent e) {
        // TODO go to date functionality
    }
    
    void exitSearchButton_actionPerformed(ActionEvent e) {
    	this.removeAll();
    	this.updateUI();
    }

    void searchField_caretUpdate(CaretEvent e) {
        searchB.setEnabled(searchField.getText().length() > 0);
    }
    
    void scrollPane_mouseClick(MouseEvent e) {
    	goToDateButton.setEnabled(true);
    }
    
    void doSearch() {
    	
        Pattern pattern;
        //this.add(progressBar, BorderLayout.SOUTH);
        int flags = Pattern.DOTALL;
        if (!caseSensCB.isSelected())
            flags = flags + Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE;
        String _find = searchField.getText();
        try {
            pattern = Pattern.compile(_find, flags);
        }
        catch (Exception ex) {
            new ExceptionDialog(ex, "Error in regular expression", "Check the regular expression syntax");
            return;
        }
        /*progressBar.setMinimum(0);
        progressBar.setStringPainted(true);*/
        Vector tasks = (Vector) CurrentProject.getTaskList().getTopLevelTasks();
        Vector<String> found = new Vector();
        /*progressBar.setMaximum(notes.size()-1);
        progressBar.setIndeterminate(false);
        this.add(progressBar, BorderLayout.SOUTH);*/
        for (int i = 0; i < tasks.size(); i++) {
            //progressBar.setValue(i);
            Task task = (Task) tasks.get(i);
//            Document doc = CurrentStorage.get().openTask(task);
            try {
                String txt = task.getDescription();
                CalendarDate dateDue = task.getEndDate();
                Matcher matcher = pattern.matcher(txt);
                if (matcher.find()) {
                	System.out.println("Found Task: " + txt);
                	String fullTaskInfo = txt + " - " + dateDue.getFullDateString();
                    found.add(fullTaskInfo);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //this.remove(progressBar);
        for (int buttonIndex = 0; buttonIndex < found.size(); buttonIndex++) {
        	TaskListButton foundTaskButton = new TaskListButton();
        	foundTaskButton.setButtonLabel(found.get(buttonIndex));
        	buttonListPanel.add(foundTaskButton);
        }
        JList graphicTaskList = new JList(found);
        //scrollPane.getViewport().add(buttonListPanel);
        this.updateUI();
    }

}