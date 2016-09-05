package com.minersleague.main.towerdefense;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

public class AdvZombie {

	private Zombie zombie;
	
	public AdvZombie(String gameName, Location spawn) {
		Zombie zombie1 = (Zombie)spawn.getWorld().spawnEntity(spawn, EntityType.ZOMBIE);
		zombie1.setBaby(false);
		zombie1.setCustomName(gameName);
		zombie1.setCustomNameVisible(true);
		zombie1.setSilent(true);
		zombie1.setCanPickupItems(false);
		zombie1.getEquipment().setItemInMainHand(null);
		zombie1.getEquipment().setItemInOffHand(null);
		zombie1.getEquipment().setHelmet(null);
		zombie1.getEquipment().setChestplate(null);
		zombie1.getEquipment().setLeggings(null);
		zombie1.getEquipment().setBoots(null);
		zombie1.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(128.0D);
		this.zombie = zombie1;
	}
	
	public Zombie getSpawn() {
		return zombie;
	}
	
}
