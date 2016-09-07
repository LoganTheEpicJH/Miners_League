package com.minersleague.main.games.generall;

public abstract class SimpleThread extends IDAble implements Runnable {

	private Thread thread;
	public boolean running;
	
	public void executeThread(Runnable runnable) {
		thread = new Thread(runnable);
		running = true;
		thread.start();
	}
	
	public void cancelThread() {
		running = false;
		if(thread.isAlive()) {
			thread.interrupt();
		}
	}
	
}
