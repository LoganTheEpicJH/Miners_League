package com.minersleague.main.towerdefense.mechanics;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

import com.minersleague.main.towerdefense.Game;

public class Rounds {

	private int roundAt;
	private final int maxRound = 5;
	public int lifes;
	public boolean won;
	private ArrayList<Zombie> zombies;
	
	public Rounds() {
		zombies = new ArrayList<Zombie>();
		won = false;
		roundAt = 1;
		lifes = 3;
	}
	
	public void nextRound(Game game) {
		if(roundAt<=maxRound) {
			//int zPerRound = 1;//10*roundAt;
			zombies.add((Zombie)game.getStart().getWorld().spawnEntity(game.getStart(), EntityType.ZOMBIE));
			zombies.add((Zombie)game.getStart().getWorld().spawnEntity(game.getStart(), EntityType.ZOMBIE));
			zombies.add((Zombie)game.getStart().getWorld().spawnEntity(game.getStart(), EntityType.ZOMBIE));
			zombies.add((Zombie)game.getStart().getWorld().spawnEntity(game.getStart(), EntityType.ZOMBIE));
			zombies.add((Zombie)game.getStart().getWorld().spawnEntity(game.getStart(), EntityType.ZOMBIE));
			zombies.add((Zombie)game.getStart().getWorld().spawnEntity(game.getStart(), EntityType.ZOMBIE));
//			for(int i = 1; i<zPerRound; i++) {
//				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
//					@Override
//					public void run() {
//						zombies.add((Zombie)game.getStart().getLocation().getWorld().spawnEntity(game.getStart().getLocation(), EntityType.ZOMBIE));
//					}
//				}, 10);
//			}
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
