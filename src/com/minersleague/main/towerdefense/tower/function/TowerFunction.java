package com.minersleague.main.towerdefense.tower.function;

import org.bukkit.Location;

public abstract class TowerFunction implements Runnable {

	public Location towerPos;
	public boolean repeating;
	public int activeAtStage;
	public Thread thread;
	
	public TowerFunction() {}
	
	public TowerFunction(Location towerPos) {
		this.towerPos = towerPos;
	}
	
	public abstract void start();
	
}
