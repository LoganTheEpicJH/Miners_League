package com.minersleague.main.games.codwarfare;

import org.bukkit.Location;

public class Area {

	public Location[] spawns;
	public Location lobby;
	
	public Area(Location[] spawns, Location lobby) {
		this.spawns = spawns;
		this.lobby = lobby;
	}
	
}
