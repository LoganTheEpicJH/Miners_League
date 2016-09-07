package com.minersleague.main.games.codwarfare;

import java.util.ArrayList;

import com.minersleague.main.games.generall.SimpleThread;

public class CODGame extends SimpleThread {

	public ArrayList<String> playersPlaying;
	public boolean running;
	
	public CODGame() {
		playersPlaying = new ArrayList<String>();
		running = true;
		executeThread(this);
	}
	
	@Override
	public void run() {
		while(running) {
			try {
				Thread.sleep(20);
			} catch(InterruptedException e) {}
		}
	}

}
