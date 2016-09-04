package com.minersleague.main.towerdefense.mechanics;

import java.util.ArrayList;

import com.minersleague.main.towerdefense.AdvZombie;
import com.minersleague.main.towerdefense.Game;

public class Rounds {

	private int roundAt;
	//private final int maxRound = 5;
	public int lifes;
	public boolean won;
	private ArrayList<AdvZombie> zombies;
	
	public Rounds() {
		zombies = new ArrayList<AdvZombie>();
		won = false;
		roundAt = 1;
		lifes = 3;
	}
	
	public void nextRound(Game game) {
		zombies.add(new AdvZombie("Z-"+game.getName(), game.getStart()));
		zombies.add(new AdvZombie("Z-"+game.getName(), game.getStart()));
		zombies.add(new AdvZombie("Z-"+game.getName(), game.getStart()));
		zombies.add(new AdvZombie("Z-"+game.getName(), game.getStart()));
		zombies.add(new AdvZombie("Z-"+game.getName(), game.getStart()));
		zombies.add(new AdvZombie("Z-"+game.getName(), game.getStart()));
	}
	
	public ArrayList<AdvZombie> getZombies() {
		return zombies;
	}
	
	public int getRoundAt() {
		return roundAt;
	}
	
}
