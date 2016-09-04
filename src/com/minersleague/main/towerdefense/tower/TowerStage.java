package com.minersleague.main.towerdefense.tower;

import java.util.ArrayList;

public class TowerStage {

	public int stageID;
	public boolean finalStage;
	public ArrayList<TowerBlock> oldBlocks = new ArrayList<TowerBlock>();
	public ArrayList<TowerBlock> newBlocks = new ArrayList<TowerBlock>();
	public long timeToStart, delay;
	
	public TowerStage(int stageID, boolean finalStage, ArrayList<TowerBlock> oldBlocks, ArrayList<TowerBlock> newBlocks, long timeToStart, long delay) {
		this.stageID = stageID;
		this.finalStage = finalStage;
		this.oldBlocks = oldBlocks;
		this.newBlocks = newBlocks;
		this.timeToStart = timeToStart;
		this.delay = delay;
	}
	
}
