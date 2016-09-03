package com.minersleague.main.towerdefense;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

import com.minersleague.main.Main;

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
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				@Override
				public void run() {
					zombies.add((Zombie)game.getStart().getLocation().getWorld().spawnEntity(game.getStart().getLocation(), EntityType.ZOMBIE));
				}
			}, 10);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				@Override
				public void run() {
					zombies.add((Zombie)game.getStart().getLocation().getWorld().spawnEntity(game.getStart().getLocation(), EntityType.ZOMBIE));
				}
			}, 30);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				@Override
				public void run() {
					zombies.add((Zombie)game.getStart().getLocation().getWorld().spawnEntity(game.getStart().getLocation(), EntityType.ZOMBIE));
				}
			}, 50);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				@Override
				public void run() {
					zombies.add((Zombie)game.getStart().getLocation().getWorld().spawnEntity(game.getStart().getLocation(), EntityType.ZOMBIE));
				}
			}, 70);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				@Override
				public void run() {
					zombies.add((Zombie)game.getStart().getLocation().getWorld().spawnEntity(game.getStart().getLocation(), EntityType.ZOMBIE));
				}
			}, 90);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				@Override
				public void run() {
					zombies.add((Zombie)game.getStart().getLocation().getWorld().spawnEntity(game.getStart().getLocation(), EntityType.ZOMBIE));
				}
			}, 110);
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
