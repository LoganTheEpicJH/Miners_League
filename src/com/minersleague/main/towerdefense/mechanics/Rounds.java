package com.minersleague.main.towerdefense.mechanics;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import com.minersleague.main.towerdefense.Game;

public class Rounds {

	private int roundAt;
	private final int maxRound = 5;
	public int lifes;
	public boolean won;
	private ArrayList<Zombie> zombies;
	
	public Rounds() {
		zombies = new ArrayList<Zombie>();
		won = false;
		roundAt = 1;
		lifes = 3;
	}
	
	public void nextRound(Game game) {
		if(roundAt<=maxRound) {
			{
				Zombie zombie1 = (Zombie)game.getPlayground().getWorld().spawnEntity(game.getStart(), EntityType.ZOMBIE);
				zombie1.setCustomName("Z-"+game.getName());
				zombie1.setCustomNameVisible(true);
				zombie1.setSilent(true);
				zombie1.setBaby(false);
				zombie1.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
				zombie1.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
				zombie1.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(128.0D);
				zombies.add(zombie1);
			}
			{
				Zombie zombie1 = (Zombie)game.getPlayground().getWorld().spawnEntity(game.getStart(), EntityType.ZOMBIE);
				zombie1.setCustomName("Z-"+game.getName());
				zombie1.setCustomNameVisible(true);
				zombie1.setSilent(true);
				zombie1.setBaby(false);
				zombie1.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
				zombie1.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
				zombie1.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(128.0D);
				zombies.add(zombie1);
			}
			{
				Zombie zombie1 = (Zombie)game.getPlayground().getWorld().spawnEntity(game.getStart(), EntityType.ZOMBIE);
				zombie1.setCustomName("Z-"+game.getName());
				zombie1.setCustomNameVisible(true);
				zombie1.setSilent(true);
				zombie1.setBaby(false);
				zombie1.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
				zombie1.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
				zombie1.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(128.0D);
				zombies.add(zombie1);
			}
			{
				Zombie zombie1 = (Zombie)game.getPlayground().getWorld().spawnEntity(game.getStart(), EntityType.ZOMBIE);
				zombie1.setCustomName("Z-"+game.getName());
				zombie1.setCustomNameVisible(true);
				zombie1.setSilent(true);
				zombie1.setBaby(false);
				zombie1.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
				zombie1.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
				zombie1.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(128.0D);
				zombies.add(zombie1);
			}
			{
				Zombie zombie1 = (Zombie)game.getPlayground().getWorld().spawnEntity(game.getStart(), EntityType.ZOMBIE);
				zombie1.setCustomName("Z-"+game.getName());
				zombie1.setCustomNameVisible(true);
				zombie1.setSilent(true);
				zombie1.setBaby(false);
				zombie1.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
				zombie1.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
				zombie1.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(128.0D);
				zombies.add(zombie1);
			}
			{
				Zombie zombie1 = (Zombie)game.getPlayground().getWorld().spawnEntity(game.getStart(), EntityType.ZOMBIE);
				zombie1.setCustomName("Z-"+game.getName());
				zombie1.setCustomNameVisible(true);
				zombie1.setSilent(true);
				zombie1.setBaby(false);
				zombie1.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
				zombie1.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
				zombie1.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(128.0D);
				zombies.add(zombie1);
			}
		} else {
			won = true;
		}
	}
	
	public ArrayList<Zombie> getZombies() {
		return zombies;
	}
	
	public int getRoundAt() {
		return roundAt;
	}
	
}
