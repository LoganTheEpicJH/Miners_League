package com.minersleague.main.towerdefense.mechanics;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Zombie;

public class GameLogic_2 implements Runnable {

	private Zombie zombie;
	private Location target;
	public boolean run;
	
	public GameLogic_2(Location zombieSpawn, Location target) {
		zombie = (Zombie)zombieSpawn.getWorld().spawnEntity(zombieSpawn, EntityType.ZOMBIE);
		zombie.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(100D);
		this.target = target;
		Villager v = (Villager)target.getWorld().spawnEntity(new Location(target.getWorld(), target.getBlockX()+0.5D, target.getBlockY()+0.1D, target.getBlockZ()+0.5D), EntityType.VILLAGER);
		v.setCustomName("End");
		v.setCustomNameVisible(true);
		v.setAdult();
		v.setSilent(true);
		v.setProfession(Profession.FARMER);
		v.setAI(false);
		run = true;
	}
	
	@Override
	public void run() {
		while(run) {
			for(Entity entity : target.getWorld().getNearbyEntities(target, 1D, 2D, 1D)) {
				if(entity instanceof Villager) {
					Villager villager = (Villager)entity;
					if(villager.getCustomName().equalsIgnoreCase("End")) {
						zombie.setTarget(villager);
						break;
					}
				}
			}
			try {
				Thread.sleep(20);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
