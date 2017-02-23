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

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

// TODO: Auto-generated Javadoc
/**
 * The Class ProgressCircleUI.
 */
public class ProgressCircleUI extends BasicProgressBarUI {
	  
  	/* (non-Javadoc)
  	 * @see javax.swing.plaf.basic.BasicProgressBarUI#getPreferredSize(javax.swing.JComponent)
  	 */
  	@Override public Dimension getPreferredSize(JComponent c) {
	    Dimension d = super.getPreferredSize(c);
	    int v = Math.max(d.width, d.height);
	    d.setSize(v, v);
	    return d;
	  }
	  
  	/* (non-Javadoc)
  	 * @see javax.swing.plaf.basic.BasicProgressBarUI#paint(java.awt.Graphics, javax.swing.JComponent)
  	 */
  	public void paint(Graphics g, JComponent c) {
	    Insets b = progressBar.getInsets(); // area for border
	    int barRectWidth  = progressBar.getWidth()  - b.right - b.left;
	    int barRectHeight = progressBar.getHeight() - b.top - b.bottom;
	    if (barRectWidth <= 0 || barRectHeight <= 0) {
	      return;
	    }

	    // draw the cells
	    Graphics2D g2 = (Graphics2D) g.create();
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                        RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setPaint(progressBar.getForeground());
	    double degree = 360 * progressBar.getPercentComplete();
	    double sz = Math.min(barRectWidth, barRectHeight);
	    double cx = b.left + barRectWidth  * .5;
	    double cy = b.top  + barRectHeight * .5;
	    double or = sz * .5;
	    double ir = or * .5; //or - 20;
	    Shape inner = new Ellipse2D.Double(cx - ir, cy - ir, ir * 2, ir * 2);
	    Shape outer = new Arc2D.Double(
	        cx - or, cy - or, sz, sz, 90 - degree, degree, Arc2D.PIE);
	    Area area = new Area(outer);
	    area.subtract(new Area(inner));
	    g2.fill(area);
	    g2.dispose();

	    // Deal with possible text painting
	    if (progressBar.isStringPainted()) {
	      paintString(g, b.left, b.top, barRectWidth, barRectHeight, 0, b);
	    }
	  }
	  
  	/**
	   * Make UI.
	   *
	   * @param color the color
	   * @return the j progress bar
	   */
  	public static JProgressBar makeUI(Color color) {
		    JProgressBar progress = new JProgressBar();
		    // use JProgressBar#setUI(...) method
		    progress.setUI(new ProgressCircleUI());
		    progress.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		    progress.setStringPainted(true);
		    progress.setFont(progress.getFont().deriveFont(24f));
		    progress.setForeground(color);
		    progress.setBackground(new Color(255,255,255,255));
		    progress.setOpaque(false);

		    /*
		    (new Timer(50, e -> {
		        int iv = Math.min(100, progress.getValue() + 1);
		        progress.setValue(iv);
		      })).start();*/

		    //progress.setBorder(BorderFactory.createLineBorder(Color.red));
		    return progress;
		  }
	}
