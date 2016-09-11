package com.minersleague.main.games.towerdefense.tower;

import org.bukkit.Location;
import org.bukkit.Material;

public class TowerBreaker {

	Tower tower;
	Location location;

	public TowerBreaker(Tower tower, Location location) {
		this.tower = tower;
		this.location = location;
		run();
	}

	public void run() {
		for(TowerBlock tb : tower.getBlocks()) {
			location.getWorld().getBlockAt(location.clone().add(tb.x, tb.y, tb.z)).setType(Material.AIR);
			location.getWorld().getBlockAt(location.clone().add(tb.x, tb.y, tb.z)).getState().update();
		}
	}

}
