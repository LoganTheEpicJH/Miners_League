package com.minersleague.main.games.towerdefense.tower;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class TowerBreaker {

	public TowerBreaker(Location location) {
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockY();
		World w = location.getWorld();
		for(int i = 0; i<7; i++) {
			w.getBlockAt(x, y+i, z).setType(Material.AIR);
			w.getBlockAt(x-1, y+i, z+1).setType(Material.AIR);
			w.getBlockAt(x, y+i, z+1).setType(Material.AIR);
			w.getBlockAt(x+1, y+i, z+1).setType(Material.AIR);
			w.getBlockAt(x+1, y+i, z).setType(Material.AIR);
			w.getBlockAt(x+1, y+i, z-1).setType(Material.AIR);
			w.getBlockAt(x, y+i, z-1).setType(Material.AIR);
			w.getBlockAt(x-1, y+i, z-1).setType(Material.AIR);
			w.getBlockAt(x-1, y+i, z).setType(Material.AIR);
		}
	}
	
}
