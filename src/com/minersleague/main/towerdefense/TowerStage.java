package com.minersleague.main.towerdefense;

import java.util.ArrayList;

public class TowerStage {

	private int stageID;
	private boolean finalStage;
	private ArrayList<TowerBlock> oldBlocks = new ArrayList<TowerBlock>();
	private ArrayList<TowerBlock> newBlocks = new ArrayList<TowerBlock>();
	
	public TowerStage(int stageID, boolean finalStage, ArrayList<TowerBlock> oldBlocks, ArrayList<TowerBlock> newBlocks) {
		this.stageID = stageID;
		this.finalStage = finalStage;
		this.oldBlocks = oldBlocks;
		this.newBlocks = newBlocks;
	}
	
	public int getStageID() {
		return stageID;
	}
	
	public boolean isFinalStage() {
		return finalStage;
	}
	
	public ArrayList<TowerBlock> getOldBlocks() {
		return oldBlocks;
	}
	
	public ArrayList<TowerBlock> getNewBlocks() {
		return newBlocks;
	}
	
}
