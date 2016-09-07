package com.minersleague.main.games.codwarfare;

import org.bukkit.Material;

public enum Guns {
	
	Barret_50cal(50, Material.BLAZE_ROD);
	
	private int damage;
	private Material material;
	
	private Guns(int damage, Material material) {
		this.damage = damage;
		this.material = material;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public Material getMaterial() {
		return material;
	}
	
}
