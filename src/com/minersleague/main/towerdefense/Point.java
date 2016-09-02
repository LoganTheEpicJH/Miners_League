package com.minersleague.main.towerdefense;

import org.bukkit.Location;
import org.bukkit.entity.Villager;

public class Point {

	private int pointID;
	private Location location;
	private Villager villager;
	
	public Point(int pointID, Location loacation, Villager villager) {
		this.pointID = pointID;
		this.location = loacation;
		this.villager = villager;
	}
	
	public int getID() {
		return pointID;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public Villager getVillager() {
		return villager;
	}
	
}
