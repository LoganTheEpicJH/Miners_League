package com.minersleague.main.towerdefense;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

public class Point {

	private int pointID;
	private Location location;
	
	public Point(int pointID, Location location) {
		this.pointID = pointID;
		this.location = new Location(location.getWorld(), location.getBlockX()+0.5D, location.getBlockY()+0.1D, location.getBlockZ()+0.5D);
		Villager villager = (Villager)this.location.getWorld().spawnEntity(this.location, EntityType.VILLAGER);
		villager.setProfession(Profession.FARMER);
		villager.setAdult();
		villager.setCustomName("Point "+this.pointID);
		villager.setCustomNameVisible(true);
		villager.setSilent(true);
		villager.setRemoveWhenFarAway(false);
		villager.setAI(false);
	}
	
	public int getID() {
		return pointID;
	}
	
	public Location getLocation() {
		return location;
	}
	
}
