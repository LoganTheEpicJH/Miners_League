package com.minersleague.main.games.towerdefense.tower.function;

import org.bukkit.Location;

public abstract class TowerFunction implements Runnable {

	public Location towerPos;
	public boolean repeating;
	public double radius;
	
	public TowerFunction() {}
	
	public abstract void start();
	
}
