import javax.swing.*;
import java.util.*;
import java.text.*;

public class Timer implements Runnable{
	private Thread thread;
	private String threadName;
	private JLabel label;
	private long currentTimeMs;
	private long previousTimeMs;
	private long deltaTime;

	

	public Timer(JLabel label){
		threadName = "Task Timer";
		this.label = label;
		currentTimeMs = 0;
		previousTimeMs = 0;
		deltaTime = currentTimeMs - previousTimeMs;

	}
	public void run(){
		
		synchronized(this){
			previousTimeMs = System.currentTimeMillis();
			int i = 0;
			while(true){
				currentTimeMs = System.currentTimeMillis();
				
				deltaTime = currentTimeMs - previousTimeMs;
				if(i == 0){
					System.out.println(deltaTime);
					i++;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

				sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

				Date result = new Date(deltaTime);

				label.setText(sdf.format(result));
			}
		}
	}

	public void start(){
		thread = new Thread(this,threadName);
		thread.start();
	}

}