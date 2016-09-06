package com.minersleague.main.games.towerdefense;

public class TowerDefensePlayerStorage {
	
	PlayingStage playingStage;
	String gameName;
	
	public TowerDefensePlayerStorage(String gameName, PlayingStage playingStage) {
		this.gameName = gameName;
		this.playingStage = playingStage;
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public PlayingStage getPlayingStage() {
		return playingStage;
	}
	
}
