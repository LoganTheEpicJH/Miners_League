package com.minersleague.main.games.towerdefense.tower;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import com.minersleague.main.games.generall.SimpleThread;

public class TowerBreaker extends SimpleThread {

	int levelAt;
	int maxLevel = 7;
	boolean running;
	Location location;
	TowerBreaker tb;

	public TowerBreaker(Location location) {
		this.location = location;
		levelAt = 0;
		running = true;
		tb = this;
		executeThread(tb);
	}

	@Override
	public void run() {
		while(running) {
			levelAt++;
			if(levelAt<=maxLevel) {
				int x = location.getBlockX();
				int y = location.getBlockY()+levelAt;
				int z = location.getBlockY();
				World w = location.getWorld();
				w.getBlockAt(x, y, z).setType(Material.AIR);
				try {
					Thread.sleep(10);
				} catch(InterruptedException e) {}
				w.getBlockAt(x-1, y, z+1).setType(Material.AIR);
				try {
					Thread.sleep(10);
				} catch(InterruptedException e) {}
				w.getBlockAt(x, y, z+1).setType(Material.AIR);
				try {
					Thread.sleep(10);
				} catch(InterruptedException e) {}
				w.getBlockAt(x+1, y, z+1).setType(Material.AIR);
				try {
					Thread.sleep(10);
				} catch(InterruptedException e) {}
				w.getBlockAt(x+1, y, z).setType(Material.AIR);
				try {
					Thread.sleep(10);
				} catch(InterruptedException e) {}
				w.getBlockAt(x+1, y, z-1).setType(Material.AIR);
				try {
					Thread.sleep(10);
				} catch(InterruptedException e) {}
				w.getBlockAt(x, y, z-1).setType(Material.AIR);
				try {
					Thread.sleep(10);
				} catch(InterruptedException e) {}
				w.getBlockAt(x-1, y, z-1).setType(Material.AIR);
				try {
					Thread.sleep(10);
				} catch(InterruptedException e) {}
				w.getBlockAt(x-1, y, z).setType(Material.AIR);
				try {
					Thread.sleep(10);
				} catch(InterruptedException e) {}
			} else {
				running = false;
				cancelThread();
			}
		}
	}

}
