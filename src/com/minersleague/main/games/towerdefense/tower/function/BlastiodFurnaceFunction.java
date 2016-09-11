package com.minersleague.main.games.towerdefense.tower.function;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;

public class BlastiodFurnaceFunction implements TowerFunction {
	
	BlastiodFurnaceFunction bff;
	public Location towerPos;
	public boolean repeating;
	public double radius;
	
	private void start() {
		towerPos.add(0, 2.5, 0);
		towerPos.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, towerPos, 10);
		for(Entity entity : towerPos.getWorld().getNearbyEntities(towerPos, radius, 64, radius)) {
			if(entity instanceof Zombie) {
				((Zombie)entity).damage(10.0D);
				towerPos.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, entity.getLocation().add(0, 1.5, 0), 5);
			}
		}
	}
	
	public void init(Location location, double radius) {
		this.towerPos = location;
		this.radius = radius;
	}

	@Override
	public void run() {
		start();
	}

}
