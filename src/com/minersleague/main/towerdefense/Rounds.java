package com.minersleague.main.towerdefense;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

public class Rounds {

	private int roundAt;
	private final int maxRound = 5;
	private int lifes;
	private boolean won;
	private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	
	public Rounds() {
		won = false;
		roundAt = 1;
		lifes = 3;
	}
	
	public void nextRound(Game game) {
		if(roundAt<=maxRound) {
			int zPerRound = 10*roundAt;
			for(int i = 1; i<zPerRound; i++) {
				zombies.add((Zombie)game.getStart().getStart().getWorld().spawnEntity(game.getStart().getStart(), EntityType.ZOMBIE));
			}
		} else {
			won = true;
		}
	}
	
	public ArrayList<Zombie> getZombies() {
		return zombies;
	}
	
	public int getRoundAt() {
		return roundAt;
	}
	
}
