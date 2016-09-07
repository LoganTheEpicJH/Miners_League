package com.minersleague.main.games.generall;

public class PlayerStorage {
	
	PlayingStage playingStage;
	String gameName;
	
	public PlayerStorage(String gameName, PlayingStage playingStage) {
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
