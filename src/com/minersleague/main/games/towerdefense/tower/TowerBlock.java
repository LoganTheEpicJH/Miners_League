package com.minersleague.main.games.towerdefense.tower;

import com.minersleague.main.util.BlockResource;

public class TowerBlock {

	public int x, y, z;
	private BlockResource material;
	
	public TowerBlock(int x, int y, int z, BlockResource material) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.material = material;
	}
	
	public BlockResource getBlock() {
		return material;
	}
	
}
