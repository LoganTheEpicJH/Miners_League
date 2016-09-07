package com.minersleague.main.games.towerdefense.tower.function;

import org.bukkit.Location;

import com.minersleague.main.games.generall.SimpleThread;

public abstract class TowerFunction extends SimpleThread {

	public Location towerPos;
	public boolean repeating;
	public double radius;
	
	public TowerFunction() {}
	
	public abstract void start();
	
}
