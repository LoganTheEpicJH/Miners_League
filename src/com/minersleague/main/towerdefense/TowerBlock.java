package com.minersleague.main.towerdefense;

import org.bukkit.Material;

public class TowerBlock {

	public int x, y, z;
	public byte direction;
	private Material material;
	
	public TowerBlock(int x, int y, int z, Material material) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.material = material;
	}
	
	public TowerBlock(int x, int y, int z, Material material, byte direction) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.material = material;
		this.direction = direction;
	}
	
	public Material getMaterial() {
		return material;
	}
	
}
