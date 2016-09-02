package com.minersleague.main.towerdefense;

import java.util.ArrayList;

public class Tower {

	private ArrayList<TowerBlock> blocks;
	
	public Tower(ArrayList<TowerBlock> blocks) {
		this.blocks = blocks;
	}
	
	public ArrayList<TowerBlock> getBlocks() {
		return blocks;
	}
	
}
