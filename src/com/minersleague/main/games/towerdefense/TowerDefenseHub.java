package com.minersleague.main.games.towerdefense;

import org.bukkit.Location;

public class TowerDefenseHub {

	private Location location;
	
	public TowerDefenseHub(Location location) {
		this.location = location;
	}
	
	public Location getHubLocation() {
		return location;
	}
	
}
