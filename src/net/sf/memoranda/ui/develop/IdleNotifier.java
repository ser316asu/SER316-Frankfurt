package net.sf.memoranda.ui.develop;

public class IdleNotifier implements Runnable{
	private static final IdleNotifier INSTANCE = new IdleNotifier();
	private IdleThread thread;
	private IdleNotifier(){
		thread = new IdleThread(IdleNotifier.getInstance());
		thread.start();
	}
	
	public static IdleNotifier getInstance(){
		return IdleNotifier.INSTANCE;
	}
	public static void resetTimer(){
		IdleNotifier.getInstance().getThread().resetTimer();
	}
	@Override
	public void run() {
		System.out.println("running");
		while(true){
			try {
				this.wait(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("thread");
		}
		
	}
	
	private class IdleThread extends Thread {
		public IdleThread(Runnable runnable) {
			super(runnable);
		}

		public void resetTimer(){
			this.interrupt();
			this.start();
		}
		
		public void start(){
			this.run();
			super.start();
		}
	}
	
	public IdleThread getThread(){
		return thread;
	}
}
