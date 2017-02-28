/**************************************************************
 * Copyright (c) 2017 - 2017, Joshua Becker, Alec Shinn,
 * All rights reserved
 * SER316-Frankfurt is a project for ser216, 
 * using agile scrum.
 * Description:
 * 
 * Contact: jdbecke3@asu.edu, atshinn@asu.edu
 **************************************************************/
package net.sf.memoranda.ui.develop;

import javax.swing.*;

import net.sf.memoranda.EventsScheduler;
import net.sf.memoranda.ui.App;
import net.sf.memoranda.ui.ExceptionDialog;
import net.sf.memoranda.util.Configuration;

import java.awt.*;
import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class Develop.
 */
public class Develop extends JFrame {
	
	/**
	 * auto gen serialversion UID.
	 */
	private static final long serialVersionUID = 5124674529848101573L;
	/** The splash. */
	private JFrame splash;
    
    /**
     * Instantiates a new develop.
     *
     * @param fullWindowMode the fullWindowMode
     */
    public Develop(boolean fullWindowMode) {
    	super();
		if (fullWindowMode) {
			fullWindowMode = !Configuration.get("START_MINIMIZED").equals("yes");
		}
		/* DEBUG */
		if (!fullWindowMode){
			System.out.println("Minimized mode");
		}
		if (!Configuration.get("SHOW_SPLASH").equals("no")) {
			showSplash();
		}
		try {
			if (Configuration.get("LOOK_AND_FEEL").equals("system")) {
				UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
			} else if (Configuration.get("LOOK_AND_FEEL").equals("default")) {
				UIManager.setLookAndFeel(
					UIManager.
					getCrossPlatformLookAndFeelClassName());		
			} else if (
				Configuration.get("LOOK_AND_FEEL").toString().length() > 0) {
				UIManager.setLookAndFeel(
					Configuration.get("LOOK_AND_FEEL").toString());
			}

		} catch (Exception e) {		    
			new ExceptionDialog(e, "Error when initializing a "
					+ "pluggable look-and-feel. Default LF will be used.",
					"Make sure that specified look-and-feel library"
					+ " classes are on the CLASSPATH.");
		}
		if (Configuration.get("FIRST_DAY_OF_WEEK").equals("")) {
			String fdow;
			if (Calendar.getInstance().getFirstDayOfWeek() == 2) {
				fdow = "mon";
			} else {
				fdow = "sun";
			}
			Configuration.put("FIRST_DAY_OF_WEEK", fdow);
			Configuration.saveConfig();
			/* DEBUG */
			System.out.println("[DEBUG] first day of week is set to " + fdow);
		}

		EventsScheduler.init();
		if (fullWindowMode) {
			this.init();
		}
		if (!Configuration.get("SHOW_SPLASH").equals("no")) {
			splash.dispose();
		}
    }
    
    /**
     * sets up the frame and uses the splash while loading.
     */
    private void init() {
		/*
		 * if (packFrame) { frame.pack(); } else { frame.validate(); }
		 * 
		 * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		 * 
		 * Dimension frameSize = frame.getSize(); if (frameSize.height >
		 * screenSize.height) { frameSize.height = screenSize.height; } if
		 * (frameSize.width > screenSize.width) { frameSize.width =
		 * screenSize.width; }
		 * 
		 * 
		 * Make the window fullscreen - On Request of users This seems not to
		 * work on sun's version 1.4.1_01 Works great with 1.4.2 !!! So update
		 * your J2RE or J2SDK.
		 */
		/* Used to maximize the screen if the JVM Version if 1.4 or higher */
		/* --------------------------------------------------------------- */
		double jvmVer = Double.valueOf(
				System.getProperty("java.version").substring(0, 3)).doubleValue();

		HomePanel hp = new HomePanel();
		hp.setPreferredSize(new Dimension(Styling.SCREEN_WIDTH,Styling.SCREEN_HEIGHT));
		this.add(hp);
		this.pack();
		if (jvmVer >= 1.4) {
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
		} else {
			this.setExtendedState(Frame.NORMAL);
		}
		/* --------------------------------------------------------------- */
		/* Added By Jeremy Whitlock (jcscoobyrs) 07-Nov-2003 at 15:54:24 */

		// Not needed ???
		this.setVisible(true);
		this.toFront();
		this.requestFocus();

	}
    //taken from App.java
	/**
     * Show splash.
     */
	private void showSplash() {
		splash = new JFrame();
		ImageIcon spl =
			new ImageIcon(App.class.getResource("resources/splash.png"));
		JLabel label = new JLabel();
		label.setSize(400, 300);
		label.setIcon(spl);
		splash.getContentPane().add(label);
		splash.setSize(400, 300);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		splash.setLocation(
			(screenSize.width - 400) / 2,
			(screenSize.height - 300) / 2);
		splash.setUndecorated(true);
		splash.setVisible(true);
	}
}