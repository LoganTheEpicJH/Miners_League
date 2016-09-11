package com.minersleague.main.games.towerdefense.mechanics;

import com.minersleague.main.games.generall.SimpleThread;
import com.minersleague.main.games.generall.util.TDUtils;
import com.minersleague.main.games.towerdefense.TDGame;

public class TDLobby extends SimpleThread {
	
	public TDGame game;
	public int countdown;
	public TDLobby curr;
	public int playersIn;
	public boolean done;
	
	public TDLobby(TDGame game) {
		this.game = game;
		countdown = 600;
		curr = this;
		done = false;
		TDUtils.idLink.put(setID(game.getName()+"-Lobby"), curr);
		executeThread(curr);
	}

	public void start() {
		cancelThread();
		done = true;
	}
	
	@Override
	public void run() {
		while(isAlive()) {
			if(game.getPlayersPlaying().size()<=0) {
				countdown = 600;
				playersIn = 0;
			}
			if(game.getPlayersPlaying().size()>0) {
				if(countdown==600) {
					countdown=60;
					playersIn = 1;
				}
				if(playersIn>1) {
					countdown=31;
					playersIn=1;
				}
				countdown--;
			}
			if(game.getPlayersPlaying().size()>1) {
				if(countdown>31) {
					countdown=31;
					playersIn = 2;
				}
				countdown--;
			}
			if(countdown==0) {
				start();
			}
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {}
		}
	}
	
	
}
