package com.minersleague.main.towerdefense;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

public class AdvZombie {

	private Zombie zombie;
	
	public AdvZombie(String gameName, Location spawn) {
		zombie = (Zombie)spawn.getWorld().spawnEntity(spawn, EntityType.ZOMBIE);
		zombie.setCustomName(gameName);
		zombie.setCustomNameVisible(true);
		zombie.setSilent(true);
		zombie.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(128.0D);
	}
	
	public Zombie getSpawn() {
		return zombie;
	}
	
}
