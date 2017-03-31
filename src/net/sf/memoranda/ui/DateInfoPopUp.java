package net.sf.memoranda.ui; 
 
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.Local; 
 
public class DateInfoPopUp extends JDialog { 
  private static final long serialVersionUID = 1L; 
   
  private int WIDTH; 
  private int HEIGHT; 
 
  private JScrollPane scroller; 
  private JPanel mainPanel; 
  private static int numberOfCreations = 1; 
   
    String[] priority = {Local.getString("Lowest"), Local.getString("Low"), 
            Local.getString("Normal"), Local.getString("High"), 
            Local.getString("Highest")}; 
     
  public DateInfoPopUp(JFrame parentFrame, CalendarDate date, JLabel tableInformation) { 
    super(parentFrame,date.getShortDateString(), true); 
    numberOfCreations++; 
    if (!(numberOfCreations % 2 == 0)) { 
      this.dispose(); 
      return; 
    } 
    WIDTH = 690; 
    HEIGHT = 450; 
    createComponents(tableInformation); 
    editComponents(); 
    addComponents(); 
    this.setVisible(true);
  } 
 
  private void createComponents(JLabel tableInfo) { 
    mainPanel = new JPanel(); 
    scroller = new JScrollPane(tableInfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
  } 
   
  private void editComponents() { 
     
    setMaximumSize(new Dimension(WIDTH, HEIGHT)); 
    setMinimumSize(new Dimension(WIDTH, HEIGHT)); 
    setPreferredSize(new Dimension(WIDTH, HEIGHT)); 
 
    setLocation(WIDTH/2,HEIGHT/2); 
    setResizable(false);  
    setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
  }   
   
  private void addComponents(){
    mainPanel.add(scroller);
    this.add(mainPanel); 
  } 
} 

