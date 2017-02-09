import javax.swing.*;
import java.util.*;
import java.text.*;

public class Timer implements Runnable{
	private Thread thread;
	private String threadName;
	private JLabel label;
	private long currentTime;
	private long startTime;
	private long deltaTime;
	private long totalTime;
	private boolean paused,stopped;
	SimpleDateFormat sdf;
	Date result;
	
	public Timer(JLabel label){
		threadName = "Task Timer";
		this.label = label;
		currentTime = 0;
		startTime = 0;
		deltaTime = currentTime - startTime;
		totalTime = 0;
		paused = false;
		stopped = false;

		sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		result = new Date(deltaTime);
	}
	
	public void run(){		
		startTime = System.currentTimeMillis();
		
		while(!stopped){

			synchronized(this){
	
				while(paused){
	
					try{
						wait();
						
						totalTime += deltaTime;
						
						currentTime = 0;
						
						deltaTime = 0;
						
						startTime = System.currentTimeMillis();
					}catch(Exception e){
						e.printStackTrace();
	
					}
	
				}
	
			}				
			
			currentTime = System.currentTimeMillis();
			
			deltaTime = currentTime - startTime;
			
			result.setTime(totalTime + deltaTime);

			label.setText(sdf.format(result));
			
		}

		totalTime += deltaTime;
		currentTime = 0;
		startTime = 0;
		deltaTime = 0;
		result.setTime(totalTime);
		label.setText(sdf.format(result));		
	}

	public void stop(){
		stopped = true;
		try{
			thread.join();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void pause(){
		paused = true;

	}

	synchronized void resume(){
		paused = false;
		notify();
	}	

	public void start(){
		thread = new Thread(this,threadName);
		thread.start();
	}

	public SimpleDateFormat getDateFormatter(){
		return sdf;
	}

}