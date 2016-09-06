package com.minersleague.main.games.towerdefense.tower;

import java.util.ArrayList;

import com.minersleague.main.games.towerdefense.tower.function.TowerFunction;

public class Tower {

	private ArrayList<TowerBlock> blocks;
	private ArrayList<TowerStage> stages;
	private boolean hasStages;
	private TowerFunction towerFunction;
	private double radius;
	
	public Tower(ArrayList<TowerBlock> blocks, TowerFunction towerFunction, double radius) {
		this.blocks = blocks;
		this.towerFunction = towerFunction;
		this.radius = radius;
		hasStages = false;
		stages = null;
	}
	
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
	
	public TowerFunction getRepeatingFunction() {
		return towerFunction;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public ArrayList<TowerBlock> getBlocks() {
		return blocks;
	}
	
}
