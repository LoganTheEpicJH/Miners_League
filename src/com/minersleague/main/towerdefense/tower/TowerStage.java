package com.minersleague.main.towerdefense.tower;

import java.util.ArrayList;

import com.minersleague.main.towerdefense.tower.function.TowerFunction;

public class TowerStage {

	public int stageID;
	public boolean finalStage;
	public ArrayList<TowerBlock> blocksToPlace;
	public long timeToStart, delay;
	public TowerFunction tf;
	public double radius;
	
	public TowerStage(int stageID, boolean finalStage, ArrayList<TowerBlock> blocksToPlace, long timeToStart, long delay, TowerFunction towerFunction, double radius) {
		this.stageID = stageID;
		this.finalStage = finalStage;
		this.blocksToPlace = blocksToPlace;
		this.timeToStart = timeToStart;
		this.delay = delay;
		this.tf = towerFunction;
		this.radius = radius;
	}
	
}
