package net.sf.memoranda.ui.develop;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JViewport;

public class DailyItemsPanelViewPort extends JViewport{
	public static final int EVENT_VIEW = 0;
	public static final int	TASK_VIEW = 1;
	public static final int RESOURCE_VIEW = 2;
	public static final int NOTES_VIEW = 3;
	private Image image;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DailyItemsPanelViewPort(int panelType){
		switch(panelType){
			case EVENT_VIEW:
				image = LoadAssets.TERMINAL_IMAGE.getImage();
				break;
			case TASK_VIEW:
				image = LoadAssets.TERMINAL_IMAGE.getImage();
				break;
			case RESOURCE_VIEW:
				image = LoadAssets.TERMINAL_IMAGE.getImage();
				break;
			case NOTES_VIEW:
				image = LoadAssets.HOMEPAGE_BACKGROUND.getImage();
				break;
			default:
			image = LoadAssets.HOMEPAGE_BACKGROUND.getImage();
			break;
		}
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
