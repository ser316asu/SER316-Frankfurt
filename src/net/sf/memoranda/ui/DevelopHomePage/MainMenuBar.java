/**************************************************************
 * Copyright (c) 2017 - 2017, Joshua Becker, All rights reserved
 * SER316-Frankfurt
 * Description:
 */
package net.sf.memoranda.ui.DevelopHomePage;
import javax.swing.*;
// TODO: Auto-generated Javadoc

/**
 * The Class MainMenuBar.
 */
public class MainMenuBar extends JMenuBar implements Styling
{
	
	/** The help. */
	private JMenu file,edit,insert,format,go,help;
	
    /**
     * Instantiates a new main menu bar.
     *
     * @param parent the parent
     */
    public MainMenuBar(JFrame parent)
    {
        buildComponents();
        buildFileMenu();
        buildEditMenu();
        buildInsertMenu();
        buildFormatMenu();
        buildHelpMenu();
        addMenus();
    }
    
    /**
     * Builds the components.
     */
    public void buildComponents(){
    	file = new JMenu("File");
    	edit = new JMenu("Edit");
    	insert = new JMenu("Insert");
    	format = new JMenu("Format");
    	go = new JMenu("Go");
    	help = new JMenu("Help");
    }
    
    /**
     * Builds the file menu.
     */
    public void buildFileMenu(){
    	JMenuItem newProject = new JMenuItem("New Project");
    	JMenuItem newTask = new JMenuItem("New Task");
    	JMenuItem packProject = new JMenuItem("Pack Project");
    	JMenuItem unpackProject = new JMenuItem("Unpack Project");
    	JMenuItem exportNotes = new JMenuItem("Export Notes");
    	JMenuItem importOneNote = new JMenuItem("Import One Note");
    	JMenuItem importMultipleNotes = new JMenuItem("Import Multiple Notes");
    	JMenuItem preferences = new JMenuItem("Preferences");
    	JMenuItem closeWindow = new JMenuItem("Close Window");
    	JMenuItem exit = new JMenuItem("Exit");
    	
    	file.add(newTask);
    	file.add(newProject);
    	file.add(packProject);
    	file.add(unpackProject);
    	file.add(exportNotes);
    	file.add(importOneNote);
    	file.add(importMultipleNotes);
    	file.add(preferences);
    	file.add(closeWindow);
    	file.add(exit);
    }
    
    /**
     * Builds the edit menu.
     */
    public void buildEditMenu(){}
    
    /**
     * Builds the insert menu.
     */
    public void buildInsertMenu(){}
    
    /**
     * Builds the format menu.
     */
    public void buildFormatMenu(){}
    
    /**
     * Builds the go menu.
     */
    public void buildGoMenu(){}
    
    /**
     * Builds the help menu.
     */
    public void buildHelpMenu(){
    	JMenuItem onlineUserGuide = new JMenuItem("Online User's Guide");
    	JMenuItem website = new JMenuItem("Memoranda Website");
    	JMenuItem reportBug = new JMenuItem("Report a bug");
    	JMenuItem aboutMemoranda = new JMenuItem("About Memoranda");
    	
    	help.add(onlineUserGuide);
    	help.add(website);
    	help.add(reportBug);
    	help.add(aboutMemoranda);
    }
    
    /**
     * Adds the menus.
     */
    public void addMenus(){
    	this.add(file);
    	this.add(edit);
    	this.add(insert);
    	this.add(format);
    	this.add(go);
    	this.add(help);
    }
    
    /**
     * Edits the components.
     */
    public void editComponents(){
    	
    }
    
    /* (non-Javadoc)
     * @see net.sf.memoranda.ui.DevelopHomePage.Styling#style()
     */
    public void style(){}
}