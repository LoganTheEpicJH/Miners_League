package com.minersleague.main.towerdefense.tower;

import org.bukkit.Material;

import com.minersleague.main.towerdefense.BlockMetaData;

public class TowerBlock {

	public int x, y, z;
	private Material material;
	private BlockMetaData[] meta;
	private int specialData;
	
	public TowerBlock(int x, int y, int z, Material material) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.material = material;
	}
	
	public TowerBlock(int x, int y, int z, Material material, BlockMetaData[] meta) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.material = material;
		this.meta = meta;
	}
	
	public TowerBlock(int x, int y, int z, Material material, int meta) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.material = material;
		this.specialData = meta;
	}
	
	public int getSpecialData() {
		return specialData;
	}
	
	public BlockMetaData[] getMetaData() {
		return meta;
	}
	
	public Material getMaterial() {
		return material;
	}
	
}
