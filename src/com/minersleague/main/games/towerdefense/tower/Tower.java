package com.minersleague.main.games.towerdefense.tower;

import java.util.ArrayList;

import com.minersleague.main.games.towerdefense.tower.function.TowerFunction;

public class Tower {

	private ArrayList<TowerBlock> blocks;
	private ArrayList<TowerStage> stages;
	private boolean hasStages;
	private boolean repeatingFunction;
	private TowerFunction towerFunction;
	
	public Tower(ArrayList<TowerBlock> blocks) {
		this.blocks = blocks;
		this.hasStages = false;
		this.repeatingFunction = false;
	}
	
	public Tower(ArrayList<TowerBlock> blocks, TowerFunction towerFunction) {
		this.blocks = blocks;
		this.towerFunction = towerFunction;
		this.hasStages = false;
		this.repeatingFunction = true;
	}
	
	public Tower(ArrayList<TowerBlock> blocks, boolean hasStages, ArrayList<TowerStage> stages) {
		this.blocks = blocks;
		this.hasStages = hasStages;
		this.stages = stages;
	}
	
	public Tower(ArrayList<TowerBlock> blocks, boolean repeatingFunction, TowerFunction towerFunction) {
		this.blocks = blocks;
		this.repeatingFunction = repeatingFunction;
		this.towerFunction = towerFunction;
	}
	
	public ArrayList<TowerStage> getTowerStages() {
		return stages;
	}
	
	public boolean hasStages() {
		return hasStages;
	}
	
	public boolean hasRepeatingFunction() {
		return repeatingFunction;
	}
	
	public TowerFunction getTowerFunction() {
		return towerFunction;
	}
	
	public ArrayList<TowerBlock> getBlocks() {
		return blocks;
	}
	
}
