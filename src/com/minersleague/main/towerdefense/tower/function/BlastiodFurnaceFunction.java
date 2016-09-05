package com.minersleague.main.towerdefense.tower.function;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;

public class BlastiodFurnaceFunction extends TowerFunction {
	
	public int radius;
	BlastiodFurnaceFunction bff;
	
	public BlastiodFurnaceFunction() {}
	
	public BlastiodFurnaceFunction(int radius) {
		this.radius = radius;
		bff = this;
	}

	@Override
	public void run() {}

	@Override
	public void start() {
		towerPos.add(0, 2.5, 0);
		towerPos.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, towerPos, 10);
		for(Entity entity : towerPos.getWorld().getNearbyEntities(towerPos, radius, radius, radius)) {
			if(entity instanceof Zombie) {
				//((Zombie)entity).setHealth(((Zombie)entity).getHealth()-10.0D);
				((Zombie)entity).damage(10.0D);
				towerPos.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, entity.getLocation().add(0, 1.5, 0), 5);
			}
		}
		towerPos.subtract(0, 2.5, 0);
	}

}