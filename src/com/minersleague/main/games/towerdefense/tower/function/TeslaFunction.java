package com.minersleague.main.games.towerdefense.tower.function;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;

import com.minersleague.main.games.generall.SimpleThread;

public class TeslaFunction extends SimpleThread {

	boolean found;
	public Location towerPos;
	public boolean repeating;
	public double radius;
	
	@Override
	public void run() {
		while(repeating) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e1) {}
			found = false;
			Zombie entity = null;
			for(int i = 0; i<radius; i++) {
				if(towerPos.getWorld().getNearbyEntities(towerPos, radius, 64, radius)!=null) {
					if(!towerPos.getWorld().getNearbyEntities(towerPos, radius, 64, radius).isEmpty()) {
						for(Entity e : towerPos.getWorld().getNearbyEntities(towerPos, radius, 64, radius)) {
							if(e instanceof Zombie) {
								entity = (Zombie)e;
								entity.damage(2.5D);
								found = true;
								break;
							}
						}
						if(found) {
							//System.out.println("Found; Loop Broken");
							break;
						}
					}
				}
			}
		}
	}
	
	public void start() {
		executeThread(this);
	}
	
	public void init(Location location, double radius) {
		this.towerPos = location;
		this.radius = radius;
		repeating = true;
	}
	
	public void stop() {
		repeating = false;
		cancelThread();
	}

}
