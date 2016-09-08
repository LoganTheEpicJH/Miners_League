package com.minersleague.main.games.towerdefense.tower;

import java.util.ArrayList;

public class Tower {

	private ArrayList<TowerBlock> blocks;
	private ArrayList<TowerStage> stages;
	private boolean hasStages;
	
	public Tower(ArrayList<TowerBlock> blocks, boolean hasStages, ArrayList<TowerStage> stages) {
		this.blocks = blocks;
		this.hasStages = hasStages;
		this.stages = stages;
	}
	
	public ArrayList<TowerStage> getTowerStages() {
		return stages;
	}
	
	public boolean hasStages() {
		return hasStages;
	}
	
	public ArrayList<TowerBlock> getBlocks() {
		return blocks;
	}
	
}
