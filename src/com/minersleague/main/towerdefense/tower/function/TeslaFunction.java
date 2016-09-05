package com.minersleague.main.towerdefense.tower.function;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;

public class TeslaFunction extends TowerFunction {

	boolean found;
	
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

	@Override
	public void start() {}

}
