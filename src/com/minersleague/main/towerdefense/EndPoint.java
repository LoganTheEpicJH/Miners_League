package com.minersleague.main.towerdefense;

import org.bukkit.Location;

public class EndPoint {

	private Location location;
	
	public EndPoint(Location location) {
		this.location = location;
	}
	
	public Location getEnd() {
		return location;
	}
	
}
