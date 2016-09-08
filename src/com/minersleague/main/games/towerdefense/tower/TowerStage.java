package com.minersleague.main.games.towerdefense.tower;

import java.util.ArrayList;

public class TowerStage {

	public int stageID;
	public boolean finalStage;
	public ArrayList<TowerBlock> blocksToPlace;
	public long timeToStart, delay;
	
	public TowerStage(int stageID, boolean finalStage, ArrayList<TowerBlock> blocksToPlace, long timeToStart, long delay) {
		this.stageID = stageID;
		this.finalStage = finalStage;
		this.blocksToPlace = blocksToPlace;
		this.timeToStart = timeToStart;
		this.delay = delay;
	}
	
}
